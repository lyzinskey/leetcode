//A message containing letters from A-Z is being encoded to numbers using the following mapping:

//  'A' -> 1
//  'B' -> 2
//  ...
//  'Z' -> 26
//  Given a non-empty string containing only digits, determine the total number of ways to decode it.

//  Example 1:
//
//  Input: "12"
//  Output: 2
//  Explanation: It could be decoded as "AB" (1 2) or "L" (12).

//  Example 2:
//  
//  Input: "226"
//  Output: 3
//  Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).




class Solution {
    public int numDecodings(String s) {        
        int prev = 1;
        int cur = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            int next = 0;
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                next += cur;
            }
            if (second >= 10 && second <= 26) {
                next += prev;
            }            
            prev = cur;
            cur = next;
        }
        return cur;
    }
}



