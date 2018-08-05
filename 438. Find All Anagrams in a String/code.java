//Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

//Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

//The order of output does not matter.

//Example 1:
//
//Input:
//s: "cbaebabacd" p: "abc"
//
//Output:
//[0, 6]
//
//Explanation:
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".

//Example 2:
//
//Input:
//s: "abab" p: "ab"
//
//Output:
//[0, 1, 2]
//
//Explanation:
//The substring with start index = 0 is "ab", which is an anagram of "ab".
//The substring with start index = 1 is "ba", which is an anagram of "ab".
//The substring with start index = 2 is "ab", which is an anagram of "ab".



class Solution {
    public List<Integer> findAnagrams(String l, String s) {
        List<Integer> result = new ArrayList<>();

        if (l.length() == 0 || s.length() > l.length()) {
            return result;
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
                result.add(i - s.length() + 1);
            }
        }

        return result;
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



