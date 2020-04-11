//Given a string s and an integer k, find out if the given string is a K-Palindrome or not.

//A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.

//Example 1:
//
//Input: s = "abcdeca", k = 2
//Output: true
//Explanation: Remove 'b' and 'e' characters.

//Constraints:
//
//1 <= s.length <= 1000
//s has only lowercase English letters.
//1 <= k <= s.length




class Solution {
    // Time: O(n^2)
    // Space: O(n^2)
    public boolean isValidPalindrome(String s, int k) {
        Integer[][] dp = new Integer[s.length()][s.length()];
        return dfs(s.toCharArray(), 0, s.length() - 1, dp) <= k;
    }

    private int dfs(char[] array, int left, int right, Integer[][] dp) {
        if (right - left < 1) {
            return 0;
        }
        if (dp[left][right] != null) {
            return dp[left][right];
        }

        int step = 0;
        if (array[left] == array[right]) {
            step = dfs(array, left + 1, right - 1, dp);
        } else {
            step = 1 + Math.min(dfs(array, left + 1, right, dp), dfs(array, left, right - 1, dp));
        }
        dp[left][right] = step;
        return step;
    }
}



