//Given a list of strings words representing an English Dictionary, 
//find the longest word in words that can be built one character at a time by other words in words. 
//If there is more than one possible answer, return the longest word with the smallest lexicographical order.
//If there is no answer, return the empty string.

//Example 1:
//Input: 
//words = ["w","wo","wor","worl", "world"]
//Output: "world"
//Explanation: 
//The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".

//Example 2:
//Input: 
//words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
//Output: "apple"
//Explanation: 
//Both "apply" and "apple" can be built from other words in the dictionary. 
//However, "apple" is lexicographically smaller than "apply".

//Note:
//
//All the strings in the input will only contain lowercase letters.
//The length of words will be in the range [1, 1000].
//The length of words[i] will be in the range [1, 30].




class Solution {
    // brute force
    // Time: O(w1^2 + w2^2 + w3^2 + ... + wn^2)
    // Space: O(w1 + w2 + w3 + ... + wn + n)
    public String longestWord(String[] words) {
        Set<String> hashset = new HashSet<>();
        for (String word : words) {
            hashset.add(word);
        }
        String res = "";
        
        for (String word : words) {
            if (word.length() < res.length() || (word.length() == res.length() && word.compareTo(res) > 0)) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            boolean isValid = true;
            for (int i = 0; i < word.length(); i++) {
                sb.append(word.charAt(i));
                if (!hashset.contains(sb.toString())) {
                    isValid = false;
                    break;
                }
            }
            res = isValid ? word : res;
        }
        return res;
    }
}







class Solution {
    // Trie + sorting
    // Time: O(w1 + w2 + w3 + ... + wn + nlogn)
    // Space: O(26 * w * n)
    public String longestWord(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
        });

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (String word : words) {
            if (trie.hasAllPrefix(word)) {
                return word;
            }
        }
        return "";
    }


    class Trie {
        class TrieNode {
            private TrieNode[] child;
            private boolean isWord;

            public TrieNode() {
                this.child = new TrieNode[26];
                this.isWord = false;
            }
        }

        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            char[] array = word.toCharArray();
            TrieNode node = root;
            for (char c : array) {
                int index = c - 'a';
                if (node.child[index] == null) {
                    node.child[index] = new TrieNode();
                }
                node = node.child[index];
            }
            node.isWord = true;
        }

        public boolean hasAllPrefix(String word) {
            char[] array = word.toCharArray();
            TrieNode node = root;
            for (char c : array) {
                int index = c - 'a';
                if (node.child[index] == null) {
                    return false;
                }
                node = node.child[index];
                if (!node.isWord) {
                    return false;
                }
            }
            return true;
        }
    }
}




