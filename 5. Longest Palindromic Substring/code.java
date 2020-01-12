//Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//
//Example 1:
//
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
//Example 2:
//
//Input: "cbbd"
//Output: "bb"




class Solution {
    // Time: O(n^2)
    // Space: O(1);
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        char[] array = s.toCharArray();
        int len = 0;
        int start = 0;
        
        for (int i = 0; i < array.length; i++) {         
            int cur = Math.max(getLen(array, i, i), getLen(array, i, i + 1));
            if (cur > len) {
                len = cur;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + len);
    }
    
    private int getLen(char[] array, int l, int r) {
        while (l >= 0 && r < array.length && array[l] == array[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}



