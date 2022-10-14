package ciphers;

import java.util.Scanner;

public class vigenereCipher {
    // generate key
    static String Key(String str, String k)
    {
        int x = str.length();
        for (int m = 0; ; m++)
        {
            if (x == m)
                m = 0;
            if (k.length() == str.length())
                break;
            k+=(k.charAt(m));
        }
        return k;
    }

    // encrypting text
    static String encr(String str, String k)
    {
        String cipher="";

        for (int t = 0; t < str.length(); t++)
        {
            int x = (str.charAt(t) + k.charAt(t)) %26;
            x += 'A';
            cipher+=(char)(x);
        }
        return cipher;
    }

    // decrypting text
    static String decr(String cipher, String k)
    {
        String decr_text="";
        for (int t = 0 ; t < cipher.length() && t < k.length(); t++)
        {
            int x = (cipher.charAt(t) - k.charAt(t) + 26) %26;
            x += 'A';
            decr_text+=(char)(x);
        }
        return decr_text;
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input plaintext: ");
        String S = scan.nextLine();
        System.out.println("Ciphertext: ");
        String K = scan.nextLine();
        String string = S.toUpperCase();
        String key = K.toUpperCase();
        String k = Key(string, key);
        System.out.println("Ciphertext: " + encr(string, k).toLowerCase() + "\n");
        System.out.println("Decrypted Text: " + decr(encr(string, k), k).toLowerCase());
    }
}
