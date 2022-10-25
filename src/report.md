<p><strong style="color: black;">Intro to Cryptography. Classical ciphers. Caesar cipher.</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Course: Cryptography &amp; Security</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Task: Laboratory work nr. 1</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Author: Vladimir Russu</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">Overview</strong></p><p><br></p><p>This project contains laboratory works nr.&nbsp;1 and nr.&nbsp;2&nbsp;for the Cryptography&nbsp;&amp;&nbsp;Security course.</p><p>In&nbsp;"caesarCipherNoPermutation"&nbsp;class encryption and decryption&nbsp;according to Caesar algorithm with no permutations are done.&nbsp;In&nbsp;"caesarCipherWithPermutation"&nbsp;class encryption and decryption according to Caesar algorithm&nbsp;with permutations are done.&nbsp;Next the Vigenere algorithm and the Playfair algorithms are implemented.&nbsp;Next Stream cipher and Block cipher are implemented.</p><p><strong><em>Caesar Cipher with No Permutations</em></strong></p><pre class="ql-syntax" spellcheck="false">In the "caesarCipherNoPermutations" class there are two methods for cryptograhic operations: 

one is for encryption and another is for decryption. The method for decryption is the following:
// encrypting text

public static String caesarCipherEncr(String message, int substitutionKey) {

&nbsp;&nbsp;&nbsp; char[] inputArrayChar = message.toLowerCase().toCharArray();

&nbsp;&nbsp;&nbsp; String encrMessage = "";

&nbsp;&nbsp;&nbsp; for (int k=0; k&lt;inputArrayChar.length; k++)

&nbsp;&nbsp;&nbsp; {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int encrCharPos = (alphabetEng.indexOf(inputArrayChar[k]) + substitutionKey) % 26;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; char encrChar = alphabetEng.charAt(encrCharPos);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; encrMessage+=encrChar;

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return encrMessage;

}
</pre><p>This method takes as parameters the plaintext message and the integer value of the substitution key and returns a an encrypted ciphertext following the procedures of Caesar algorithm for encryption. </p><p>The method for decryption is the following:</p><pre class="ql-syntax" spellcheck="false">// decrypting text

public static String caesarCipherDecr(String message, int substitutionKey) {

&nbsp;&nbsp;&nbsp; char[] inputArrayChar = message.toLowerCase().toCharArray();

&nbsp;&nbsp;&nbsp; String decrMessage = "";

&nbsp;&nbsp;&nbsp; for (int k=0; k&lt;inputArrayChar.length; k++)

&nbsp; &nbsp;&nbsp;{

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if(alphabetEng.indexOf(inputArrayChar[k]) &gt;= substitutionKey) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int decrCharPos = (alphabetEng.indexOf(inputArrayChar[k]) - substitutionKey) % 26;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; char decrChar = alphabetEng.charAt(decrCharPos);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; decrMessage += decrChar;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if(alphabetEng.indexOf(inputArrayChar[k]) &lt; substitutionKey) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int decrCharPos = (26 + alphabetEng.indexOf(inputArrayChar[k]) - substitutionKey) % 26;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; char decrChar = alphabetEng.charAt(decrCharPos);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; decrMessage += decrChar;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return decrMessage;

}
</pre><p>This method uses as parameter the ciphertext message and the substitution key and returns the decrypted plaintext which is achieved following the Caesar algorithm for decryption.</p><p>Further these two methods: for encryption and decryption are called in the <em>main()</em> method of the class. Both plaintext to encrypt and ciphertext to decrypt are taken from the user input.</p><p><strong><em>Caesar Cipher with Permutation</em></strong></p><p>In the “caesarCipherWithPermutation” class there are 3 methods used for cryptographic operations: one for encryption, the second one for decryption and the last one for removing permutation chars in order to obtain the right modification of the alphabet for decryption and decryption operations. The methods for decryption and encryption in this class are very similar to those from the class for the Caesar cipher without permutations. The only difference is that for the encryption/decryption methods with permutation an additional parameter is added which is the permutation string. This string is further used to make the modified version of the alphabet which is then used to identify the substitute chars by their position. It means that instead the standard alphabet char index in the formula for encryption the char index of the modified alphabet is used. That modified alphabet is obtained by concatenating the permutation key with all the other letters of the alphabet except those already used in the permutation key. In order to do that concatenating we should first get rid of all the permutation chars in the standard alphabet – that is obtained be the following method:</p><pre class="ql-syntax" spellcheck="false">// remove chars of a string from another string

public static String removeCharsOfPermutation(String str1, String str2)

{

&nbsp;&nbsp;&nbsp; for (int index = 0; index &lt; str2.length();

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; index++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; char k = str2.charAt(index);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; while (str1.contains(k + "")) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int i = str1.indexOf(k);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; str1 = str1.replace((k + ""), "");

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return str1;

}
</pre><p>This method takes as parameters 2 strings and eliminates from the first string all the chars which are present in the second string. In our case the permutation key chars are being eliminated from the standard English alphabet. This method is further called in the encryption and decryption methods in order to obtain the modified version of the alphabet. Finally, the encryption and decryption methods are being called in the <em>main()</em> method inn order to get the output.</p><p><strong><em>Vigenere Cipher </em></strong></p><p>In the “vigenereCipher” class there are 2 methods used for the cryptographic operations: one is for encryption and another is for decryption. In both those methods there are 2 parameters: the plaintext/ciphertext string and the keystream string. The encrypted/decrypted texts are being received by implementing the Vigenere cipher algorithm: </p><pre class="ql-syntax" spellcheck="false">Ciphertexti = (Plaintexti + Keystreami) mod 26 – for encryption
&nbsp;
Plaintexti = (Ciphertexti - Keystreami + 26) mod 26 – for decryption
&nbsp;
</pre><p><br></p><p><strong><em>Playfair Cipher </em></strong></p><pre class="ql-syntax" spellcheck="false">In the “playfairCipher” class there are 3 methods used for the cryptographic operations: one is for encryption and another is for decryption. In both those methods there are 2 parameters. There is the “pairRC” method to identify the row and column position in the 5*5 encryption matrix for encrypting the pair. Further this method is called in the “playfairEnc” method which does the encryption itself. There is also a method “duplicateElim” to eliminate the duplicates from the key string. And there is also a method “whiteSpaceElim” to eliminate white spaces which occur after removing from the standard alphabet the chars of the keystream. Finally those relevant methods are called in the main() method in order to get the output:
Scanner scan = new Scanner(System.in);

System.out.println("Input plaintext: ");

String pt = scan.nextLine();

System.out.println("Input keystream: ");

String keyS = scan.nextLine();

keyS = duplicateElim(keyS);

char[] chK = keyS.toCharArray();

String alph = "abcdefghiklmnopqrstuvwxyz";

alph = whiteSpaceElim(chK, alph);

char[] ch = alph.toCharArray();

char[][] matrix = new char[5][5];

int stIndex = 0, keyIndex = 0;

// fill the 5*5 matrix with keystream first and then with the modified alphabet without keystream chars

for (int k = 0; k &lt; 5; k++) {

&nbsp;&nbsp;&nbsp; for (int l = 0; l &lt; 5; l++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (keyIndex &lt; keyS.length())

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; matrix[k][l] = chK[keyIndex++];

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; else

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; matrix[k][l] = ch[stIndex++];

&nbsp;&nbsp;&nbsp; }

}

pt = playfairEnc(pt, matrix);

System.out.println("Ciphertext is: " + pt);
&nbsp;
&nbsp;
</pre><p><br></p><p><strong><em>Stream Cipher with No Shift</em></strong></p><pre class="ql-syntax" spellcheck="false">In the “streamCipherNoShift” class the stream cipher algorithm without shifts is implemented. There are 4 methods: the first 2 are being used in order to convert a bit string into an integer array and vice versa. Those methods are then being used in the method for encryption, which is done by performing XOR operations on the plaintext text and the keystream:
static String encryption(String plaintext, String keystream) {

&nbsp;&nbsp;&nbsp; int[] plaintextInt = stringToIntArray(plaintext);

&nbsp;&nbsp;&nbsp; int[] keystreamInt = stringToIntArray(keystream);

&nbsp;&nbsp;&nbsp; int[] ciphertextInt = new int[plaintextInt.length];

&nbsp;&nbsp;&nbsp; // perform XOR operations on corresponding bits of plaintext and keystream in order to obtain ciphertext

&nbsp;&nbsp;&nbsp; for (int i = 0; i&lt;plaintextInt.length; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i]==0 &amp;&amp; keystreamInt[i]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i]==0 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i]==1 &amp;&amp; keystreamInt[i]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i]==1 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; String ciphertext = intArrayToString(ciphertextInt);

&nbsp;&nbsp;&nbsp; return ciphertext;

}
The same logic is then implemented for decryption with only difference that the first parameter for the decryption method is not the plaintext but ciphertext.
</pre><p><br></p><p><strong><em>Stream Cipher with Shift</em></strong></p><p>In the “streamCipherWithShift” class the stream cipher algorithm with shifts is implemented. Like in the previous stream cipher class in this class there are first 2 methods to convert a bit string into an integer array and vice versa. Then there are encryption and decryption methods, which use shifting plaintext/ciphertext to the right and XORing correspondent bits in ciphertext/plaintext and keystream. First the keystream is generated by shifting and XORing the plaintext depending on the index of bits which take part in XOR operations:</p><pre class="ql-syntax" spellcheck="false">int[] plaintextInt = stringToIntArray(plaintext);

