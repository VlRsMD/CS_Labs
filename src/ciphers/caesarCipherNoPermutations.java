package ciphers;
import interfaces.cipherTextKeyInt;

public class caesarCipherNoPermutations implements cipherTextKeyInt {
    public static String encrypt(String plaintext, int key) {
        String alphabetEng = "abcdefghijklmnopqrstuvwxyz";
        char[] inputArrayChar = plaintext.toLowerCase().toCharArray();
        String encrMessage = "";
        for (int k=0; k<inputArrayChar.length; k++)
        {
            int encrCharPos = (alphabetEng.indexOf(inputArrayChar[k]) + key) % 26;
            char encrChar = alphabetEng.charAt(encrCharPos);
            encrMessage+=encrChar;
        }
        return encrMessage;
    }

    public static String decrypt(String ciphertext, int key) {
        String alphabetEng = "abcdefghijklmnopqrstuvwxyz";
        char[] inputArrayChar = ciphertext.toLowerCase().toCharArray();
        String decrMessage = "";
        for (int k=0; k<inputArrayChar.length; k++)
        {
            if(alphabetEng.indexOf(inputArrayChar[k]) >= key) {
                int decrCharPos = (alphabetEng.indexOf(inputArrayChar[k]) - key) % 26;
                char decrChar = alphabetEng.charAt(decrCharPos);
                decrMessage += decrChar;
            }
            if(alphabetEng.indexOf(inputArrayChar[k]) < key) {
                int decrCharPos = (26 + alphabetEng.indexOf(inputArrayChar[k]) - key) % 26;
                char decrChar = alphabetEng.charAt(decrCharPos);
                decrMessage += decrChar;
            }
        }
        return decrMessage;
    }
}
