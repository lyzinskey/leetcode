//Given a string containing only three types of characters: '(', ')' and '*', 
//write a function to check whether this string is valid. We define the validity of a string by these rules:

//Any left parenthesis '(' must have a corresponding right parenthesis ')'.
//Any right parenthesis ')' must have a corresponding left parenthesis '('.
//Left parenthesis '(' must go before the corresponding right parenthesis ')'.
//'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
//An empty string is also valid.

//Example 1:
//Input: "()"
//Output: True

//Example 2:
//Input: "(*)"
//Output: True

//Example 3:
//Input: "(*))"
//Output: True

//Note:
//The string size will be in the range [1, 100].





class Solution {
    // memorization recursion
    // Time: O(n^3)
    // Space: O(n^2)
    //
    // two cases:
    // case1: L + dp[i + 1][j - 1] + R
    // case2: dp[i][k] && dp[k + 1][j]
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int len = s.length();
        int[][] dp = new int[len][len];
        return dfs(s.toCharArray(), 0, len - 1, dp) == 1;             
    }
    
    private int dfs(char[] array, int left, int right, int[][] dp) {
        if (left > right) {
            return 1;
        }
        if (dp[left][right] != 0) {
            return dp[left][right];
        }
        if (left == right) {
            dp[left][right] = array[left] == '*' ? 1 : -1;
            return dp[left][right];
        }
        // if left and right can match, and dp[left + 1][right - 1] is valid
        // then dp[left][right] is valid
        if ((array[left] == '(' || array[left] == '*') 
            && (array[right] == ')' || array[right] == '*') 
            && dfs(array, left + 1, right - 1, dp) == 1) {
            dp[left][right] = 1;
            return dp[left][right];
        }
        // for every k in [left, right)
        // if dp[left][k] is valid and dp[k + 1][right] is also valid
        // then dp[left][right] is valid
        for (int i = left; i < right; i++) {
            if (dfs(array, left, i, dp) == 1 && dfs(array, i + 1, right, dp) == 1) {
                dp[left][right] = 1;
                return dp[left][right];
            }
        }
        dp[left][right] = -1;
        return dp[left][right];
    }
}






class Solution {    
    // We count the number of ')' we are waiting for,
    // and it's equal to the number of open parenthesis.
    // This number will be in a range and we count it as [cmin, cmax]
    //
    // cmax counts the maximum open parenthesis,
    // which means the maximum number of unbalanced '(' that COULD be paired.
    // cmin counts the minimum open parenthesis,
    // which means the number of unbalanced '(' that MUST be paired.
    //
    // The string is valid for 2 condition:
    // cmax will never be negative.
    //cmin is 0 at the end.
    // 
    // Time: O(n)
    // Space: O(1)
    public boolean checkValidString(String s) {
        int cmin = 0, cmax = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == ')') {
                cmax--;
                cmin = Math.max(cmin - 1, 0);
            } else {
                cmax++;
                cmin = Math.max(cmin - 1, 0);
            }
            if (cmax < 0) return false;
        }
        return cmin == 0;
    }
}



