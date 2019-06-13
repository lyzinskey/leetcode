//Determine if a 9x9 Sudoku board is valid. 
//Only the filled cells need to be validated according to the following rules:
//Each row must contain the digits 1-9 without repetition.
//Each column must contain the digits 1-9 without repetition.
//Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

//The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

//Example 1:
//
//  Input:
//  [
//    ["5","3",".",".","7",".",".",".","."],
//    ["6",".",".","1","9","5",".",".","."],
//    [".","9","8",".",".",".",".","6","."],
//    ["8",".",".",".","6",".",".",".","3"],
//    ["4",".",".","8",".","3",".",".","1"],
//    ["7",".",".",".","2",".",".",".","6"],
//    [".","6",".",".",".",".","2","8","."],
//    [".",".",".","4","1","9",".",".","5"],
//    [".",".",".",".","8",".",".","7","9"]
//  ]
//  Output: true

//Example 2:
//
//  Input:
//  [
//    ["8","3",".",".","7",".",".",".","."],
//    ["6",".",".","1","9","5",".",".","."],
//    [".","9","8",".",".",".",".","6","."],
//    ["8",".",".",".","6",".",".",".","3"],
//    ["4",".",".","8",".","3",".",".","1"],
//    ["7",".",".",".","2",".",".",".","6"],
//    [".","6",".",".",".",".","2","8","."],
//    [".",".",".","4","1","9",".",".","5"],
//    [".",".",".",".","8",".",".","7","9"]
//  ]
//  Output: false

//Explanation: Same as Example 1, except with the 5 in the top left corner being 
//modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.

//Note:
//
//A Sudoku board (partially filled) could be valid but is not necessarily solvable.
//Only the filled cells need to be validated according to the mentioned rules.
//The given board contain only digits 1-9 and the character '.'.
//The given board size is always 9x9.




class Solution {
    //  Collect the set of things we see, encoded as strings. For example:
    //  '4' in row 7 is encoded as "(4)7".
    //  '4' in column 7 is encoded as "7(4)".
    //  '4' in the top-right block is encoded as "0(4)2".
    //  Scream false if we ever fail to add something because it was already added (i.e., seen before).    
    public boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    String num = "(" + board[i][j] + ")";
                    if (!seen.add(num + i) || !seen.add(j + num) || !seen.add(i/3 + num + j/3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}



