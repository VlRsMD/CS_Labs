package testcases;
import ciphers.caesarCipherNoPermutations;

import java.util.Scanner;

public class caesarCipherNoPermTest {
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
        System.out.println("The encrypted message is: " + caesarCipherNoPermutations.encrypt(messageIn_E, substKeyIn_E) + "\n");
        System.out.println("The decrypted message is: " + caesarCipherNoPermutations.decrypt(messageIn_D, substKeyIn_D));
    }
}
