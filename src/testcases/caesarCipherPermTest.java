package testcases;
import ciphers.caesarCipherWithPermutation;

import java.util.Scanner;

public class caesarCipherPermTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input the text of the message to be encrypted: ");
        String messageIn_E = in.nextLine();
        System.out.println("Input the substitution key of the message to be encrypted: ");
        int substKeyIn_E = in.nextInt();
        Scanner inP = new Scanner(System.in);
        System.out.println("Input the permutation of the message to be encrypted: ");
        String permutKeyIn_E = inP.nextLine();
        Scanner inD = new Scanner(System.in);
        System.out.println("Input the text of the message to be decrypted: ");
        String messageIn_D = inD.nextLine();
        System.out.println("Input the substitution key of the message to be decrypted: ");
        int substKeyIn_D = inD.nextInt();
        System.out.println("Input the permutation of the message to be decrypted: ");
        Scanner inPD = new Scanner(System.in);
        String permutKeyIn_D = inPD.nextLine();
        System.out.println("The encrypted message is: " + caesarCipherWithPermutation.encrypt(messageIn_E, substKeyIn_E, permutKeyIn_E) + "\n");
        System.out.println("The decrypted message is: " + caesarCipherWithPermutation.decrypt(messageIn_D, substKeyIn_D, permutKeyIn_D));
    }
}
