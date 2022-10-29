<p><strong style="color: black;">Intro to Cryptography. Classical ciphers. Caesar cipher.</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Course: Cryptography &amp; Security</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Task: Laboratory work nr. 2</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Author: Vladimir Russu</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">Overview</strong></p><p><br></p><p>This report contains the description of the laboratory work nr.&nbsp;2&nbsp;for the Cryptography&nbsp;&amp;&nbsp;Security course.</p><p>In this laboratory work there are 3 cipher algorithms implemented: Stream cipher without shifts, Stream cipher with shifts and Block cipher. These ciphers are stored in the “cipher” package. All the cipher classes are the implementations of corresponding interfaces, which can be found in the “interfaces” package. The interfaces have two initialized methods – one for decryption and another for encryption. Also, there is the “testcases” package in which there are test cases for each of the cipher algorithms.</p><p><br></p><p><strong><em>Stream Cipher with No Shift</em></strong></p><p>In the “streamCipherNoShift” class the stream cipher algorithm without shifts is implemented. There are 4 methods: “stringToIntArray”, “intArrayToString”, “encrypt” and “decrypt”. The “stringToIntArray” converts a string into an integer array, which is further required to separate from each other the bits from the input string in order to encrypt/decrypt each bit. The “intArrayToString” method converts an integer array into string in order to get a clearly formatted output after performing encryption/decryption:</p><pre class="ql-syntax" spellcheck="false">static int[] stringToIntArray(String string) {

&nbsp;&nbsp;&nbsp; int l = string.length();

&nbsp;&nbsp;&nbsp; char[] charArr = new char[l];

