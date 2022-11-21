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

    public static int[] gcd1Arr (int fi) {
        // creating the array of divisors of fi
        List<Integer> fiDivList = new ArrayList<Integer>();
        for (int i=2; i<=fi; i++){
            if (fi%i==0) {
                fiDivList.add(i);
            }
        }
        int[] fiDiv = fiDivList.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        List<Integer> allDivRList = new ArrayList<Integer>();
        for (int k=2; k<fi; k++)
            for (int d=2; d<k; d++)
                if (k%d == 0) {
                    for (int l=0; l<fiDiv.length; l++) {
                        if (d==fiDiv[l]) {
                            allDivRList.add(k);
                        }
                    }
                }
        int[] allDivR =allDivRList.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        List<Integer> gcd1List = new ArrayList<Integer>();
        for (int x=2; x<fi; x++) {
            int count = 0;
            for (int l=0; l<allDivR.length; l++) {
                if (x==l) {
                    count++;
                }
            }
            if (count==0) {
                gcd1List.add(x);
            }
        }
        int[] gcd1 =allDivRList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return gcd1;
    }

    public static int[] keyGen(){
        int p = primeNumGen();
        int q = primeNumGen();
        int n = p*q;
        int fi = (p-1)*(q-1);
        int rndE = new Random().nextInt(gcd1Arr(fi).length);
        int e = gcd1Arr(fi)[rndE];
        int k = (int) ((Math.random())*10 + 1);
        int d = fi*k + 1;
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
