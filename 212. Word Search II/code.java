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