&nbsp;&nbsp;&nbsp; for (int i = 0; i &lt; l; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; charArr[i] = string.charAt(i);

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; int [] intArr = new int [l];

&nbsp;&nbsp;&nbsp; for(int i=0; i&lt;l; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; intArr[i] = Integer.parseInt(String.valueOf(charArr[i]));

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return intArr;

}



static String intArrayToString(int[] intArr) {

&nbsp;&nbsp;&nbsp; StringBuffer sBf = new StringBuffer();

&nbsp;&nbsp;&nbsp; String strSep = " ";

&nbsp;&nbsp;&nbsp; for(int i=0; i &lt; intArr.length; i++){

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; sBf.append(intArr[i]);

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; String res = sBf.toString();

&nbsp;&nbsp;&nbsp; return res;

}
Those methods are then being used in the methods for encryption and decryption, which are done by performing XOR operations on the consecutive bits of the plaintext text and the keystream:

&nbsp;
</pre><p>Those methods are then being used in the methods for encryption and decryption, which are done by performing XOR operations on the consecutive bits of the plaintext/ciphertext and the keystream:</p><pre class="ql-syntax" spellcheck="false">public static String encrypt(String plaintext, String keystream) {

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

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i]==1 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; String ciphertext = intArrayToString(ciphertextInt);

&nbsp;&nbsp;&nbsp; return ciphertext;

}


public static String decrypt(String ciphertext, String keystream) {

&nbsp;&nbsp;&nbsp; int[] ciphertextInt = stringToIntArray(ciphertext);

&nbsp;&nbsp;&nbsp; int[] keystreamInt = stringToIntArray(keystream);

&nbsp;&nbsp;&nbsp; int[] plaintextInt = new int[ciphertextInt.length];

&nbsp;&nbsp;&nbsp; // perform XOR operations on corresponding bits of ciphertext and keystream in order to obtain plaintext

&nbsp;&nbsp;&nbsp; for (int i = 0; i&lt;ciphertextInt.length; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (ciphertextInt[i]==0 &amp;&amp; keystreamInt[i]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextInt[i] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (ciphertextInt[i]==0 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextInt[i] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (ciphertextInt[i]==1 &amp;&amp; keystreamInt[i]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextInt[i] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (ciphertextInt[i]==1 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextInt[i] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; String plaintext = intArrayToString(plaintextInt);

&nbsp;&nbsp;&nbsp; return plaintext;

}
</pre><p><br></p><p><strong><em>Stream Cipher with Shift</em></strong></p><p>In the “streamCipherShift” class the stream cipher algorithm with shifts is implemented. There are 4 methods: “stringToIntArray”, “intArrayToString”, “encrypt” and “decrypt”. The “stringToIntArray” converts a string into an integer array, which is further required to separate from each other the bits from the input string in order to encrypt/decrypt each bit. The “intArrayToString” method converts an integer array into string in order to get a clearly formatted output after performing encryption/decryption:</p><pre class="ql-syntax" spellcheck="false">static int[] stringToIntArray(String string) {

&nbsp;&nbsp;&nbsp; int l = string.length();

&nbsp;&nbsp;&nbsp; char[] charArr = new char[l];

&nbsp;&nbsp;&nbsp; for (int i = 0; i &lt; l; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; charArr[i] = string.charAt(i);

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; int [] intArr = new int [l];

&nbsp;&nbsp;&nbsp; for(int i=0; i&lt;l; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; intArr[i] = Integer.parseInt(String.valueOf(charArr[i]));

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return intArr;

}



static String intArrayToString(int[] intArr) {

&nbsp;&nbsp;&nbsp; StringBuffer sBf = new StringBuffer();

&nbsp;&nbsp;&nbsp; String strSep = " ";

&nbsp;&nbsp;&nbsp; for(int i=0; i &lt; intArr.length; i++){

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; sBf.append(intArr[i]);

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; String res = sBf.toString();

&nbsp;&nbsp;&nbsp; return res;

}
</pre><p>Then there are encryption and decryption methods, which: first shift plaintext/ciphertext to the right and XOR those bits in the ciphertext/plaintext which are defined to take part in XORing (in this case those are bits on positions 1 and 6) in order to obtain keystream; and then XOR the obtained keystream and plaintext/ciphertext in order to get ciphertext/plaintext. Below is the code of the encryption method:</p><pre class="ql-syntax" spellcheck="false">static String encrypt(String plaintext) {

&nbsp;&nbsp;&nbsp; int[] plaintextInt = stringToIntArray(plaintext);

&nbsp;&nbsp;&nbsp; int l = 8;

&nbsp;&nbsp;&nbsp; // assign the position of bits which should be XORed

&nbsp;&nbsp;&nbsp; int s1 = 1;

&nbsp;&nbsp;&nbsp; int s2 = 6;

&nbsp;&nbsp;&nbsp; // make copy of plaintext integer array for modifications

&nbsp;&nbsp;&nbsp; int[] plaintextIntCopy = new int[l];

&nbsp;&nbsp;&nbsp; for (int i=0; i&lt;l; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[i] = plaintextInt[i];

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; int[] keystreamInt = new int[8];

&nbsp;&nbsp;&nbsp; // assign value of the last bit of initial plaintext to first bit of keystream

&nbsp;&nbsp;&nbsp; keystreamInt[0] = plaintextIntCopy[7];

&nbsp;&nbsp;&nbsp; // consecutively shift bits of plaintext copy array to right and assign the first bit of each modified plaintext iteration to XORed value of 1st and 6th bit of the previous iteration

&nbsp;&nbsp;&nbsp; for (int i=1; i&lt;l; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int j = 1; j &lt; l; j++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextIntCopy[s1] == 0 &amp;&amp; plaintextIntCopy[s2] == 0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[0] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextIntCopy[s1] == 0 &amp;&amp; plaintextIntCopy[s2] == 1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[0] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextIntCopy[s1] == 1 &amp;&amp; plaintextIntCopy[s2] == 0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[0] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextIntCopy[s1] == 1 &amp;&amp; plaintextIntCopy[s2] == 1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;plaintextIntCopy[0] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; plaintextIntCopy[j] = plaintextIntCopy[j - 1];

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; keystreamInt[i] = plaintextInt[7];

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; // perform XOR operations on corresponding bits of plaintext and keystream in order to obtain ciphertext

&nbsp;&nbsp;&nbsp; int[] ciphertextInt = new int[8];

&nbsp;&nbsp;&nbsp; for (int i=1; i&lt;l; i++) {

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
</pre><p>&nbsp;</p><p><br></p><p><strong><em>Block Cipher </em></strong></p><p>In the “blockCipher” class the block cipher algorithm is implemented. There are 4 methods: “stringToIntArray”, “intArrayToString”, “encrypt” and “decrypt”. The “stringToIntArray” converts a string into an integer array, which is further required to separate from each other the bits from the input string in order to encrypt/decrypt each bit. The “intArrayToString” method converts an integer array into string in order to get a clearly formatted output after performing encryption/decryption:</p><pre class="ql-syntax" spellcheck="false">static int[] stringToIntArray(String string) {

&nbsp;&nbsp;&nbsp; int l = string.length();

&nbsp;&nbsp;&nbsp; char[] charArr = new char[l];

&nbsp;&nbsp;&nbsp; for (int i = 0; i &lt; l; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; charArr[i] = string.charAt(i);

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; int [] intArr = new int [l];

&nbsp;&nbsp;&nbsp; for(int i=0; i&lt;l; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; intArr[i] = Integer.parseInt(String.valueOf(charArr[i]));

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; return intArr;

}



static String intArrayToString(int[] intArr) {

&nbsp;&nbsp;&nbsp; StringBuffer sBf = new StringBuffer();

&nbsp;&nbsp;&nbsp; String strSep = " ";

&nbsp;&nbsp;&nbsp; for(int i=0; i &lt; intArr.length; i++){

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; sBf.append(intArr[i]);

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; String res = sBf.toString();

&nbsp;&nbsp;&nbsp; return res;

}
</pre><p>Then there are methods performs encryption and decryption. Keystream represents a block that is consecutively used to XOR the correspondent bits from the plaintext. First, we need to find out the number of iterations of the keystream block necessary to encrypt/decrypt the plaintext, which is being achieved by dividing the length of the plaintext/ciphertext by the length of the keystream. The result of that division is further used in the <em>for() </em>loop to initiate jump to the next encryption/decryption block after the previous block has been encrypted/decrypted by performing XOR operations. Below is the code of the encryption method:</p><pre class="ql-syntax" spellcheck="false">public static String encrypt(String plaintext, String keystream) {

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

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+j]==0 &amp;&amp; keystreamInt[j]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+j] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+j]==1 &amp;&amp; keystreamInt[j]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+j] = 1;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;}

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+j]==1 &amp;&amp; keystreamInt[j]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+j] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; // encrypting last bits which are left after consecutive usage of the full length keystream block

&nbsp;&nbsp;&nbsp; if (rem&gt;0) {

&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;for (int i=0; i&lt;rem; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+nOfBlocks]==0 &amp;&amp; keystreamInt[i]==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ciphertextInt[i*k+nOfBlocks] = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (plaintextInt[i*k+nOfBlocks]==0 &amp;&amp; keystreamInt[i]==1) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;ciphertextInt[i*k+nOfBlocks] = 1;

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
</pre>