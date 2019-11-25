//A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), 
//followed by some number of '1's (also possibly 0.)

//We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.

//Return the minimum number of flips to make S monotone increasing.

//Example 1:
//
//Input: "00110"
//Output: 1
//Explanation: We flip the last digit to get 00111.

//Example 2:
//
//Input: "010110"
//Output: 2
//Explanation: We flip to get 011111, or alternatively 000111.

//Example 3:
//
//Input: "00011000"
//Output: 2
//Explanation: We flip to get 00000000.

//Note:
//
//1 <= S.length <= 20000
//S only consists of '0' and '1' characters.




// Time: O(n)
// Space: O(n)
// dp[i][0] means min cost to make string valid if char at i is changed to 0
// dp[i][1] means min cost to make string valid if char at i is changed to 1
class Solution {
    public int minFlipsMonoIncr(String S) {
        int[][] dp = new int[S.length() + 1][2];
        for (int i = 1; i <= S.length(); i++) {
            if (S.charAt(i - 1) == '0') {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]);
            }
        }
        return Math.min(dp[dp.length - 1][0], dp[dp.length - 1][1]);
    }
}    



// Time: O(n)
// Space: O(1)
// same idea as the previous solution but optimize space to O(1)
class Solution {    
    public int minFlipsMonoIncr(String S) {
        int[][] dp = new int[S.length() + 1][2];
        int dp0 = 0;
        int dp1 = 0;
        for (int i = 1; i <= S.length(); i++) {
            int temp0 = 0;
            int temp1 = 0;
            if (S.charAt(i - 1) == '0') {
                temp0 = dp0;
                temp1 = Math.min(dp0, dp1) + 1;
            } else {
                temp0 = dp0 + 1;
                temp1 = Math.min(dp0, dp1);
            }
            dp0 = temp0;
            dp1 = temp1;
        }
        return Math.min(dp0, dp1);
    }
}




