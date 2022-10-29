package ciphers;
import interfaces.cipherText;

public class streamCipherShift implements cipherText {
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

    static String encrypt(String plaintext) {
        int[] plaintextInt = stringToIntArray(plaintext);
        int l = 8;
        // assign the position of bits which should be XORed
        int s1 = 1;
        int s2 = 6;
        // make copy of plaintext integer array for modifications
        int[] plaintextIntCopy = new int[l];
        for (int i=0; i<l; i++) {
            plaintextIntCopy[i] = plaintextInt[i];
        }
        int[] keystreamInt = new int[8];
        // assign value of the last bit of initial plaintext to first bit of keystream
        keystreamInt[0] = plaintextIntCopy[7];
        // consecutively shift bits of plaintext copy array to right and assign the first bit of each modified plaintext iteration to XORed value of 1st and 6th bit of the previous iteration
        for (int i=1; i<l; i++) {
            for (int j = 1; j < l; j++) {
                if (plaintextIntCopy[s1] == 0 && plaintextIntCopy[s2] == 0) {
                    plaintextIntCopy[0] = 0;
                }
                if (plaintextIntCopy[s1] == 0 && plaintextIntCopy[s2] == 1) {
                    plaintextIntCopy[0] = 1;
                }
                if (plaintextIntCopy[s1] == 1 && plaintextIntCopy[s2] == 0) {
                    plaintextIntCopy[0] = 1;
                }
                if (plaintextIntCopy[s1] == 1 && plaintextIntCopy[s2] == 1) {
                    plaintextIntCopy[0] = 0;
                }
                plaintextIntCopy[j] = plaintextIntCopy[j - 1];
            }
            keystreamInt[i] = plaintextInt[7];
        }
        // perform XOR operations on corresponding bits of plaintext and keystream in order to obtain ciphertext
        int[] ciphertextInt = new int[8];
        for (int i=1; i<l; i++) {
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

    static String decrypt(String ciphertext) {
        int[] ciphertextInt = stringToIntArray(ciphertext);
        int l = 8;
        // assign the position of bits which should be XORed
        int s1 = 1;
        int s2 = 6;
        // make copy of plaintext integer array for modifications
        int[] ciphertextIntCopy = new int[l];
        for (int i=0; i<l; i++) {
            ciphertextIntCopy[i] = ciphertextInt[i];
        }
        int[] keystreamInt = new int[8];
        // assign value of the last bit of initial plaintext to first bit of keystream
        keystreamInt[0] = ciphertextIntCopy[7];
        // consecutively shift bits of plaintext copy array to right and assign the first bit of each modified plaintext iteration to XORed value of 1st and 6th bit of the previous iteration
        for (int i=1; i<l; i++) {
            for (int j = 1; j < l; j++) {
                if (ciphertextIntCopy[s1] == 0 && ciphertextIntCopy[s2] == 0) {
                    ciphertextIntCopy[0] = 0;
                }
                if (ciphertextIntCopy[s1] == 0 && ciphertextIntCopy[s2] == 1) {
                    ciphertextIntCopy[0] = 1;
                }
                if (ciphertextIntCopy[s1] == 1 && ciphertextIntCopy[s2] == 0) {
                    ciphertextIntCopy[0] = 1;
                }
                if (ciphertextIntCopy[s1] == 1 && ciphertextIntCopy[s2] == 1) {
                    ciphertextIntCopy[0] = 0;
                }
                ciphertextIntCopy[j] = ciphertextIntCopy[j - 1];
            }
            keystreamInt[i] = ciphertextInt[7];
        }
        // perform XOR operations on corresponding bits of plaintext and keystream in order to obtain ciphertext
        int[] plaintextInt = new int[8];
        for (int i=1; i<l; i++) {
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
        String plaintext = intArrayToString(ciphertextInt);
        return plaintext;
    }

    /*public static void main(String[] args) {
        Scanner inEnc = new Scanner(System.in);
        System.out.println("Input plaintext (of length 8): ");
        String plaintext= inEnc.nextLine();
        System.out.println("Ciphertext is: " + encryption(plaintext));
    }*/
}
