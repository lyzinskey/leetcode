//Given many words, words[i] has weight i.

//Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). 
//It will return the word with given prefix and suffix with maximum weight. 
//If no word exists, return -1.

//Examples:
//
//Input:
//WordFilter(["apple"])
//WordFilter.f("a", "e") // returns 0
//WordFilter.f("b", "") // returns -1

//Note:
//
//words has length in range [1, 15000].
//For each test case, up to words.length queries WordFilter.f may be made.
//words[i] has length in range [1, 10].
//prefix, suffix have lengths in range [0, 10].
//words[i] and prefix, suffix queries consist of lowercase letters only.




class WordFilter {
    // using hashmap, generate all possible filters
    // Time: O(nL^3 + qL) where n is the number of words
    //       L is the max length of the word, q is the number of queries
    // Space: O(nL^3)
    private Map<String, Integer> hashmap;

    public WordFilter(String[] words) {
        this.hashmap = new HashMap<>();
        for (int index = 0; index < words.length; index++) {
            char[] word = words[index].toCharArray();
            StringBuilder prefix = new StringBuilder();
            StringBuilder suffix = new StringBuilder();
            List<String> prefixes = new ArrayList<>();
            List<String> suffixes = new ArrayList<>();
            prefixes.add(prefix.toString());
            suffixes.add(suffix.toString());

            for (int i = 0; i < word.length; i++) {
                prefix.append(word[i]);
                suffix.insert(0, word[word.length - i - 1]);
                prefixes.add(prefix.toString());
                suffixes.add(suffix.toString());
            }

            for (String pre : prefixes) {
                for (String suf : suffixes) {
                    hashmap.put(pre + "-" + suf, index);
                }
            }
        }
    }

    public int f(String prefix, String suffix) {
        String key = prefix + "-" + suffix;
        return hashmap.getOrDefault(key, -1);
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
 
 
 
 
 
