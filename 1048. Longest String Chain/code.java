//Given a list of words, each word consists of English lowercase letters.

//Let's say word1 is a predecessor of word2 
//if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  
//For example, "abc" is a predecessor of "abac".

//A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, 
//where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

//Return the longest possible length of a word chain with words chosen from the given list of words.

//Example 1:
//
//Input: ["a","b","ba","bca","bda","bdca"]
//Output: 4
//Explanation: one of the longest word chain is "a","ba","bda","bdca".

//Note:
//
//1 <= words.length <= 1000
//1 <= words[i].length <= 16
//words[i] only consists of English lowercase letters.




class Solution {
    // dp map: key -> word in dict, val -> max chain len that end with key word
    //
    // Time: O(nlogn + nss)
    // Space: O(ns)
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); ++i) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                int len = 0;
                if (dp.containsKey(prev)) {
                    len = dp.get(prev);
                }
                best = Math.max(best, len + 1);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }
}




