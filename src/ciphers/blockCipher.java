package ciphers;
import interfaces.cipherTextKeystr;

public class blockCipher implements cipherTextKeystr {
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

    public static String encrypt(String plaintext, String keystream) {
        int[] plaintextInt = stringToIntArray(plaintext);
        int[] keystreamInt = stringToIntArray(keystream);
        int[] ciphertextInt = new int[plaintextInt.length];
        int k = keystreamInt.length;
        // calculating the number  of block usages
        int nOfBlocks = plaintextInt.length / k;
        // calculating the remainder of dividing the plaintext length by the keystream length
        int rem = plaintextInt.length % k;
        for (int i = 0; i<nOfBlocks; i++)
            for (int j=0; j<k; j++) {
                // perform XOR operations
                if (plaintextInt[i*k+j]==0 && keystreamInt[j]==0) {
                    ciphertextInt[i*k+j] = 0;
                }
                if (plaintextInt[i*k+j]==0 && keystreamInt[j]==1) {
                    ciphertextInt[i*k+j] = 1;
                }
                if (plaintextInt[i*k+j]==1 && keystreamInt[j]==0) {
                    ciphertextInt[i*k+j] = 1;
                }
                if (plaintextInt[i*k+j]==1 && keystreamInt[j]==1) {
                    ciphertextInt[i*k+j] = 0;
                }
            }
        // encrypting last bits which are left after consecutive usage of the full length keystream block
        if (rem>0) {
            for (int i=0; i<rem; i++) {
                if (plaintextInt[i*k+nOfBlocks]==0 && keystreamInt[i]==0) {
                    ciphertextInt[i*k+nOfBlocks] = 0;
                }
                if (plaintextInt[i*k+nOfBlocks]==0 && keystreamInt[i]==1) {
                    ciphertextInt[i*k+nOfBlocks] = 1;
                }
                if (plaintextInt[i*k+nOfBlocks]==1 && keystreamInt[i]==0) {
                    ciphertextInt[i*k+nOfBlocks] = 1;
                }
                if (plaintextInt[i*k+nOfBlocks]==1 && keystreamInt[i]==1) {
                    ciphertextInt[i*k+nOfBlocks] = 0;
                }
            }
        }
        String ciphertext = intArrayToString(ciphertextInt);
        return ciphertext;
    }

    public static String decrypt(String ciphertext, String keystream) {
        int[] ciphertextInt = stringToIntArray(ciphertext);
        int[] keystreamInt = stringToIntArray(keystream);
        int[] plaintextInt = new int[ciphertextInt.length];
        int k = keystreamInt.length;
        // calculating the number  of block usages
        int nOfBlocks = ciphertextInt.length / k;
        // calculating the remainder of dividing the plaintext length by the keystream length
        int rem = ciphertextInt.length % k;
        for (int i = 0; i<nOfBlocks; i++)
            for (int j=0; j<k; j++) {
                // perform XOR operations
                if (ciphertextInt[i*k+j]==0 && keystreamInt[j]==0) {
                    plaintextInt[i*k+j] = 0;
                }
                if (ciphertextInt[i*k+j]==0 && keystreamInt[j]==1) {
                    plaintextInt[i*k+j] = 1;
                }
                if (ciphertextInt[i*k+j]==1 && keystreamInt[j]==0) {
                    plaintextInt[i*k+j] = 1;
                }
                if (ciphertextInt[i*k+j]==1 && keystreamInt[j]==1) {
                    plaintextInt[i*k+j] = 0;
                }
            }
        // encrypting last bits which are left after consecutive usage of the full length keystream block
        if (rem>0) {
            for (int i=0; i<rem; i++) {
                if (ciphertextInt[i*k+nOfBlocks]==0 && keystreamInt[i]==0) {
                    plaintextInt[i*k+nOfBlocks] = 0;
                }
                if (ciphertextInt[i*k+nOfBlocks]==0 && keystreamInt[i]==1) {
                    plaintextInt[i*k+nOfBlocks] = 1;
                }
                if (ciphertextInt[i*k+nOfBlocks]==1 && keystreamInt[i]==0) {
                    plaintextInt[i*k+nOfBlocks] = 1;
                }
                if (ciphertextInt[i*k+nOfBlocks]==1 && keystreamInt[i]==1) {
                    plaintextInt[i*k+nOfBlocks] = 0;
                }
            }
        }
        String plaintext = intArrayToString(plaintextInt);
        return plaintext;
    }

    /*public static void main(String[] args) {
        // encrypt and decrypt data from input
        Scanner in = new Scanner(System.in);
        System.out.println("Input plaintext: ");
        String plaintext= in.nextLine();
        System.out.println("Input keystream: ");
        String keystream = in.nextLine();
        System.out.println("Ciphertext is: " + encrypt(plaintext, keystream));
    }*/
}
