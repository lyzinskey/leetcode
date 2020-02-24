//Given a string containing just the characters '(' and ')', 
//find the length of the longest valid (well-formed) parentheses substring.

//Example 1:
//
//Input: "(()"
//Output: 2
//Explanation: The longest valid parentheses substring is "()"

//Example 2:
//
//Input: ")()())"
//Output: 4
//Explanation: The longest valid parentheses substring is "()()"




class Solution {
    // dp
    // Time: O(n)
    // Space: O(n)
    // dp[i]: longest length of valid parentheses that end at i including i
    public int longestValidParentheses(String s) {
        int len = s.length();
        int res = 0;
        int[] dp = new int[len];

        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] += i - 2 >= 0 ? dp[i - 2] + 2 : 2;
                    res = Math.max(res, dp[i]);
                } else {
                    if (i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                        dp[i] += dp[i - 1] + 2;
                        // combine previous length
                        if (i - 2 - dp[i - 1] >= 0) {
                            dp[i] += dp[i - 2 - dp[i - 1]];
                        }
                        res = Math.max(res, dp[i]);
                    }
                }
            }
        }
        return res;
    }
}




