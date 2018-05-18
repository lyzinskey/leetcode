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


public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            //palindromes with odd number of letters (such as ”abba”)
            int len1 = expandAroundCenter(s, i, i);
            //palindromes with even number of letters (such as ”abba”)
            int len2 = expandAroundCenter(s, i, i + 1);
            //len: the length of the palindrome
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        //public String substring(int beginIndex, int endIndex)
        //Returns a new string that is a substring of this string.
        //The substring begins at the specified beginIndex and extends to the character at index endIndex - 1.
        return s.substring(start, end + 1);
    }

    //helper function
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
