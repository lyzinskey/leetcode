//Given n balloons, indexed from 0 to n-1. 
//Each balloon is painted with a number on it represented by array nums. 
//You are asked to burst all the balloons. 
//If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
//Here left and right are adjacent indices of i. 
//After the burst, the left and right then becomes adjacent.

//Find the maximum coins you can collect by bursting the balloons wisely.

//Note:
//
//You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
//0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

//Example:
//
//Input: [3,1,5,8]
//Output: 167 
//Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167





class Solution {    
    // Time: O(n^3)
    // Space: O(n^2)
    public int maxCoins(int[] input) {
        int n = input.length;
        int[] nums = new int[n + 2];
        for (int i = 0; i < input.length; i++) {
            nums[i + 1] = input[i];
        }
        nums[0] = 1;
        nums[nums.length - 1] = 1;
        
        // dp[i][j] = maxCoins(nums[i] ~ nums[j])
        int[][] dp = new int[n + 2][n + 2];        
        // l represents the size of sub-problems
        // sub-problem is like a sliding window of size l
        for (int l = 1; l <= n; l++) {
            // i represents the start index of current sub-problems
            for (int i = 1; i <= n - l + 1; i++) {
                // j represents the end index of current sub-problems
                int j = i + l - 1;
                // k is the last balloon we want to burst in every sub-problem
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + nums[i - 1] * nums[k] * nums[j + 1] + dp[k + 1][j]);
                }
            }
        }
        return dp[1][n];
    }
}




