//Given a string, find the length of the longest substring without repeating characters.

//Examples:
//
//Given "abcabcbb", the answer is "abc", which the length is 3.
//
//Given "bbbbb", the answer is "b", with the length of 1.
//
//Given "pwwkew", the answer is "wke", with the length of 3. 
//Note that the answer must be a substring, "pwke" is a subsequence and not a substring.



class Solution {
    public int lengthOfLongestSubstring(String input) {  
        if (input.isEmpty()) {
            return 0;
        }
    
        int slow = 0; 
        int fast = 0;
        int maxLength = 0;
        Set<Character> hashset = new HashSet<>();
    
        while (fast < input.length()) {
            if (!hashset.contains(input.charAt(fast))) {
                hashset.add(input.charAt(fast++));
                maxLength = Math.max(maxLength, fast - slow);
            }
            else {
                hashset.remove(input.charAt(slow++));        
            }
        }
    
        return maxLength;
    }
}



