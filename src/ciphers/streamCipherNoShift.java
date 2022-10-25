package ciphers;
import java.util.Scanner;

public class streamCipherNoShift {
    // convert string to an integer array
    static int[] stringToIntArray(String string) {
        int l = string.length();
        char[] charArr = new char[l];
        for (int i = 0; i < l; i++) {
            charArr[i] = string.charAt(i);
        }
        int [] intArr = new int [l];
        for(int i=0; i<l; i++) {
            intArr[i] = Integer.parseInt(String.valueOf(charArr[i]));
        }
        return intArr;
    }

    // convert integer array to string
    static String intArrayToString(int[] intArr) {
        StringBuffer sBf = new StringBuffer();
        String strSep = " ";
        for(int i=0; i < intArr.length; i++){
            sBf.append(intArr[i]);
        }
        String res = sBf.toString();
        return res;
    }

    // encrypt
    static String encryption(String plaintext, String keystream) {
        int[] plaintextInt = stringToIntArray(plaintext);
        int[] keystreamInt = stringToIntArray(keystream);
        int[] ciphertextInt = new int[plaintextInt.length];
        // perform XOR operations on corresponding bits of plaintext and keystream in order to obtain ciphertext
        for (int i = 0; i<plaintextInt.length; i++) {
            if (plaintextInt[i]==0 && keystreamInt[i]==0) {
                ciphertextInt[i] = 0;
            }
            if (plaintextInt[i]==0 && keystreamInt[i]==1) {
                ciphertextInt[i] = 1;
            }
            if (plaintextInt[i]==1 && keystreamInt[i]==0) {
                ciphertextInt[i] = 1;
            }
            if (plaintextInt[i]==1 && keystreamInt[i]==1) {
                ciphertextInt[i] = 0;
            }
        }
        String ciphertext = intArrayToString(ciphertextInt);
        return ciphertext;
    }

    // decrypt
    static String decryption(String ciphertext, String keystream) {
        int[] ciphertextInt = stringToIntArray(ciphertext);
        int[] keystreamInt = stringToIntArray(keystream);
        int[] plaintextInt = new int[ciphertextInt.length];
        // perform XOR operations on corresponding bits of ciphertext and keystream in order to obtain plaintext
        for (int i = 0; i<ciphertextInt.length; i++) {
            if (ciphertextInt[i]==0 && keystreamInt[i]==0) {
                plaintextInt[i] = 0;
            }
            if (ciphertextInt[i]==0 && keystreamInt[i]==1) {
                plaintextInt[i] = 1;
            }
            if (ciphertextInt[i]==1 && keystreamInt[i]==0) {
                plaintextInt[i] = 1;
            }
            if (ciphertextInt[i]==1 && keystreamInt[i]==1) {
                plaintextInt[i] = 0;
            }
        }
        String plaintext = intArrayToString(plaintextInt);
        return plaintext;
    }

    public static void main(String[] args) {
        // test case (ciphertext should be '101110110')
        System.out.println("The ciphertext for the plaintext '111001101' and keystream '010111011' is: " + encryption("111001101", "010111011"));
        // encrypt and decrypt data from input
        Scanner inEnc = new Scanner(System.in);
        System.out.println("Input plaintext Nr. 1: ");
        String plaintext= inEnc.nextLine();
        System.out.println("Input the keystream for encryption (please let it be of the same length as the plaintext Nr.1): ");
        String keystreamE = inEnc.nextLine();
        Scanner inDec = new Scanner(System.in);
        System.out.println("Input the ciphertext Nr.2: ");
        String ciphertext= inDec.nextLine();
        System.out.println("Input the keystream for decryption (please let it be of the same length as the ciphertext Nr. 2): ");
        String keystreamD = inDec.nextLine();
        System.out.println("The ciphertext for the plaintext Nr. 1 is: " + encryption(plaintext, keystreamE));
        System.out.println("The plaintext for the ciphertext Nr. 2 is: " + decryption(ciphertext, keystreamD));
    }
}
