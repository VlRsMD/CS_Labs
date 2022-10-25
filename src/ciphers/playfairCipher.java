package ciphers;

import java.util.Arrays;
import java.util.Scanner;

public class playfairCipher {
    // identify row and column position in order to encrypt the pair
    static int[] pairRC(char row, char col, char pos[][])
    {
        int[] arr = new int[4];
        if (row == 'c')
            row = 'r';
        else if (col == 'c')
            col = 'r';
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (pos[r][c] == row) {
                    arr[0] = r;
                    arr[1] = c;
                }
                else if (pos[r][c] == col) {
                    arr[2] = r;
                    arr[3] = c;
                }
            }
        }
        if (arr[0] == arr[2]) {
            arr[1] += 1;
            arr[3] += 1;
        }
        else if (arr[1] == arr[3]) {
            arr[0] += 1;
            arr[2] += 1;
        }
        for (int r = 0; r < 4; r++)
            arr[r] %= 5;
        return arr;
    }

    // encrypting text
    static String playfairEnc(String plaintext, char res[][])
    {
        char chPt[] = plaintext.toCharArray();
        int a[] = new int[4];
        for (int k = 0; k < plaintext.length(); k += 2) {
            if (k < plaintext.length() - 1) {
                a = pairRC(plaintext.charAt(k), plaintext.charAt(k + 1),
                        res);
                if (a[0] == a[2]) {
                    chPt[k] = res[a[0]][a[1]];
                    chPt[k + 1] = res[a[0]][a[3]];
                }
                else if (a[1] == a[3]) {
                    chPt[k] = res[a[0]][a[1]];
                    chPt[k + 1] = res[a[2]][a[1]];
                }
                else {
                    chPt[k] = res[a[0]][a[3]];
                    chPt[k + 1] = res[a[2]][a[1]];
                }
            }
        }
        String ciphertext = new String(chPt);
        return ciphertext;
    }

    // eliminate white spaces
    static String whiteSpaceElim(char[] chArrK, String kS)
    {
        char[] c = kS.toCharArray();
        for (int l = 0; l < c.length; l++) {
            for (int m = 0; m < chArrK.length; m++) {
                if (c[l] == chArrK[m])
                    c[l] = ' ';
            }
        }
        String noWhSp = new String(c);
        String noWhSpR = noWhSp.replaceAll(" ", "");
        return noWhSpR;
    }

    // eliminate duplicate values from key
    static String duplicateElim(String keyStr)
    {
        int id = 0;
        char c[] = keyStr.toCharArray();
        for (int i = 0; i < keyStr.length(); i++) {
            int j;
            for (j = 0; j < i; j++) {
                if (c[i] == c[j])
                    break;
            }
            if (i == j)
                c[id++] = c[i];
        }
        keyStr = new String((Arrays.copyOf(c, id)));
        return keyStr;
    }

    public static void main(String[] args)
    {
        // test case (the ciphertext should be "rfkconxvqekhrkkabr")
        String pt0 = "sehenswurdigkeiten";
        String keyS0 = "platz";
        keyS0 = duplicateElim(keyS0);
        char[] chK0 = keyS0.toCharArray();
        String alph0 = "abcdefghiklmnopqrstuvwxyz";
        alph0 = whiteSpaceElim(chK0, alph0);
        char[] c0 = alph0.toCharArray();
        char[][] x0 = new char[5][5];
        int stIndex0 = 0, keyIndex0 = 0;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (keyIndex0 < keyS0.length())
                    x0[k][l] = chK0[keyIndex0++];
                else
                    x0[k][l] = c0[stIndex0++];
            }
        }
        pt0 = playfairEnc(pt0, x0);
        System.out.println("Ciphertext for plaintext 'sehenswurdigkeiten' and key 'platz' is: " + pt0);
        // generating ciphertext from input data
        Scanner scan = new Scanner(System.in);
        System.out.println("Input plaintext: ");
        String pt = scan.nextLine();
        System.out.println("Input keystream: ");
        String keyS = scan.nextLine();
        keyS = duplicateElim(keyS);
        char[] chK = keyS.toCharArray();
        String alph = "abcdefghiklmnopqrstuvwxyz";
        alph = whiteSpaceElim(chK, alph);
        char[] ch = alph.toCharArray();
        char[][] matrix = new char[5][5];
        int stIndex = 0, keyIndex = 0;
        // fill the 5*5 matrix with keystream first and then with the modified alphabet without keystream chars
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (keyIndex < keyS.length())
                    matrix[k][l] = chK[keyIndex++];
                else
                    matrix[k][l] = ch[stIndex++];
            }
        }
        pt = playfairEnc(pt, matrix);
        System.out.println("Ciphertext is: " + pt);
    }
}
