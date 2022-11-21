<p><strong style="color: black;">Intro to Cryptography. Classical ciphers. Caesar cipher.</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Course: Cryptography &amp; Security</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Task: Laboratory work nr. 1</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Author: Vladimir Russu</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">Overview</strong></p><p><br></p><p>This report contains the description of the laboratory work nr.&nbsp;1&nbsp;for the Cryptography&nbsp;&amp;&nbsp;Security course.</p><p>In this laboratory work there are 4 cipher algorithms implemented: Caesar cipher with permutation, Caesar cipher with no permutation, Vigenere cipher and Playfair cipher. These ciphers are stored in the “cipher” package. All the cipher classes are the implementations of corresponding interfaces, which can be found in the “interfaces” package. The interfaces have two initialized methods – one for decryption and another for encryption. The only difference between particular interfaces is made by passing slightly different parameters to the methods for encryption and decryption (for example, there are ciphers which require additional parameters such as permutation key for encryption and decryption). Also, there is the “testcases” package in which there test cases for each of the cipher algorithms.</p><p><br></p><p><strong><em>Caesar Cipher with No Permutations</em></strong></p><p>In the "caesarCipherNoPermutations" class the Caesar cipher algorithm for encryption and decryption without using permutations is implemented. In this class there are 2 methods for cryptograhic operations: one is for encryption and another one is for decryption. The method for encryption takes as parameters the String value of the plaintext and the integer value of the substitution key. The plaintext message is then being converted into a char array. Then each letter of that char array is being encrypted. That is done in the following way: first the position of each char in the English alphabet is added up with the substitution key and then the modulo 26 of that sum is being calculated. The result of that calculation represents the position of the encrypted char in the English alphabet. In that way the ciphertext is being obtained. Below is the code of the method for encryption:</p><pre class="ql-syntax" spellcheck="false">public static String encrypt(String plaintext, int key) {

&nbsp;&nbsp;&nbsp; String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

&nbsp;&nbsp;&nbsp; char[] inputArrayChar = plaintext.toLowerCase().toCharArray();

&nbsp;&nbsp;&nbsp; String encrMessage = "";

&nbsp;&nbsp;&nbsp; for (int k=0; k&lt;inputArrayChar.length; k++)

