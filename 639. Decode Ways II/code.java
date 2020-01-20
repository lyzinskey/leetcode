//A message containing letters from A-Z is being encoded to numbers using the following mapping way:

//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//Beyond that, now the encoded string can also contain the character '*', 
//which can be treated as one of the numbers from 1 to 9.

//Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

//Also, since the answer may be very large, you should return the output mod 109 + 7.

//Example 1:
//Input: "*"
//Output: 9
//Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".

//Example 2:
//Input: "1*"
//Output: 9 + 9 = 18

//Note:
//The length of the input string will fit in range [1, 105].
//The input string will only contain the character '*' and digits '0' - '9'.





class Solution {
    // dp
    // Time: O(n)
    // Space: O(1)
    public int numDecodings(String s) {
        int mod = 1000000007;        
        char[] array = s.toCharArray();
        long[] dp = new long[] {1, ways(array[0])};
        
        for (int i = 1; i < array.length; i++) {
            long num = dp[1] * ways(array[i]) + dp[0] * ways(array[i - 1], array[i]);
            num %= mod;
            dp[0] = dp[1];
            dp[1] = num;
        }
        return (int) dp[1];
    }
    
    private long ways(char ch) {
        if (ch == '*') {
            return 9;
        }        
        return ch == '0' ? 0 : 1;
    }
    
    private long ways(char ch1, char ch2) {
        if (ch1 == '*' && ch2 == '*') {
            return 15;
        } else if (ch1 == '*') {
            int num = ch2 - '0';
            return num > 6 ? 1 : 2;
        } else if (ch2 == '*') {
            int num = ch1 - '0';
            if (num == 1) {
                return 9;
            } else if (num == 2) {
                return 6;
            } else {
                return 0;
            }
        } else {
            int num = (ch1 - '0') * 10 + (ch2 - '0');
            return num >= 10 && num <= 26 ? 1 : 0;
        }
    }
}




