//Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
//determine if s can be segmented into a space-separated sequence of one or more dictionary words.

//Note:
//
//The same word in the dictionary may be reused multiple times in the segmentation.
//You may assume the dictionary does not contain duplicate words.

//Example 1:
//
//Input: s = "leetcode", wordDict = ["leet", "code"]
//Output: true
//Explanation: Return true because "leetcode" can be segmented as "leet code".

//Example 2:
//
//Input: s = "applepenapple", wordDict = ["apple", "pen"]
//Output: true
//Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//             Note that you are allowed to reuse a dictionary word.

//Example 3:
//
//Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output: false



class Solution {
    // Time: O(l^3)
    // Space: O(l + dict.size)
    public boolean wordBreak(String input, List<String> dict) {
        Set<String> hashset = new HashSet<>();
        for (String str : dict) {
            hashset.add(str);
        }
        boolean[] dp = new boolean[input.length() + 1];
        dp[0] = true;
        
        for (int j = 0; j <= input.length(); j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (dp[i] && hashset.contains(input.substring(i, j))) {
                    dp[j] = true;
                    break;
                }
            }
        }
        return dp[input.length()];
    }
}




class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int maxLength = getMaxLength(wordDict);
        boolean[] canBreak = new boolean[s.length() + 1];
        canBreak[0] = true;
        
        for (int i = 1; i <= s.length(); i++) {
            canBreak[i] = false;
            
            for (int j = 1; j <= maxLength && j <= i; j++) {
                if (!canBreak[i - j]) {
                    continue;
                }
                
                String string = s.substring(i - j, i);
                if (wordDict.contains(string)) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        
        return canBreak[s.length()];        
    }
    
    private int getMaxLength(List<String> dict) {
        int maxLength = 0;
        
        for (String word : dict) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        
        return maxLength;
    }    
}





// DP
//
class Solution {
    public boolean wordBreak(String input, List<String> dict) {
        // boolean[] 的长度定为input.length() + 1，
        // 省一个corner case同时方便后续思考
        boolean[] DP = new boolean[input.length() + 1];
        Set<String> hashset = makeDict(dict);
        
        // i 是从1到input.length() + 1，而不是从0到input.length()
        // 因为boolean[]的长度为input.length() + 1
        for (int i = 1; i < input.length() + 1; i++) {
            if (hashset.contains(input.substring(0,i))) {
                DP[i] = true;
                continue;
            }
            // j相当于从0到i之间每一个能切的位置，只要有任何一个case满足条件，
            // DP[i]就返回true
            for (int j = 1; j < i; j++) {
                if (DP[j] && hashset.contains(input.substring(j,i))) {
                    DP[i] = true;
                    continue;
                }
            }
        }
        return DP[input.length()];
    }

    private Set<String> makeDict(List<String> dict) {
        Set<String> hashset = new HashSet<>();
        for (String word : dict) {
            hashset.add(word);
        }
        return hashset;
    }    
}




