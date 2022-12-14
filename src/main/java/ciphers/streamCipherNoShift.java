package ciphers;
import interfaces.cipherTextKeystr;

public class streamCipherNoShift implements cipherTextKeystr {
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
    public static String encrypt(String plaintext, String keystream) {
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

    public static String decrypt(String ciphertext, String keystream) {
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
}
