//You have d dice, and each die has f faces numbered 1, 2, ..., f.

//Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 
//to roll the dice so the sum of the face up numbers equals target.

//Example 1:
//
//Input: d = 1, f = 6, target = 3
//Output: 1
//Explanation: 
//You throw one die with 6 faces.  There is only one way to get a sum of 3.

//Example 2:
//
//Input: d = 2, f = 6, target = 7
//Output: 6
//Explanation: 
//You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
//1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

//Example 3:
//
//Input: d = 2, f = 5, target = 10
//Output: 1
//Explanation: 
//You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.

//Example 4:
//
//Input: d = 1, f = 2, target = 3
//Output: 0
//Explanation: 
//You throw one die with 2 faces.  There is no way to get a sum of 3.

//Example 5:
//
//Input: d = 30, f = 30, target = 500
//Output: 222616187
//Explanation: 
//The answer must be returned modulo 10^9 + 7.
 
//Constraints:
//
//1 <= d, f <= 30
//1 <= target <= 1000




class Solution {
    // dp[i][t]: number of ways to have t sum using first i dices
    // dp[i][t] = sum(dp[i - 1][t - j]) where 1 <= j <= min(f, target)
    //
    // Time: O(d * f * target)
    // Space: O(d * target) -> O(target)
    public int numRollsToTarget(int d, int f, int target) {
        int mod = 1000000007;
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= f; j++) {
                for (int t = j; t <= target; t++) {                        
                    dp[i][t] = (dp[i][t] + dp[i - 1][t - j]) % mod;                     
                }
            }
        }
        return dp[d][target];
    }
}



// another way to write the triple for loop
class Solution {
    // dp[i][t]: number of ways to have t sum using first i dices
    // dp[i][t] = sum(dp[i - 1][t - j]) where 1 <= j <= min(f, target)
    //
    // Time: O(d * f * target)
    // Space: O(d * target) -> O(target)
    public int numRollsToTarget(int d, int f, int target) {
        int mod = 1000000007;
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            for (int t = 1; t <= target; t++) {  
                for (int j = 1; j <= Math.min(t, f); j++) {
                                      
                    dp[i][t] = (dp[i][t] + dp[i - 1][t - j]) % mod;                     
                }
            }
        }
        return dp[d][target];
    }
}



