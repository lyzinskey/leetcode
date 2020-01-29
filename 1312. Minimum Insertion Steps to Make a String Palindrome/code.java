//Given a string s. In one step you can insert any character at any index of the string.
//Return the minimum number of steps to make s palindrome.
//A Palindrome String is one that reads the same backward as well as forward.

//Example 1:
//
//Input: s = "zzazz"
//Output: 0
//Explanation: The string "zzazz" is already palindrome we don't need any insertions.

//Example 2:
//
//Input: s = "mbadm"
//Output: 2
//Explanation: String can be "mbdadbm" or "mdbabdm".

//Example 3:
//
//Input: s = "leetcode"
//Output: 5
//Explanation: Inserting 5 characters the string becomes "leetcodocteel".

//Example 4:
//
//Input: s = "g"
//Output: 0

//Example 5:
//
//Input: s = "no"
//Output: 1

//Constraints:
//
//1 <= s.length <= 500
//All characters of s are lower case English letters.




class Solution {
    // dp: bottom up
    // Time: O(n^2)
    // Space: O(n^2)
    public int minInsertions(String s) {
        int n = s.length();
        char[] array = s.toCharArray();
        int[][] dp = new int[n+1][n+1];
        for (int l = 2; l <= n; l++)
            for (int i = 0, j = l - 1; j < n; i++, j++)
                dp[i][j] = array[i] == array[j] ? dp[i + 1][j - 1] : Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
        return dp[0][n - 1];
    }
}





class Solution {
    // recursion with memorization
    // Time: O(n^2)
    // Space: O(n^2)
    public int minInsertions(String s) {
        char[] array = s.toCharArray();
        int[][] dp = new int[array.length][array.length];
        dfs(dp, 0, array.length - 1, array);
        return dp[0][array.length - 1];
    }
    
    private int dfs(int[][] dp, int i, int j, char[] array) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (i == j || i + 1 == j) {
            dp[i][j] = array[i] == array[j] ? 0 : 1;
            return dp[i][j];
        }
        
        if (array[i] == array[j]) {
            dp[i][j] = dfs(dp, i + 1, j - 1, array);
        } else {
            dp[i][j] = Math.min(dfs(dp, i + 1, j, array), dfs(dp, i, j - 1, array)) + 1;
        }
        return dp[i][j];
    }
}



