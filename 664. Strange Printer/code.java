//There is a strange printer with the following two special requirements:

//The printer can only print a sequence of the same character each time.
//At each turn, the printer can print new characters starting from and ending at any places, 
//and will cover the original existing characters.
//Given a string consists of lower English letters only, 
//your job is to count the minimum number of turns the printer needed in order to print it.

//Example 1:
//Input: "aaabbb"
//Output: 2
//Explanation: Print "aaa" first and then print "bbb".

//Example 2:
//Input: "aba"
//Output: 2
//Explanation: Print "aaa" first and then print "b" from the second place of the string, 
//which will cover the existing character 'a'.

//Hint: Length of the given string will not exceed 100.




class Solution {
    // Time: O(n^3)
    // Space: O(n^2)
    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        return getMinCost(s.toCharArray(), dp, 0, n - 1);
    }
    
    private int getMinCost(char[] array, int[][] dp, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        int ans = getMinCost(array, dp, i, j - 1) + 1;
        for (int k = i; k < j; k++) {
            if (array[k] == array[j]) {
                ans = Math.min(ans, getMinCost(array, dp, i, k) + getMinCost(array, dp, k + 1, j - 1));
            }            
        }
        dp[i][j] = ans;
        return ans;
    }
}