&nbsp;&nbsp;&nbsp; {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int encrCharPos = (alphabetEng.indexOf(inputArrayChar[k]) + key) % 26;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; char encrChar = alphabetEng.charAt(encrCharPos);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; encrMessage+=encrChar;

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return encrMessage;

}
</pre><p>The method for decryption takes as parameters the String value of the ciphertext and the integer value of the substitution key. The ciphertext message is then being converted into a char array. Then each letter of that char array is being decrypted. That is done in the following way: first, the substitution key is being subtracted from the position of each char of the ciphertext in the English alphabet, then 26 is being added to that difference if the corresponding ciphertext char index is smaller than the substitution key and finally the modulo 26 of the result is being calculated. The result of that calculation represents the position of the encrypted char in the English alphabet. In that way the plaintext is being obtained. Below is the code of the method for decryption:</p><pre class="ql-syntax" spellcheck="false">public static String decrypt(String ciphertext, int key) {

&nbsp;&nbsp;&nbsp; String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

&nbsp;&nbsp;&nbsp; char[] inputArrayChar = ciphertext.toLowerCase().toCharArray();

&nbsp;&nbsp;&nbsp; String decrMessage = "";

&nbsp;&nbsp;&nbsp; for (int k=0; k&lt;inputArrayChar.length; k++)

&nbsp;&nbsp;&nbsp; {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if(alphabetEng.indexOf(inputArrayChar[k]) &gt;= key) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int decrCharPos = (alphabetEng.indexOf(inputArrayChar[k]) - key) % 26;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; char decrChar = alphabetEng.charAt(decrCharPos);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; decrMessage += decrChar;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if(alphabetEng.indexOf(inputArrayChar[k]) &lt; key) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int decrCharPos = (26 + alphabetEng.indexOf(inputArrayChar[k]) - key) % 26;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; char decrChar = alphabetEng.charAt(decrCharPos);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; decrMessage += decrChar;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return decrMessage;

}
</pre><p><br></p><p><strong><em>Caesar Cipher with Permutation</em></strong></p><p>In the “caesarCipherWithPermutation” class there are 3 methods used for cryptographic operations: one for encryption, the second one for decryption and the last one for removing permutation chars in order to obtain the right modification of the alphabet for decryption and decryption operations. The methods for decryption and encryption in this class are almost the same as in the class for the Caesar cipher without permutations. The only difference is that for the encryption/decryption methods with permutation an additional parameter is added which is the permutation string. This string is further used to make the modified version of the alphabet which is then used to identify the substitute chars by their position. It means that instead the standard alphabet char index in the formula for encryption the char index of the modified alphabet is used. That modified alphabet is obtained by concatenating the permutation key with all the other letters of the alphabet except those already used in the permutation key. In order to do that concatenating we should first get rid of all the permutation chars in the standard alphabet – that is obtained be the following method:</p><pre class="ql-syntax" spellcheck="false">// remove chars of a string from another string

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
</pre><p>This method takes as parameters 2 strings and eliminates from the first string all the chars which are present in the second string. In our case the permutation key chars are being eliminated from the standard English alphabet. This method is further called in the encryption and decryption methods in order to obtain the modified version of the alphabet:</p><pre class="ql-syntax" spellcheck="false">String permToL = permutation.toLowerCase();

// new alphabet after applying permutation key

String newAlph = permToL + removeCharsOfPermutation(alphabetEng, permToL);
</pre><p>Further the encryption and decryption are performed in the same way as in the Caesar cipher with no permutation, but the difference is that the reference alphabet for calculating each char position is the modified one instead of the standard English one.</p><p><br></p><p><strong><em>Vigenere Cipher </em></strong></p><p>In the “vigenereCipher” class there are 2 methods used for the cryptographic operations: one is for encryption and another is for decryption. In both those methods there are 2 parameters: the plaintext/ciphertext string and the keystream string. The encrypted/decrypted texts are being received by implementing the Vigenere cipher algorithm: </p><pre class="ql-syntax" spellcheck="false">Ciphertexti = (Plaintexti + Keystreami) mod 26 – for encryption
&nbsp;
Plaintexti = (Ciphertexti - Keystreami + 26) mod 26 – for decryption
</pre><p>The ciphertext is obtained in the following way: first, the position index of each consecutive char of the plaintext in the English alphabet is being added up with the position index of each consecutive char of the keystream in English alphabet, and then the modulo 26 of that sum is being calculated. The obtained result indicates the position of the corresponding char of ciphertext in the English alphabet. Below is the code of the encryption method:</p><pre class="ql-syntax" spellcheck="false">public static String encrypt(String plaintext, String keystream) {

&nbsp;&nbsp;&nbsp; String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

&nbsp;&nbsp;&nbsp; char[] pTextArrayChar = plaintext.toLowerCase().toCharArray();

&nbsp;&nbsp;&nbsp; char[] keyArrayChar = keystream.toLowerCase().toCharArray();

&nbsp;&nbsp;&nbsp; String encrMessage = "";

&nbsp;&nbsp;&nbsp; for (int k=0; k&lt;pTextArrayChar.length &amp;&amp; k&lt;keyArrayChar.length; k++)

&nbsp;&nbsp;&nbsp; {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int encrCharPos = (alphabetEng.indexOf(pTextArrayChar[k]) + alphabetEng.indexOf(keyArrayChar[k])) % 26;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; char encrChar = alphabetEng.charAt(encrCharPos);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; encrMessage+=encrChar;

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return encrMessage;

}
</pre><p>The plaintext is obtained in the following way: first, the position index of each consecutive char of the keystream in English alphabet is being subtracted from the position index of each consecutive char of the ciphertext in the English alphabet, then 26 is being added up to that difference and then the modulo 26 of the result of previous operation is being calculated. The obtained result indicates the position of the corresponding char of plaintext in the English alphabet. Below is the code of the decryption method:</p><pre class="ql-syntax" spellcheck="false">public static String decrypt(String ciphertext, String keystream) {

&nbsp;&nbsp;&nbsp; String alphabetEng = "abcdefghijklmnopqrstuvwxyz";

&nbsp;&nbsp;&nbsp; char[] cTextArrayChar = ciphertext.toLowerCase().toCharArray();

&nbsp;&nbsp;&nbsp; char[] keyArrayChar = keystream.toLowerCase().toCharArray();

&nbsp;&nbsp;&nbsp; String decrMessage = "";

&nbsp;&nbsp;&nbsp; for (int k=0; k&lt;cTextArrayChar.length &amp;&amp; k&lt;keyArrayChar.length; k++)

&nbsp;&nbsp;&nbsp; {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int decrCharPos = (alphabetEng.indexOf(cTextArrayChar[k]) - alphabetEng.indexOf(keyArrayChar[k]) + 26) % 26;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; char decrChar = alphabetEng.charAt(decrCharPos);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; decrMessage+=decrChar;

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return decrMessage;

}&nbsp;
</pre><p><br></p><p><strong><em>Playfair Cipher </em></strong></p><p>In the “playfairCipher” class there are 6 methods: “pairRC”, “duplicateElim”, “whiteSpaceElim”, “matrixCr”, “encrypt” and “decrypt”. In the “pairRC” method the corresponding row and column position in the 5*5 matrix is being identified in order to obtain a pair for encrypting/decrypting a particular char:</p><pre class="ql-syntax" spellcheck="false">static int[] pairRC(char row, char col, char pos[][])

{

&nbsp;&nbsp;&nbsp; int[] arr = new int[4];

&nbsp;&nbsp;&nbsp; if (row == 'c')

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; row = 'r';

&nbsp;&nbsp;&nbsp; else if (col == 'c')

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; col = 'r';

&nbsp;&nbsp;&nbsp; for (int r = 0; r &lt; 5; r++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int c = 0; c &lt; 5; c++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (pos[r][c] == row) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; arr[0] = r;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; arr[1] = c;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; else if (pos[r][c] == col) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; arr[2] = r;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; arr[3] = c;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; if (arr[0] == arr[2]) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; arr[1] += 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; arr[3] += 1;

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; else if (arr[1] == arr[3]) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; arr[0] += 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; arr[2] += 1;

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; for (int r = 0; r &lt; 4; r++)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; arr[r] %= 5;

&nbsp;&nbsp;&nbsp; return arr;

}
</pre><p>The “duplicateElim” method operates in order to eliminate the duplicates from a string, which is then applied to get rid of the duplicates in the key string. Below is that method:</p><pre class="ql-syntax" spellcheck="false">static String duplicateElim(String keyStr)

{

&nbsp;&nbsp;&nbsp; int id = 0;

&nbsp;&nbsp;&nbsp; char c[] = keyStr.toCharArray();

&nbsp;&nbsp;&nbsp; for (int i = 0; i &lt; keyStr.length(); i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int j;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (j = 0; j &lt; i; j++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (c[i] == c[j])

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; break;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (i == j)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; c[id++] = c[i];

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; keyStr = new String((Arrays.copyOf(c, id)));

&nbsp;&nbsp;&nbsp; return keyStr;

}
</pre><p>The “whiteSpaceElim” method operates in order to eliminate white spaces which occur after removing from a string the chars which are present in another string. This method is further applied in order to get rid of the white spaces after removing the chars of the keystream from the English alphabet:</p><pre class="ql-syntax" spellcheck="false">static String whiteSpaceElim(char[] chArrK, String kS)

{

&nbsp;&nbsp;&nbsp; char[] c = kS.toCharArray();

&nbsp;&nbsp;&nbsp; for (int l = 0; l &lt; c.length; l++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int m = 0; m &lt; chArrK.length; m++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (c[l] == chArrK[m])

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; c[l] = ' ';

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; String noWhSp = new String(c);

&nbsp;&nbsp;&nbsp; String noWhSpR = noWhSp.replaceAll(" ", "");

&nbsp;&nbsp;&nbsp; return noWhSpR;

}
</pre><p>The “matrixCr” method operates in order to get the ciphertext from plaintext and the keystream matrix (or plaintext from ciphertext and the keystream matrix):</p><pre class="ql-syntax" spellcheck="false">static String encrypt(String plaintext, char matrix[][])

{

&nbsp;&nbsp;&nbsp; char chPt[] = plaintext.toCharArray();

&nbsp;&nbsp;&nbsp; int a[] = new int[4];

&nbsp;&nbsp;&nbsp; for (int k = 0; k &lt; plaintext.length(); k += 2) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (k &lt; plaintext.length() - 1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; a = pairRC(plaintext.charAt(k), plaintext.charAt(k + 1),

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; matrix);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;if (a[0] == a[2]) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; chPt[k] = matrix[a[0]][a[1]];

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; chPt[k + 1] = matrix[a[0]][a[3]];

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; else if (a[1] == a[3]) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; chPt[k] = matrix[a[0]][a[1]];

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; chPt[k + 1] = matrix[a[2]][a[1]];

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; else {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; chPt[k] = matrix[a[0]][a[3]];

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; chPt[k + 1] = matrix[a[2]][a[1]];

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; String ciphertext = new String(chPt);

&nbsp;&nbsp;&nbsp; return ciphertext;

}
</pre><p>Finally, the above methods are being called in the encryption and decryption methods which take as parameters only plaintext/ciphertext and keystream. In the encryption and decryption methods the keystream matrix is being formed out of the keystream and the English alphabet:</p><pre class="ql-syntax" spellcheck="false">String keySNoDupl = duplicateElim(keystream);

char[] chKstr = keySNoDupl.toCharArray();

String alph = "abcdefghiklmnopqrstuvwxyz";

alph = whiteSpaceElim(chKstr, alph);

char[] chArr = alph.toCharArray();

char[][] matrix = new char[5][5];

int stIndex0 = 0, keyIndex0 = 0;
&nbsp;
for (int k = 0; k &lt; 5; k++) {

&nbsp;&nbsp;&nbsp; for (int l = 0; l &lt; 5; l++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (keyIndex0 &lt; keySNoDupl.length())

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; matrix[k][l] = chKstr[keyIndex0++];

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; else

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; matrix[k][l] = chArr[stIndex0++];

&nbsp;&nbsp;&nbsp; }

}
</pre><p>And then the ciphertext/plaintext is being obtained by calling the “matrixCr” method with parameters being plaintext/ciphertext and the matrix obtained in previous steps:</p><pre class="ql-syntax" spellcheck="false">String ciphertext = matrixCr(plaintext, matrix);
</pre><p><br></p>