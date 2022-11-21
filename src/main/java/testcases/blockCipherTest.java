package testcases;
import ciphers.blockCipher;

import java.util.Scanner;

public class blockCipherTest {
    public static void main(String[] args) {
        Scanner inEnc = new Scanner(System.in);
        System.out.println("Input plaintext Nr. 1: ");
        String plaintext= inEnc.nextLine();
        System.out.println("Input the keystream for encryption: ");
        String keystreamE = inEnc.nextLine();
        Scanner inDec = new Scanner(System.in);
        System.out.println("Input the ciphertext Nr.2: ");
        String ciphertext= inDec.nextLine();
        System.out.println("Input the keystream for decryption: ");
        String keystreamD = inDec.nextLine();
        System.out.println("The ciphertext for the plaintext Nr. 1 is: " + blockCipher.encrypt(plaintext, keystreamE) + "\n");
        System.out.println("The plaintext for the ciphertext Nr. 2 is: " + blockCipher.decrypt(ciphertext, keystreamD));
    }
}
