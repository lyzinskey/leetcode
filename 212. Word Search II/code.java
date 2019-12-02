//Given a 2D board and a list of words from the dictionary, find all words in the board.

//Each word must be constructed from letters of sequentially adjacent cell, 
//where "adjacent" cells are those horizontally or vertically neighboring. 
//The same letter cell may not be used more than once in a word.

//Example:
//
//Input: 
//words = ["oath","pea","eat","rain"] and board =
//  [
//    ['o','a','a','n'],
//    ['e','t','a','e'],
//    ['i','h','k','r'],
//    ['i','f','l','v']
//  ]
//
//Output: ["eat","oath"]

//Note:
//You may assume that all inputs are consist of lowercase letters a-z.




/*
trie: build trie to store every word
do dfs in board, for every path, try to match it in trie

Time: O(k*l + m*n * 4^l)    ->  m * n matrix, average length of word is l, size of words is k
k*l time to build trie, m*n*4^l to do dfs
*/
class Solution {
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return result;
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs(board, i, j, visited, trie.root, sb, result);
            }
        }
        return result;
    }

    private void dfs(char[][] board, int i, int j, boolean[][] visited, TrieNode root, StringBuilder sb, List<String> result) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) {
            return;
        }

        char ch = board[i][j];
        int index = ch - 'a';
        if (root.child[index] == null) {
            return;
        }

        root = root.child[index];
        sb.append(ch);
        if (root.isWord) {
            result.add(sb.toString());
            root.isWord = false;
        }

        visited[i][j] = true;
        for (int[] dir : dirs) {
            int dx = i + dir[0];
            int dy = j + dir[1];
            dfs(board, dx, dy, visited, root, sb, result);
        }
        sb.deleteCharAt(sb.length() - 1);
        visited[i][j] = false;
    }

    class TrieNode {
        private TrieNode[] child;
        private boolean isWord;

        public TrieNode() {
            this.child = new TrieNode[26];
            this.isWord = false;
        }
    }
    
    class Trie {
        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.child[index] == null) {
                    node.child[index] = new TrieNode();
                }
                node = node.child[index];
            }
            node.isWord = true;
        }
    }
}




/*
brute force: for every word, use dfs to search every possible path in board

Time: O(k*m*n * 4^l)    ->  m * n matrix, average length of word is l, size of words is k
*/
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0) {
            return result;
        } 
                
        for (int i = 0; i < words.length; i++) {
            if (exist(board, words[i])) {
                result.add(words[i]);
            }            
        }
        return result;        
    }
    
    private boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {                
                if (dfs(board, word, i, j, 0, visited)) {
                    return true;
                }                                
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(index) || visited[i][j]) {
            return false;
        }
        
        visited[i][j] = true;
        boolean result = dfs(board, word, i + 1, j, index + 1, visited) ||
                         dfs(board, word, i - 1, j, index + 1, visited) ||
                         dfs(board, word, i, j + 1, index + 1, visited) ||
                         dfs(board, word, i, j - 1, index + 1, visited);
        visited[i][j] = false;
        return result;
    }    
}




