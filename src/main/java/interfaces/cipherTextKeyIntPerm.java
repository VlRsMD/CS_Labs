package interfaces;

public interface cipherTextKeyIntPerm extends cipherTextKeyInt {
    static String encrypt(String plaintext, int key, String permutation) {
        return null;
    }

    static String decrypt(String ciphertext, int key, String permutation) {
        return null;
    }
}
