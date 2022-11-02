package testcases;

import ciphers.RSACipher;

import java.util.Scanner;

public class RSACipherTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input the integer plaintext nr. 1: ");
        int plaintext = in.nextInt();
        System.out.println("Input the integer ciphertext nr. 2: ");
        int ciphertext = in.nextInt();
        System.out.println("The ciphertext nr. 1 is: "+RSACipher.encrypt(plaintext));
        System.out.println("The plaintext nr. 2 is: "+RSACipher.decrypt(ciphertext));
    }
}
