package ciphers.hash;
import ciphers.streamCipherNoShift;

import java.util.Random;
import java.util.Scanner;

public class storingPasswords {
    public static void main(String[] args) {
        Scanner newUser = new Scanner(System.in);
        System.out.println("Input your ID: ");
        int id = Integer.parseInt(newUser.nextLine());
        System.out.println("Input your name: ");
        String name = newUser.nextLine();
        System.out.println("Input your password: ");
        String password = newUser.nextLine();
        String hashText = sha256.sha(password);
        String hashTextToBin = hexToBin.convert(hashText);
        String keystream = "";
        Random random = new Random();
        for (int i = 0; i < hashTextToBin.length(); i++) {
            int bin = random.nextInt(2);
            keystream += String.valueOf(bin);
        }
        String encrHashTextBin = streamCipherNoShift.encrypt(hashTextToBin, keystream);
        String decrDigSign = streamCipherNoShift.decrypt(encrHashTextBin, keystream);
        if (hashTextToBin.equals(decrDigSign) == true) {
            System.out.println("Digital signature check performed successfully.");
        } else System.out.println("Digital signature check failed.");

        databaseConIns DBInsert = new databaseConIns();
        DBInsert.insert(id, name, encrHashTextBin);
    }
}
