<p><strong style="color: black;">Intro to Cryptography. Classical ciphers. Caesar cipher.</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Course: Cryptography &amp; Security</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Task: Laboratory work nr. 2</strong></p><p><span style="color: black;"> </span></p><p><span style="color: black;"> </span><strong style="color: black;">Author: Vladimir Russu</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">&nbsp;</strong></p><p><strong style="color: black;">Overview</strong></p>
<p>This report contains the description of the laboratory work nr.&nbsp;3&nbsp;for the Cryptography&nbsp;&amp;&nbsp;Security course.</p><p>In this laboratory work there is an asymmetric cipher being implemented – it is the RSA Cryptography algorithm. The corresponding class “RSACipher” from the “cipher” package implements the “AssymCipher” interface from the “interfaces” package. And the test case class “RSACipherTest” from the “testcases” package demonstrates how the encryption/decryption according to the RSA algorithm is actually being performed.</p><p><br></p><p><strong><em>RSA Cipher </em></strong></p><p>First, in order to get all the necessary numbers for encryption and decryption, the 2 keys need to be generated – one public for encryption, and another one private for decryption. That is done in the following way: first two prime numbers are taken:&nbsp;<em>p</em>&nbsp;and&nbsp;<em>q</em>. In the “RSACipher” class there is a method called “primeNumGen” which generates prime integer numbers within a specified range (here it is between 50 and 300). For each number from that range the remainders after divisions by all the numbers smaller or equal than that consecutive number are being checked and the count variable is being increased by 1 each time the remainder of division equals zero. Therefore, if after all the divisions for a particular number the count variable becomes 2 (meaning that that number can be divided without remainder only by 1 or by itself) – it means that that number is prime, and that number is being added to an integer list which is further converted into an integer array:</p><pre class="ql-syntax" spellcheck="false">public static int primeNumGen () {

&nbsp;&nbsp;&nbsp; List&lt;Integer&gt; primeNumList = new ArrayList&lt;Integer&gt;();

&nbsp;&nbsp;&nbsp; for (int p = 50; p &lt;= 300; p++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int count = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int i = 1; i &lt;= p; i++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (p % i == 0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; count++;

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
</pre><p>Calling the method represented above, prime number&nbsp;<em>p</em>&nbsp;and&nbsp;<em>q</em>&nbsp;can be obtained. Then&nbsp;<em>n</em>&nbsp;is obtained as&nbsp;<em>n=p*q</em>. Further&nbsp;<em>fi&nbsp;</em>is obtained which is&nbsp;<em>fi=(p-1)*(q-1)</em>. Then&nbsp;<em>e</em>, which is a component for the public key, is obtained by taking a number in the range between&nbsp;<em>1</em>&nbsp;and&nbsp;<em>f, </em>such that <em>gcd(e, fi) = 1</em>. In order to obtain that number <em>e </em>there is a method called “gcd1Arr”, which takes as parameter the integer number <em>fi</em> and returns an array of such number <em>e (1 &lt; e &lt; fi)</em> that <em>gcd(e, fi) = 1</em>. That is done in the following way: first, all divisors of <em>fi</em> are being identified and stored into an array, then all the numbers from <em>2</em> to <em>fi-1 </em>which have at least one common divisor with <em>fi</em> except 1 (checking if divisor of consecutive number equals any divisor of <em>fi</em> from the previous array) are stored into another array, and finally all the other numbers from 2 to <em>fi-1</em> except those from the last formed array are stored into another array – those numbers being all such numbers <em>e </em>that satisfy equation <em>gcd(e, fi) =&nbsp;1, 1 &lt; e &lt; fi</em>. Bellow it is shown the entire code of the correspondent method:</p><pre class="ql-syntax" spellcheck="false">public static int[] gcd1Arr (int fi) {

&nbsp;&nbsp;&nbsp; // creating the array of divisors of fi

&nbsp;&nbsp;&nbsp; List&lt;Integer&gt; fiDivList = new ArrayList&lt;Integer&gt;();

&nbsp;&nbsp;&nbsp; for (int i=2; i&lt;=fi; i++){

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (fi%i==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; fiDivList.add(i);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; int[] fiDiv = fiDivList.stream()

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .mapToInt(Integer::intValue)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .toArray();



&nbsp;&nbsp;&nbsp; List&lt;Integer&gt; allDivRList = new ArrayList&lt;Integer&gt;();

&nbsp;&nbsp;&nbsp; for (int k=2; k&lt;fi; k++)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int d=2; d&lt;k; d++)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (k%d == 0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int l=0; l&lt;fiDiv.length; l++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (d==fiDiv[l]) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; allDivRList.add(k);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; int[] allDivR =allDivRList.stream()

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .mapToInt(Integer::intValue)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .toArray();



&nbsp;&nbsp;&nbsp; List&lt;Integer&gt; gcd1List = new ArrayList&lt;Integer&gt;();

&nbsp;&nbsp;&nbsp; for (int x=2; x&lt;fi; x++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; int count = 0;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; for (int l=0; l&lt;allDivR.length; l++) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (x==l) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; count++;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (count==0) {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; gcd1List.add(x);

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; }

&nbsp;&nbsp;&nbsp; int[] gcd1 =allDivRList.stream()

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .mapToInt(Integer::intValue)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .toArray();

&nbsp;&nbsp;&nbsp; return gcd1;

}
</pre><p>That method is then called in the key generation method in order to obtain <em>e,</em> by getting a random element from the array returned by calling the “gcd1Array” method with parameter <em>fi</em>:</p><pre class="ql-syntax" spellcheck="false">int rndE = new Random().nextInt(gcd1Arr(fi).length);

int e = gcd1Arr(fi)[rndE];
</pre><p>Then&nbsp;<em>d</em>&nbsp;is being obtained as&nbsp;<em>d=fi*k+1</em>&nbsp;(k being a random integer number within specified range). The pair&nbsp;<em>(n, e)</em>&nbsp;forms the public key, and the pair&nbsp;<em>(n, d)</em>&nbsp;forms the private one. The bellow method “keyGen” returns&nbsp;<em>n</em>,&nbsp;<em>e</em>&nbsp;and&nbsp;<em>d</em>&nbsp;as integers of an array, in order to get them further for encryption and decryption:</p><pre class="ql-syntax" spellcheck="false">public static int[] keyGen(){

&nbsp;&nbsp;&nbsp; int p = primeNumGen();

&nbsp;&nbsp;&nbsp; int q = primeNumGen();

&nbsp;&nbsp;&nbsp; int n = p*q;

&nbsp;&nbsp;&nbsp; int fi = (p-1)*(q-1);

&nbsp;&nbsp;&nbsp; int rndE = new Random().nextInt(gcd1Arr(fi).length);

&nbsp;&nbsp; &nbsp;int e = gcd1Arr(fi)[rndE];

&nbsp;&nbsp;&nbsp; int k = (int) ((Math.random())*10 + 1);

&nbsp;&nbsp;&nbsp; int d = fi*k + 1;

&nbsp;&nbsp;&nbsp; int[] keyPar = new int[3];

&nbsp;&nbsp;&nbsp; keyPar[0] = n;

&nbsp;&nbsp;&nbsp; keyPar[1] = e;

&nbsp;&nbsp;&nbsp; keyPar[2] = d;

&nbsp;&nbsp;&nbsp; return keyPar;

}
</pre><p>&nbsp;</p><p>Then the encryption is being done according to the following formula:&nbsp;<em>ciphertext = (plaintext^e) mod n.&nbsp;</em>Plaintext is transmitted as an integer parameter to the “encrypt” method:</p><pre class="ql-syntax" spellcheck="false">public static int encrypt(int m) {

&nbsp;&nbsp;&nbsp; int e = keyGen()[1];

&nbsp;&nbsp;&nbsp; int n = keyGen()[0];

&nbsp;&nbsp;&nbsp; double powE = Math.pow(m, e);

&nbsp;&nbsp;&nbsp; int powEI = (int) powE;

&nbsp;&nbsp;&nbsp; int ciphertext = powEI % n;

&nbsp;&nbsp;&nbsp; return ciphertext;

}
</pre><p>&nbsp;</p><p>And the decryption is being done according to the following formula:&nbsp;<em>plaintext = (ciphertext^d) mod n.&nbsp;</em>Ciphertext is transmitted as an integer parameter to the “decrypt” method:</p><pre class="ql-syntax" spellcheck="false">public static int decrypt (int c) {

&nbsp;&nbsp;&nbsp; int d = keyGen()[2];

&nbsp;&nbsp;&nbsp; int n = keyGen()[0];

&nbsp;&nbsp;&nbsp; double powD = Math.pow(c, d);

&nbsp;&nbsp;&nbsp; int powDI = (int) powD;

&nbsp;&nbsp;&nbsp; int plaintext = powDI % n;

&nbsp;&nbsp;&nbsp; return plaintext;

}
</pre><p><br></p>