int l = 8;

// assign the position of bits which should be XORed

int s1 = 1;

int s2 = 6;

// make copy of plaintext integer array for modifications

int[] plaintextIntCopy = new int[l];

for (int i=0; i&lt;l; i++) {

&nbsp;&nbsp;&nbsp; plaintextIntCopy[i] = plaintextInt[i];

}

int[] keystreamInt = new int[8];

// assign value of the last bit of initial plaintext to first bit of keystream

keystreamInt[0] = plaintextIntCopy[7];

// consecutively shift bits of plaintext copy array to right and assign the first bit of each modified plaintext iteration to XORed value of 1st and 6th bit of the previous iteration

for (int i=1; i&lt;l; i++) {

&nbsp;&nbsp;&nbsp; for (int j = 1; j &lt; l; j++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextIntCopy[s1] == 0 &amp;&amp; plaintextIntCopy[s2] == 0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[0] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextIntCopy[s1] == 0 &amp;&amp; plaintextIntCopy[s2] == 1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[0] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextIntCopy[s1] == 1 &amp;&amp; plaintextIntCopy[s2] == 0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[0] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextIntCopy[s1] == 1 &amp;&amp; plaintextIntCopy[s2] == 1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[0] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[j] = plaintextIntCopy[j - 1];

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; keystreamInt[i] = plaintextInt[7];

}
</pre><p>Then for the encryption the ciphertext is obtained by XORing the plaintext and the received keystream:</p><pre class="ql-syntax" spellcheck="false">// perform XOR operations on corresponding bits of plaintext and keystream in order to obtain ciphertext

int[] ciphertextInt = new int[8];

for (int i=1; i&lt;l; i++) {

&nbsp;&nbsp;&nbsp; if (plaintextInt[i]==0 &amp;&amp; keystreamInt[i]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i] = 0;

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; if (plaintextInt[i]==0 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i] = 1;

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; if (plaintextInt[i]==1 &amp;&amp; keystreamInt[i]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i] = 1;

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; if (plaintextInt[i]==1 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i] = 0;

&nbsp;&nbsp;&nbsp; }

}

