package ciphers;

import java.util.Scanner;

public class vigenereCipher {
    public static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    // encrypting text
    public static String vigenereCipherEncr(String plaintext, String keystream) {
        char[] pTextArrayChar = plaintext.toLowerCase().toCharArray();
        char[] keyArrayChar = keystream.toLowerCase().toCharArray();
        String encrMessage = "";
        for (int k=0; k<pTextArrayChar.length && k<keyArrayChar.length; k++)
        {
            int encrCharPos = (alphabetEng.indexOf(pTextArrayChar[k]) + alphabetEng.indexOf(keyArrayChar[k])) % 26;
            char encrChar = alphabetEng.charAt(encrCharPos);
            encrMessage+=encrChar;
        }
        return encrMessage;
    }

    // decrypting text
    public static String vigenereCipheDecr(String ciphertext, String keystream) {
        char[] cTextArrayChar = ciphertext.toLowerCase().toCharArray();
        char[] keyArrayChar = keystream.toLowerCase().toCharArray();
        String decrMessage = "";
        for (int k=0; k<cTextArrayChar.length && k<keyArrayChar.length; k++)
        {
            int decrCharPos = (alphabetEng.indexOf(cTextArrayChar[k]) - alphabetEng.indexOf(keyArrayChar[k]) + 26) % 26;
            char decrChar = alphabetEng.charAt(decrCharPos);
            decrMessage+=decrChar;
        }
        return decrMessage;
    }


    public static void main(String[] args)
    {
        Scanner scan0 = new Scanner(System.in);
        System.out.println("Input plaintext nr. 1: ");
        String pt = scan0.nextLine();
        System.out.println("Input keystream nr. 1: ");
        String kP = scan0.nextLine();
        String ptS = pt.toLowerCase();
        String kPS = kP.toLowerCase();
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Input ciphertext nr. 2: ");
        String ct = scan1.nextLine();
        System.out.println("Input keystream nr. 2: ");
        String kC = scan1.nextLine();
        String ctS = ct.toLowerCase();
        String kCS = kC.toLowerCase();
        System.out.println("The encrypted ciphertext for plaintext nr. 1 is: " + vigenereCipherEncr(ptS, kPS) + "\n");
        System.out.println("The decrypted plaintext for ciphertext nr. 2 is: " + vigenereCipheDecr(ctS, kCS));
    }
}
