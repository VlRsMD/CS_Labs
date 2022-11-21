package ciphers;
import interfaces.cipherTextKeystr;

public class vigenereCipher implements cipherTextKeystr {
    public static String encrypt(String plaintext, String keystream) {
        String alphabetEng = "abcdefghijklmnopqrstuvwxyz";
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

    public static String decrypt(String ciphertext, String keystream) {
        String alphabetEng = "abcdefghijklmnopqrstuvwxyz";
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
}
