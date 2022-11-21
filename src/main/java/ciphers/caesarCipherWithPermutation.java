package ciphers;
import interfaces.cipherTextKeyIntPerm;

public class caesarCipherWithPermutation implements cipherTextKeyIntPerm {
    public static String encrypt(String plaintext, int key, String permutation) {
        String alphabetEng = "abcdefghijklmnopqrstuvwxyz";
        char[] inputArrayChar = plaintext.toLowerCase().toCharArray();
        String permToL = permutation.toLowerCase();
        // new alphabet after applying permutation key
        String newAlph = permToL + removeCharsOfPermutation(alphabetEng, permToL);
        String encrMessage = "";
        for (int k=0; k<inputArrayChar.length; k++)
        {
            int encrCharPos = (newAlph.indexOf(inputArrayChar[k]) + key) % 26;
            char encrChar = newAlph.charAt(encrCharPos);
            encrMessage+=encrChar;
        }
        return encrMessage;
    }

    public static String decrypt(String ciphertext, int key, String permutation) {
        String alphabetEng = "abcdefghijklmnopqrstuvwxyz";
        char[] inputArrayChar = ciphertext.toLowerCase().toCharArray();
        String permToL = permutation.toLowerCase();
        // new alphabet after applying permutation key
        String newAlph = permToL + removeCharsOfPermutation(alphabetEng, permToL);
        String decrMessage = "";
        for (int k=0; k<inputArrayChar.length; k++)
        {
            if(newAlph.indexOf(inputArrayChar[k]) >= key) {
                int decrCharPos = (newAlph.indexOf(inputArrayChar[k]) - key) % 26;
                char decrChar = newAlph.charAt(decrCharPos);
                decrMessage += decrChar;
            }
            if(permutation.indexOf(inputArrayChar[k]) < key) {
                int decrCharPos = (26 + newAlph.indexOf(inputArrayChar[k]) - key) % 26;
                char decrChar = newAlph.charAt(decrCharPos);
                decrMessage += decrChar;
            }
        }
        return decrMessage;
    }

    // remove chars of a string from another string
    public static String removeCharsOfPermutation(String str1, String str2)
    {
        for (int index = 0; index < str2.length();
             index++) {
            char k = str2.charAt(index);
            while (str1.contains(k + "")) {
                int i = str1.indexOf(k);
                str1 = str1.replace((k + ""), "");
            }
        }
        return str1;
    }
}