String ciphertext = intArrayToString(ciphertextInt);

return ciphertext;
</pre><p>&nbsp;</p><p><br></p><p><strong><em>Block Cipher </em></strong></p><p>In the “blockCipher” class the block cipher algorithm is implemented. As in the previous classes for stream ciphers in the block cipher class first there are 2 methods to convert a bit string to an integer array and vice versa. Then there is a method which performs encryption. Keystream represents a block that is consecutively used to XOR the correspondent bits from the plaintext. First we need to find out the number of iterations of the keystream block necessary to encrypt the plaintext, this is achieved just by dividing the length of the plaintext by the length of the keystream. The result of this division is further used in the <em>for() </em>loop to initiate jump to the next encryption block after the previous block has been encrypted by performing XOR operations. The whole code of the encryption method looks as following:</p><pre class="ql-syntax" spellcheck="false">static String encrypt(String plaintext, String keystream) {

&nbsp;&nbsp;&nbsp; int[] plaintextInt = stringToIntArray(plaintext);

&nbsp;&nbsp;&nbsp; int[] keystreamInt = stringToIntArray(keystream);

&nbsp;&nbsp;&nbsp; int[] ciphertextInt = new int[plaintextInt.length];

&nbsp;&nbsp;&nbsp; int k = keystreamInt.length;

&nbsp;&nbsp;&nbsp; // calculating the number&nbsp; of block usages

&nbsp;&nbsp;&nbsp; int nOfBlocks = plaintextInt.length / k;

&nbsp;&nbsp;&nbsp; // calculating the remainder of dividing the plaintext length by the keystream length

&nbsp;&nbsp;&nbsp; int rem = plaintextInt.length % k;

&nbsp;&nbsp;&nbsp; for (int i = 0; i&lt;nOfBlocks; i++)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int j=0; j&lt;k; j++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; // perform XOR operations

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+j]==0 &amp;&amp; keystreamInt[j]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+j] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;if (plaintextInt[i*k+j]==0 &amp;&amp; keystreamInt[j]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+j] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+j]==1 &amp;&amp; keystreamInt[j]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+j] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+j]==1 &amp;&amp; keystreamInt[j]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+j] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; // encrypting last bits which are left after consecutive usage of the full length keystream block

&nbsp;&nbsp;&nbsp; if (rem&gt;0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int i=0; i&lt;rem; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+nOfBlocks]==0 &amp;&amp; keystreamInt[i]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+nOfBlocks] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+nOfBlocks]==0 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+nOfBlocks] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+nOfBlocks]==1 &amp;&amp; keystreamInt[i]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+nOfBlocks] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+nOfBlocks]==1 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+nOfBlocks] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; String ciphertext = intArrayToString(ciphertextInt);

&nbsp;&nbsp;&nbsp; return ciphertext;

}
</pre><p><strong>&nbsp;</strong></p>