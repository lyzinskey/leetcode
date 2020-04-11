//Given a string, find the length of the longest substring T that contains at most k distinct characters.

//Example 1:
//
//Input: s = "eceba", k = 2
//Output: 3
//Explanation: T is "ece" which its length is 3.

//Example 2:
//
//Input: s = "aa", k = 1
//Output: 2
//Explanation: T is "aa" which its length is 2.




class Solution {
    // Time: O(n)
    // Space: O(1)
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int num = 0;
        int res = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (count[ch] == 0) {
                num++;
            }
            count[ch]++;
            
            if (num > k) {                
                while (num > k) {                    
                    count[s.charAt(left)]--;
                    if (count[s.charAt(left)] == 0) {
                        num--;
                    }
                    left++;
                }
            }
            res = Math.max(res, right - left + 1);            
        }
        return res;
    }
}



