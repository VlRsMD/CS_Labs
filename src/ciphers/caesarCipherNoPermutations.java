package ciphers;
import java.util.*;

public class caesarCipherNoPermutations {
    public static final String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

    // encrypting text
    public static String caesarCipherEncr(String message, int substitutionKey) {
        char[] inputArrayChar = message.toLowerCase().toCharArray();
        String encrMessage = "";
        for (int k=0; k<inputArrayChar.length; k++)
        {
            int encrCharPos = (alphabetEng.indexOf(inputArrayChar[k]) + substitutionKey) % 26;
            char encrChar = alphabetEng.charAt(encrCharPos);
            encrMessage+=encrChar;
        }
        return encrMessage;
    }

    // decrypting text
    public static String caesarCipherDecr(String message, int substitutionKey) {
        char[] inputArrayChar = message.toLowerCase().toCharArray();
        String decrMessage = "";
        for (int k=0; k<inputArrayChar.length; k++)
        {
            if(alphabetEng.indexOf(inputArrayChar[k]) >= substitutionKey) {
                int decrCharPos = (alphabetEng.indexOf(inputArrayChar[k]) - substitutionKey) % 26;
                char decrChar = alphabetEng.charAt(decrCharPos);
                decrMessage += decrChar;
            }
            if(alphabetEng.indexOf(inputArrayChar[k]) < substitutionKey) {
                int decrCharPos = (26 + alphabetEng.indexOf(inputArrayChar[k]) - substitutionKey) % 26;
                char decrChar = alphabetEng.charAt(decrCharPos);
                decrMessage += decrChar;
            }
        }
        return decrMessage;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input the text of the message to be encrypted: ");
        String messageIn_E = in.nextLine();
        System.out.println("Input the substitution key of the message to be encrypted: ");
        int substKeyIn_E = in.nextInt();
        Scanner inD = new Scanner(System.in);
        System.out.println("Input the text of the message to be decrypted: ");
        String messageIn_D = inD.nextLine();
        System.out.println("Input the substitution key of the message to be decrypted: ");
        int substKeyIn_D = inD.nextInt();
        System.out.println("The encrypted message is: " + caesarCipherEncr(messageIn_E, substKeyIn_E));
        System.out.println("The decrypted message is: " + caesarCipherDecr(messageIn_D, substKeyIn_D));
    }
}
