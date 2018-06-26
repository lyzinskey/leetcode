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



class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0) {
            return result;
        } 
        
        Arrays.sort(words);
        for (int i = 0; i < words.length; i++) {
            if (i > 0 && words[i].equals(words[i - 1])) {
                continue;
            }
            if (exist(board, words[i])) {
                result.add(words[i]);
            }            
        }
        return result;        
    }
    
    private boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, i, j, 0)) {
                        return true;
                    }
                }
                
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }
        
        board[i][j] = '?';
        boolean result = dfs(board, word, i + 1, j, index + 1) ||
                         dfs(board, word, i - 1, j, index + 1) ||
                         dfs(board, word, i, j + 1, index + 1) ||
                         dfs(board, word, i, j - 1, index + 1);
        board[i][j] = word.charAt(index);
        return result;
    }    
}

