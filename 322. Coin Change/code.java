//You are given coins of different denominations and a total amount of money amount. 
//Write a function to compute the fewest number of coins that you need to make up that amount. 
//If that amount of money cannot be made up by any combination of the coins, return -1.

//Example 1:
//
//Input: coins = [1, 2, 5], amount = 11
//Output: 3 
//Explanation: 11 = 5 + 5 + 1

//Example 2:
//
//Input: coins = [2], amount = 3
//Output: -1

//Note:
//You may assume that you have an infinite number of each kind of coin.




class Solution {
    // dp[i][j]: min cost to make up j amount using first i types of coins
    // dp[i][j] = min(dp[i][j], dp[i][j - coin_i] + 1)
    //
    // Time: O(n * amount)
    // Space: O(n * amount) -> O(amount)
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != -1) {
                    min = Math.min(min, dp[i - coin]);
                }                                
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min + 1;            
        }
        
        return dp[amount];
    }
}




// DP: top down, recursion
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        
        return coinChange(coins, amount, new int[amount]);
    }
    
    private int coinChange(int[] coins, int remain, int[] count) {
        if (remain == 0) {
            return 0;
        }
        if (remain < 0) {
            return -1;
        }
        if (count[remain - 1] != 0) {
            return count[remain - 1];
        }
        
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, remain - coin, count);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        count[remain - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[remain - 1];
    }
}



