//You are given a string s containing lowercase letters and an integer k. You need to :

//First, change some characters of s to other lowercase English letters.
//Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
//Return the minimal number of characters that you need to change to divide the string.

//Example 1:
//
//Input: s = "abc", k = 2
//Output: 1
//Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.

//Example 2:
//
//Input: s = "aabbc", k = 3
//Output: 0
//Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.

//Example 3:
//
//Input: s = "leetcode", k = 8
//Output: 0

//Constraints:
//
//1 <= k <= s.length <= 100.
//s only contains lowercase English letters.





class Solution {
    // Time: O(k*n^2)
    // Space: O(kn)
    //
    // dp[i][k]: min cost to make k strings using first i letters
    public int palindromePartition(String s, int k) {
        if (s.length() == k) {
            return 0;
        }
        
        char[] array = s.toCharArray();
        int len = array.length;
        int[][] cost = new int[len][len];
        
        // pre-pocessing
        // cost[i][j]: min cost to make partition [i, j] palindrome
        for (int l = 2; l <= len; l++) {
            for (int i = 0, j = l - 1; j < len; i++, j++) {
                if (array[i] == array[j]) {
                    cost[i][j] = cost[i + 1][j - 1];
                } else {
                    cost[i][j] = cost[i + 1][j - 1] + 1;
                }
            }
        }

        int[][] dp = new int[len][k + 1];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], 100);
        }
                
        for (int i = 0; i < len; i++) {
            dp[i][1] = cost[0][i];
            for (int j = 0; j < i; j++) {
                for (int l = 2; l <= k; l++) {
                    dp[i][l] = Math.min(dp[i][l], dp[j][l - 1] + cost[j + 1][i]);
                }
            }
        }
        return dp[len - 1][k];
    }
}




