<p><strong style="color: black;">Asymmetric ciphers</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Course: Cryptography &amp; Security</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Task: Laboratory work nr. 3</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Author: Vladimir Russu</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">Overview</strong></p><p><br></p><p>This report contains the description of the laboratory work nr.&nbsp;3&nbsp;for the Cryptography&nbsp;&amp;&nbsp;Security course.</p><p>In this laboratory work there is an asymmetric cipher being implemented – it is the RSA Cryptography algorithm. The corresponding class “RSACipher” from the “cipher” package implements the “AssymCipher” interface from the “interfaces” package. And the test case class “RSACipherTest” from the “testcases” package demonstrates how the encryption/decryption according to the RSA algorithm is actually being performed.</p><p><br></p><p><strong><em>RSA Algorithm implementation</em></strong></p><p>First, in order to get all the necessary numbers for encryption and decryption, the 2 keys need to be generated – one public for encryption, and another one private for decryption. That is done in the following way: first two prime numbers are taken: <em>p</em> and <em>q</em>. In the “RSACipher” class there is a method called “primeNumGen” which generates prime integer numbers within a specified range (here it is between 50 and 300). For each number from that range the remainders after divisions by all the numbers smaller or equal than that consecutive number are being checked and the count variable is being increased by 1 each time the remainder of division equals zero. Therefore, if after all the divisions for a particular number the count variable becomes 2 (meaning that that number can be divided without remainder only by 1 or by itself) – it means that that number is prime, and that number is being added to an integer list which is further converted into an integer array:</p><pre class="ql-syntax" spellcheck="false">public static int primeNumGen () {

&nbsp;&nbsp;&nbsp; List&lt;Integer&gt; primeNumList = new ArrayList&lt;Integer&gt;();

&nbsp;&nbsp;&nbsp; for (int p = 50; p &lt;= 300; p++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int count = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int i = 1; i &lt;= p; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (p % i == 0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;count++;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (count == 2)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; primeNumList.add(p);

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; int[] primeNumArr = primeNumList.stream()

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .mapToInt(Integer::intValue)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .toArray();



&nbsp;&nbsp;&nbsp; int rnd = new Random().nextInt(primeNumArr.length);

&nbsp;&nbsp;&nbsp; int primeNum = primeNumArr[rnd];

&nbsp;&nbsp;&nbsp; return primeNum;

}
</pre><p>Calling the method represented above, prime number <em>p</em> and <em>q</em> can be obtained. Then <em>n</em> is obtained as <em>n=p*q</em>. Further <em>z </em>is obtained which is <em>z=(p-1)*(q-1)</em>. Then <em>e</em>, which is a component for the public key, is obtained by taking a number in the range between <em>1</em> and <em>z</em>. Then <em>d</em> is being obtained as <em>d=z*k+1</em> (k being a random integer number within specified range). The pair <em>(n, e)</em> forms the public key, and the pair <em>(n, d)</em> forms the private one. The bellow method “keyGen” returns <em>n</em>, <em>e</em> and <em>d</em> as integers of an array, in order to get them further for encryption and decryption:</p><pre class="ql-syntax" spellcheck="false">public static int[] keyGen(){

&nbsp;&nbsp;&nbsp; int p = primeNumGen();

&nbsp;&nbsp;&nbsp; int q = primeNumGen();

&nbsp;&nbsp;&nbsp; int n = p*q;

&nbsp;&nbsp;&nbsp; int z = (p-1)*(q-1);

&nbsp;&nbsp;&nbsp; int e = (int) ((Math.random() * ((z-1) - 2)) + 2);

&nbsp;&nbsp;&nbsp; int k = (int) ((Math.random())*10 + 1);

&nbsp;&nbsp;&nbsp; int d = z*k + 1;

&nbsp;&nbsp;&nbsp; int[] keyPar = new int[3];

&nbsp;&nbsp;&nbsp; keyPar[0] = n;

&nbsp;&nbsp;&nbsp; keyPar[1] = e;

&nbsp;&nbsp;&nbsp; keyPar[2] = d;

&nbsp;&nbsp;&nbsp; return keyPar;

}
</pre><p>Then the encryption is being done according to the following formula: <em>ciphertext = (plaintext^e) mod n. </em>Plaintext is transmitted as an integer parameter to the “encrypt” method:</p><pre class="ql-syntax" spellcheck="false">public static int encrypt(int m) {

&nbsp;&nbsp;&nbsp; int e = keyGen()[1];

&nbsp;&nbsp;&nbsp; int n = keyGen()[0];

&nbsp;&nbsp;&nbsp; double powE = Math.pow(m, e);

&nbsp;&nbsp;&nbsp; int powEI = (int) powE;

&nbsp;&nbsp;&nbsp; int ciphertext = powEI % n;

&nbsp;&nbsp;&nbsp; return ciphertext;

}
</pre><p>&nbsp;</p><p>And the decryption is being done according to the following formula: <em>plaintext = (ciphertext^d) mod n. </em>Ciphertext is transmitted as an integer parameter to the “decrypt” method:</p><pre class="ql-syntax" spellcheck="false">public static int decrypt (int c) {

&nbsp;&nbsp;&nbsp; int d = keyGen()[2];

&nbsp;&nbsp;&nbsp; int n = keyGen()[0];

&nbsp;&nbsp;&nbsp; double powD = Math.pow(c, d);

&nbsp;&nbsp;&nbsp; int powDI = (int) powD;

&nbsp;&nbsp;&nbsp; int plaintext = powDI % n;

&nbsp;&nbsp;&nbsp; return plaintext;

}
</pre><p><br></p>