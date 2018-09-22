//Given a string array words, find the maximum value of length(word[i]) * length(word[j]) 
//where the two words do not share common letters. 
//You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

//Example 1:
//
//Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
//Output: 16 
//Explanation: The two words can be "abcw", "xtfn".

//Example 2:
//
//Input: ["a","ab","abc","d","cd","bcd","abcd"]
//Output: 4 
//Explanation: The two words can be "ab", "cd".

//Example 3:
//
//Input: ["a","aa","aaa","aaaa"]
//Output: 0 
//Explanation: No such pair of words.




class Solution {
    public int maxProduct(String[] dict) {
        int max = 0;
        Arrays.sort(dict, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {        
                if (s1.length() == s2.length()) {
                return 0;
                }
                else {
                    return s1.length() < s2.length() ? 1 : -1;
                }
           }
        });
    
    
        for (int i = 0; i < dict.length; i++) {
            for (int j = i + 1; j < dict.length; j++) {
                if (!commonChar(dict[i], dict[j])) {
                    max = Math.max(dict[i].length() * dict[j].length(), max);
                }
            }
        }    
        return max;
    }
  
    private boolean commonChar(String s1, String s2) {
        Set<Character> hashset = new HashSet<>();
        for (int i = 0; i < s2.length(); i++) {
            hashset.add(s2.charAt(i));
        }
        for (int i = 0; i < s1.length(); i++) {
            if (hashset.contains(s1.charAt(i))) {
            return true;
            }
        }
        return false;
    }    
}



