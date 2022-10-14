package ciphers;

import java.util.Arrays;
import java.util.Scanner;

public class playfairCipher {
    // remove duplicate values from key
    static String remDupl(String s)
    {
        int k, index = 0, len = s.length();
        char c[] = s.toCharArray();
        for (int i = 0; i < len; i++) {
            for (k = 0; k < i; k++) {
                if (c[i] == c[k])
                    break;
            }
            if (i == k)
                c[index++] = c[i];
        }
        s = new String((Arrays.copyOf(c, index)));
        return s;
    }

    // remove white spaces
    static String remWhSp(char[] ch, String k)
    {
        char[] c = k.toCharArray();
        for (int l = 0; l < c.length; l++) {
            for (int m = 0; m < ch.length; m++) {
                if (c[l] == ch[m])
                    c[l] = ' ';
            }
        }
        k = new String(c);
        k = k.replaceAll(" ", "");
        return k;
    }

    // making pair to encrypt
    static String Pair(String p)
    {
        String s = "";
        char c = 'a';
        for (int k = 0; k < p.length(); k++) {
            if (p.charAt(k) == ' ')
                continue;
            else {
                c = p.charAt(k);
                s += p.charAt(k);
            }
            if (k < p.length() - 1)
                if (p.charAt(k) == p.charAt(k + 1))
                    s += "x";
        }
        if (s.length() % 2 != 0)
            s += "x";
        System.out.println(s);
        return s;
    }

    // find row and column position to encrypt the pair
    static int[] KL(char a, char b, char x[][])
    {
        int[] y = new int[4];
        if (a == 'l')
            a = 'k';
        else if (b == 'l')
            b = 'k';
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (x[k][l] == a) {
                    y[0] = k;
                    y[1] = l;
                }
                else if (x[k][l] == b) {
                    y[2] = k;
                    y[3] = l;
                }
            }
        }
        if (y[0] == y[2]) {
            y[1] += 1;
            y[3] += 1;
        }
        else if (y[1] == y[3]) {
            y[0] += 1;
            y[2] += 1;
        }
        for (int k = 0; k < 4; k++)
            y[k] %= 5;
        return y;
    }

    // encrypting text
    static String encr(String p, char x[][])
    {
        char c[] = p.toCharArray();
        int a[] = new int[4];
        for (int k = 0; k < p.length(); k += 2) {
            if (k < p.length() - 1) {
                a = KL(p.charAt(k), p.charAt(k + 1),
                        x);
                if (a[0] == a[2]) {
                    c[k] = x[a[0]][a[1]];
                    c[k + 1] = x[a[0]][a[3]];
                }
                else if (a[1] == a[3]) {
                    c[k] = x[a[0]][a[1]];
                    c[k + 1] = x[a[2]][a[1]];
                }
                else {
                    c[k] = x[a[0]][a[3]];
                    c[k + 1] = x[a[2]][a[1]];
                }
            }
        }
        p = new String(c);
        return p;
    }

    public static void main(String[] args)
    {
        // test case (the ciphertext should be "rfkconxvqekhrkkabr")
        String p0 = "sehenswurdigkeiten";
        String key0 = "platz";
        key0 = remDupl(key0);
        char[] ch0 = key0.toCharArray();
        String alph0 = "abcdefghiklmnopqrstuvwxyz";
        alph0 = remWhSp(ch0, alph0);
        char[] c0 = alph0.toCharArray();
        char[][] x0 = new char[5][5];
        int indexOfSt0 = 0, indexOfKey0 = 0;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (indexOfKey0 < key0.length())
                    x0[k][l] = ch0[indexOfKey0++];
                else
                    x0[k][l] = c0[indexOfSt0++];
            }
        }
        p0 = encr(p0, x0);
        System.out.println("Ciphertext for plaintext 'sehenswurdigkeiten' and key 'platz' is: " + p0);
        // generating ciphertext from input data
        Scanner scan = new Scanner(System.in);
        System.out.println("Input plaintext: ");
        String p = scan.nextLine();
        System.out.println("Input key: ");
        String key = scan.nextLine();
        key = remDupl(key);
        char[] ch = key.toCharArray();
        String alph = "abcdefghiklmnopqrstuvwxyz";
        alph = remWhSp(ch, alph);
        char[] c = alph.toCharArray();
        char[][] x = new char[5][5];
        int indexOfSt = 0, indexOfKey = 0;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (indexOfKey < key.length())
                    x[k][l] = ch[indexOfKey++];
                else
                    x[k][l] = c[indexOfSt++];
            }
        }
        p = encr(p, x);
        System.out.println("Ciphertext is: " + p);
    }
}
