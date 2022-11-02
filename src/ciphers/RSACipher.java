package ciphers;

import interfaces.AssymCipher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RSACipher implements AssymCipher {
    public static int primeNumGen () {
        List<Integer> primeNumList = new ArrayList<Integer>();
        for (int p = 50; p <= 300; p++) {
            int count = 0;
            for (int i = 1; i <= p; i++) {
                if (p % i == 0) {
                    count++;
                }
            }
            if (count == 2)
                primeNumList.add(p);
        }
        int[] primeNumArr = primeNumList.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        int rnd = new Random().nextInt(primeNumArr.length);
        int primeNum = primeNumArr[rnd];
        return primeNum;
    }

    public static int[] keyGen(){
        int p = primeNumGen();
        int q = primeNumGen();
        int n = p*q;
        int z = (p-1)*(q-1);
        int e = (int) ((Math.random() * ((z-1) - 2)) + 2);
        int k = (int) ((Math.random())*10 + 1);
        int d = z*k + 1;
        int[] keyPar = new int[3];
        keyPar[0] = n;
        keyPar[1] = e;
        keyPar[2] = d;
        return keyPar;
    }

    public static int encrypt(int m) {
        int e = keyGen()[1];
        int n = keyGen()[0];
        double powE = Math.pow(m, e);
        int powEI = (int) powE;
        int ciphertext = powEI % n;
        return ciphertext;
    }

    public static int decrypt (int c) {
        int d = keyGen()[2];
        int n = keyGen()[0];
        double powD = Math.pow(c, d);
        int powDI = (int) powD;
        int plaintext = powDI % n;
        return plaintext;
    }
}
