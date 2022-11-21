package ciphers.hash;

public class hexToBin {
    public static String convert (String hex) {
        int len = hex.length();
        char[] hexDigit = hex.toCharArray();
        int i = 0;
        String binaryVal = "";
        while(i<len)
        {
            switch(hexDigit[i])
            {
                case '0':
                    binaryVal+="0000";
                    break;
                case '1':
                    binaryVal+="0001";
                    break;
                case '2':
                    binaryVal+="0010";
                    break;
                case '3':
                    binaryVal+="0011";
                    break;
                case '4':
                    binaryVal+="0100";
                    break;
                case '5':
                    binaryVal+="0101";
                    break;
                case '6':
                    binaryVal+="0110";
                    break;
                case '7':
                    binaryVal+="0111";
                    break;
                case '8':
                    binaryVal+="1000";
                    break;
                case '9':
                    binaryVal+="1001";
                    break;
                case 'a':
                case 'A':
                    binaryVal+="1010";
                    break;
                case 'b':
                case 'B':
                    binaryVal+="1011";
                    break;
                case 'c':
                case 'C':
                    binaryVal+="1100";
                    break;
                case 'd':
                case 'D':
                    binaryVal+="1101";
                    break;
                case 'e':
                case 'E':
                    binaryVal+="1110";
                    break;
                case 'f':
                case 'F':
                    binaryVal+="1111";
                    break;
                default:
                    System.out.println("\nInvalid Hexadecimal Digit!");
                    return binaryVal;
            }
            i++;
        }
        return binaryVal;
    }
}
