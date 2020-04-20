//A die simulator generates a random number from 1 to 6 for each roll. 
//You introduced a constraint to the generator 
//such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times. 

//Given an array of integers rollMax and an integer n, 
//return the number of distinct sequences that can be obtained with exact n rolls.

//Two sequences are considered different if at least one element differs from each other. 
//Since the answer may be too large, return it modulo 10^9 + 7.

//Example 1:
//
//Input: n = 2, rollMax = [1,1,2,2,2,3]
//Output: 34
//Explanation: There will be 2 rolls of die, if there are no constraints on the die, 
//there are 6 * 6 = 36 possible combinations. 
//In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, 
//therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.

//Example 2:
//
//Input: n = 2, rollMax = [1,1,1,1,1,1]
//Output: 30

//Example 3:
//
//Input: n = 3, rollMax = [1,1,1,2,2,3]
//Output: 181
 
//Constraints:
//
//1 <= n <= 5000
//rollMax.length == 6
//1 <= rollMax[i] <= 15





class Solution {    
    // state:
    // 1. length of the sequence
    // 2. last dice (check whether it's the same as the curr dice)
    // 3. num of consecutive dices of the end
    //
    // dp[i][j][k]: num of length-i sequences end with k js (长度为i的，以k个j为结尾的序列的个数)
    //
    // dp[1][*][1] = 1
    // dp[i][j][1] = sum(dp[i - 1][p][*]), where j != p
    // dp[i][j][k + 1] = dp[i - 1][j][k], k < rollMax[j]
    // answer: sum(dp[n][*][*])
    //
    // Time: O( n * 6 * 6 * max(rollMax[i]) )
    // Space: O( n * 6 * max(rollMax[i]) )  =>  O( 6 * max(rollMax[i]) )    
    public int dieSimulator(int n, int[] rollMax) {
        long[][][] dp = new long[n + 1][6][16];
        int mod = 1000000007;

        for (int i = 0; i < 6; i++) {
            dp[1][i][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int p = 0; p < 6; p++) {
                    for (int k = 1; k <= rollMax[p]; k++) {
                        if (p != j) {
                            dp[i][j][1] = (dp[i][j][1] + dp[i - 1][p][k]) % mod;
                        } else if (k < rollMax[p]) {
                            dp[i][j][k + 1] = dp[i - 1][j][k];
                        }
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= rollMax[i]; j++) {
                res = (res + dp[n][i][j]) % mod;
            }
        }
        return (int) res;
    }
}



