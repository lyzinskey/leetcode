//Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
//In other words, one of the first string's permutations is the substring of the second string.

//Example 1:
//Input:s1 = "ab" s2 = "eidbaooo"
//Output:True
//Explanation: s2 contains one permutation of s1 ("ba").

//Example 2:
//Input:s1= "ab" s2 = "eidboaoo"
//Output: False

//Note:
//The input strings only contain lower case letters.
//The length of both given strings is in range [1, 10,000].




class Solution {
    public boolean checkInclusion(String s, String l) {             
        if (l.length() == 0 || s.length() > l.length()) {
            return false;
        }

        Map<Character, Integer> hashmap = getLetterFrequency(s);
        int matches = hashmap.size();

        for (int i = 0; i < l.length(); i++) {
            char ch = l.charAt(i);
            Integer count = hashmap.get(ch);

            if (count != null) {
                hashmap.put(ch, count - 1);
                if (count == 1) {
                    matches--;
                }
            }

            if (i >= s.length()) {
                ch = l.charAt(i - s.length());
                count = hashmap.get(ch);

                if (count != null) {
                    hashmap.put(ch, count + 1);
                    if (count == 0) {
                        matches++;
                    }
                }
            }

            if (matches == 0) {
                return true;
            }
        }

        return false;
    }

    private Map<Character, Integer> getLetterFrequency(String s) {
        Map<Character, Integer> hashmap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashmap.containsKey(s.charAt(i))) {
                hashmap.put(s.charAt(i), hashmap.get(s.charAt(i)) + 1);
            }
            else {
                hashmap.put(s.charAt(i), 1);
            }
        }
        return hashmap;
    }    
}



