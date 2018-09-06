//Given a string s, partition s such that every substring of the partition is a palindrome.

//Return the minimum cuts needed for a palindrome partitioning of s.

//Example:
//
//Input: "aab"
//Output: 1
//Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.




class Solution {
    public int minCut(String input) {
        if (input.length() <= 1) {
            return 0;
        }
        char[] array = input.toCharArray();
        int[] dp = new int[array.length];
        for (int i = 0; i < dp.length; i++) {
            if (isPalindrome(array, 0, i)) {
                dp[i] = 0;
            }
            else {
                dp[i] = i;
                for (int j = 1; j <= i; j++) {
                    if (isPalindrome(array, j, i)) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }

    private boolean isPalindrome(char[] input, int left, int right) {
        while (left < right) {
            if (input[left] != input[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }    
}



