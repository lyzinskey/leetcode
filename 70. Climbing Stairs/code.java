//You are climbing a stair case. It takes n steps to reach to the top.

//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

//Note: Given n will be a positive integer.

//Example 1:
//
//Input: 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps

//Example 2:
//
//Input: 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step





//One can reach i-th step in one of the two ways:
//    Taking a single step from (i−1)th step.
//    Taking a step of 22 from (i−2)th step.
//So, the total number of ways to reach i-th is equal to 
//sum of ways of reaching (i−1)th step and ways of reaching (i−2)th step.
//
//Let dp[i]dp[i] denotes the number of ways to reach on i-th step:
//dp[i]=dp[i−1]+dp[i−2]



class Solution {
    public int climbStairs(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}



