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






/*
using trie
Time: O(nL^2 + QL) where n is the number of words, 
      L is the max length of the word, Q is the number of queries.
Space: O(nL^2)
*/
class WordFilter {
    Trie trie = new Trie();
    public WordFilter(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder sb = new StringBuilder("{" + word);
            trie.insert(sb.toString(), i);
            char[] array = word.toCharArray();
            for (int j = array.length - 1; j >= 0; j--) {
                sb.insert(0, array[j]);
                trie.insert(sb.toString(), i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        String key = suffix + "{" + prefix;
        return trie.startsWith(key);
    }
}

class Trie {
    class TrieNode {
        private TrieNode[] child;
        private int index;

        public TrieNode() {
            this.child = new TrieNode[27];
            this.index = 0;
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word, int num) {
        TrieNode node = root;
        char[] array = word.toCharArray();
        for (char ch : array) {
            int index = ch - 'a';
            if (node.child[index] == null) {
                node.child[index] = new TrieNode();
            }
            node = node.child[index];
            node.index = num;
        }        
    }

    public int startsWith(String prefix) {
        TrieNode node = find(prefix);
        return node != null ? node.index : -1;
    }

    private TrieNode find(String input) {
        TrieNode node = root;
        char[] array = input.toCharArray();
        for (char ch : array) {
            int index = ch - 'a';
            node = node.child[index];
            if (node == null) {
                return node;
            }
        }
        return node;
    }
}






/*
using hashmap, generate all possible prefix & suffix for each word
Time: O(nL^2 + qL) where n is the number of words
      L is the max length of the word, q is the number of queries
Space: O(nL)
*/
class WordFilter {    
    Map<String, List<Integer>> prefixMap = new HashMap<>();
    Map<String, List<Integer>> suffixMap = new HashMap<>();

    public WordFilter(String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String prefix = words[i].substring(0, j);
                if (!prefixMap.containsKey(prefix)) {
                    prefixMap.put(prefix, new ArrayList<>());
                }
                prefixMap.get(prefix).add(i);

                String suffix = words[i].substring(words[i].length() - j);
                if (!suffixMap.containsKey(suffix)) {
                    suffixMap.put(suffix, new ArrayList<>());
                }
                suffixMap.get(suffix).add(i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        if (!prefixMap.containsKey(prefix) || !suffixMap.containsKey(suffix)) {
            return -1;
        }
        List<Integer> prefixList = prefixMap.get(prefix);
        List<Integer> suffixList = suffixMap.get(suffix);
        int i = prefixList.size() - 1;
        int j = suffixList.size() - 1;
        while (i >= 0 && j >= 0) {
            if (prefixList.get(i) < suffixList.get(j)) {
                j--;
            } else if (prefixList.get(i) > suffixList.get(j)) {
                i--;
            } else {
                return prefixList.get(i);
            }
        }
        return -1;
    }
}


/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
 
 
 
 
