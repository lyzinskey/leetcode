//Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
//add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

//Note:
//
//The same word in the dictionary may be reused multiple times in the segmentation.
//You may assume the dictionary does not contain duplicate words.

//Example 1:
//
//Input:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//Output:
//  [
//    "cats and dog",
//    "cat sand dog"
//  ]

//Example 2:
//
//Input:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//Output:
//  [
//    "pine apple pen apple",
//    "pineapple pen apple",
//    "pine applepen apple"
//  ]
//Explanation: Note that you are allowed to reuse a dictionary word.

//Example 3:
//
//Input:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output:
//  []



class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, ArrayList<String>> memo = new HashMap<>();
        Set<String> dict = new HashSet<>();
        
        for (String word : wordDict) {
            dict.add(word);
        }
        return wordBreakHelper(s, dict, memo);
    }        
    
    private List<String> wordBreakHelper(String s, 
                                         Set<String> wordDict, 
                                         Map<String, ArrayList<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        ArrayList<String> result = new ArrayList<>();
        
        if (s == null || s.length() == 0) {
            return result;
        }
        
        if (wordDict.contains(s)) {
            result.add(s);
        }
        
        for (int i = 1; i < s.length(); i++) {
            String word = s.substring(0, i);
            if (!wordDict.contains(word)) {
                continue;
            }
            
            String suffix = s.substring(i);
            List<String> suffixList = wordBreakHelper(suffix, wordDict, memo);
            
            for (String segment : suffixList) {
                result.add(word + " " + segment);
            }
        }
        
        memo.put(s, result);
        return result;
    }    
}

