//Design a class which receives a list of words in the constructor, 
//and implements a method that takes two words word1 and word2 
//and return the shortest distance between these two words in the list. 
//Your method will be called repeatedly many times with different parameters. 

//Example:
//Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//Input: word1 = “coding”, word2 = “practice”
//Output: 3
//
//Input: word1 = "makes", word2 = "coding"
//Output: 1

//Note:
//You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.





class WordDistance {
    Map<String, Integer> cache;
    Map<String, List<Integer>> index;
    public WordDistance(String[] words) {
        cache = new HashMap<>();
        index = new HashMap<>();
        
        for (int i = 0; i < words.length; i++) {
            if (!index.containsKey(words[i])) {
                index.put(words[i], new ArrayList<>());
            }
            index.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        String keyWord = word1 + word2;
        if (cache.containsKey(keyWord)) {
            return cache.get(keyWord);
        }
        
        List<Integer> l1 = index.get(word1);
        List<Integer> l2 = index.get(word2);
        int i1 = 0;
        int i2 = 0;
        int res = Integer.MAX_VALUE;
        while (i1 < l1.size() && i2 < l2.size()) {
            int v1 = l1.get(i1);
            int v2 = l2.get(i2);
            res = Math.min(res, Math.abs(v1 - v2));
            if (v1 > v2) {
                i2++;
            } else {
                i1++;
            }
        }
        cache.put(keyWord, res);
        return res;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
 
 
 
 
