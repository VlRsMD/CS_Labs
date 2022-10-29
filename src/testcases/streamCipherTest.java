package testcases;
import ciphers.streamCipherNoShift;

import java.util.Scanner;

public class streamCipherTest {
    public static void main(String[] args) {
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
        System.out.println("The ciphertext for the plaintext Nr. 1 is: " + streamCipherNoShift.encrypt(plaintext, keystreamE) + "\n");
        System.out.println("The plaintext for the ciphertext Nr. 2 is: " + streamCipherNoShift.decrypt(ciphertext, keystreamD));
    }
}
