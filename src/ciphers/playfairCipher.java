package ciphers;
import interfaces.cipherTextKeystr;

import java.util.Arrays;

public class playfairCipher implements cipherTextKeystr {
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

    static String encrypt(String plaintext, char matrix[][])
    {
        char chPt[] = plaintext.toCharArray();
        int a[] = new int[4];
        for (int k = 0; k < plaintext.length(); k += 2) {
            if (k < plaintext.length() - 1) {
                a = pairRC(plaintext.charAt(k), plaintext.charAt(k + 1),
                        matrix);
                if (a[0] == a[2]) {
                    chPt[k] = matrix[a[0]][a[1]];
                    chPt[k + 1] = matrix[a[0]][a[3]];
                }
                else if (a[1] == a[3]) {
                    chPt[k] = matrix[a[0]][a[1]];
                    chPt[k + 1] = matrix[a[2]][a[1]];
                }
                else {
                    chPt[k] = matrix[a[0]][a[3]];
                    chPt[k + 1] = matrix[a[2]][a[1]];
                }
            }
        }
        String ciphertext = new String(chPt);
        return ciphertext;
    }

    static String matrixCr(String ciphertext, char matrix[][])
    {
        char chPt[] = ciphertext.toCharArray();
        int a[] = new int[4];
        for (int k = 0; k < ciphertext.length(); k += 2) {
            if (k < ciphertext.length() - 1) {
                a = pairRC(ciphertext.charAt(k), ciphertext.charAt(k + 1),
                        matrix);
                if (a[0] == a[2]) {
                    chPt[k] = matrix[a[0]][a[1]];
                    chPt[k + 1] = matrix[a[0]][a[3]];
                }
                else if (a[1] == a[3]) {
                    chPt[k] = matrix[a[0]][a[1]];
                    chPt[k + 1] = matrix[a[2]][a[1]];
                }
                else {
                    chPt[k] = matrix[a[0]][a[3]];
                    chPt[k + 1] = matrix[a[2]][a[1]];
                }
            }
        }
        String plaintext = new String(chPt);
        return plaintext;
    }

    public static String encrypt (String plaintext, String keystream) {
        String keySNoDupl = duplicateElim(keystream);
        char[] chKstr = keySNoDupl.toCharArray();
        String alph = "abcdefghiklmnopqrstuvwxyz";
        alph = whiteSpaceElim(chKstr, alph);
        char[] chArr = alph.toCharArray();
        char[][] matrix = new char[5][5];
        int stIndex0 = 0, keyIndex0 = 0;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (keyIndex0 < keySNoDupl.length())
                    matrix[k][l] = chKstr[keyIndex0++];
                else
                    matrix[k][l] = chArr[stIndex0++];
            }
        }
        String ciphertext = matrixCr(plaintext, matrix);
        return ciphertext;
    }

    public static String decrypt (String ciphertext, String keystream) {
        String keySNoDupl = duplicateElim(keystream);
        char[] chKstr = keySNoDupl.toCharArray();
        String alph = "abcdefghiklmnopqrstuvwxyz";
        alph = whiteSpaceElim(chKstr, alph);
        char[] chArr = alph.toCharArray();
        char[][] matrix = new char[5][5];
        int stIndex0 = 0, keyIndex0 = 0;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (keyIndex0 < keySNoDupl.length())
                    matrix[k][l] = chKstr[keyIndex0++];
                else
                    matrix[k][l] = chArr[stIndex0++];
            }
        }
        String plaintext = matrixCr(ciphertext, matrix);
        return plaintext;
    }
}
