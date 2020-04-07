//Given two non-negative integers num1 and num2 represented as strings, 
//return the product of num1 and num2, also represented as a string.

//Example 1:
//
//Input: num1 = "2", num2 = "3"
//Output: "6"

//Example 2:
//
//Input: num1 = "123", num2 = "456"
//Output: "56088"

//Note:
//
//The length of both num1 and num2 is < 110.
//Both num1 and num2 contain only digits 0-9.
//Both num1 and num2 do not contain any leading zero, except the number 0 itself.
//You must not use any built-in BigInteger library or convert the inputs to integer directly.




class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        char[] array1 = num1.toCharArray();
        char[] array2 = num2.toCharArray();
        
        int[] pos = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (array1[i] - '0') * (array2[j] - '0'); 
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }  

        StringBuilder sb = new StringBuilder();
        for(int p : pos) {
            if(!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}



