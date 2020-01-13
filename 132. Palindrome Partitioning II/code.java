//Given a string s, partition s such that every substring of the partition is a palindrome.

//Return the minimum cuts needed for a palindrome partitioning of s.

//Example:
//
//Input: "aab"
//Output: 1
//Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.





class Solution {
    // Time: O(n^2)
    // Space: O(n^2)
    public int minCut(String input) {
        char[] array = input.toCharArray();
        int len = array.length;
        boolean[][] isPalindrome = new boolean[len][len];
        int[] dp = new int[len];
        Arrays.fill(dp, len - 1);
        
        // pre-processing: isPalindrome[i][j]: whether i ~ j is palindrome
        for (int i = 0; i < len; i++) {
            Arrays.fill(isPalindrome[i], true);
        }
        
        for (int l = 2; l <= len; l++) {
            for (int i = 0, j = i + l - 1; j < len; i++, j++) {
                isPalindrome[i][j] = array[i] == array[j] & isPalindrome[i + 1][j - 1];
            }
        }
        
        for (int i = 0; i < len; i++) {
            if (isPalindrome[0][i]) {
                dp[i] = 0;
                continue;
            }
            // try every possible partition position and find min cut
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    } 
}



