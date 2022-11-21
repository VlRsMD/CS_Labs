package testcases;
import ciphers.vigenereCipher;

import java.util.Scanner;

public class vigenereCipherTest {
    public static void main(String[] args)
    {
        Scanner scan0 = new Scanner(System.in);
        System.out.println("Input plaintext nr. 1: ");
        String pt = scan0.nextLine();
        System.out.println("Input keystream nr. 1: ");
        String kP = scan0.nextLine();
        String ptS = pt.toLowerCase();
        String kPS = kP.toLowerCase();
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Input ciphertext nr. 2: ");
        String ct = scan1.nextLine();
        System.out.println("Input keystream nr. 2: ");
        String kC = scan1.nextLine();
        String ctS = ct.toLowerCase();
        String kCS = kC.toLowerCase();
        System.out.println("The encrypted ciphertext for plaintext nr. 1 is: " + vigenereCipher.encrypt(ptS, kPS) + "\n");
        System.out.println("The decrypted plaintext for ciphertext nr. 2 is: " + vigenereCipher.decrypt(ctS, kCS));
    }
}
