//The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

//Given an integer n, return the number of distinct solutions to the n-queens puzzle.

//Example:
//
//Input: 4
//Output: 2
//
//Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
//  [
//   [".Q..",  // Solution 1
//    "...Q",
//    "Q...",
//    "..Q."],
//  
//   ["..Q.",  // Solution 2
//    "Q...",
//    "...Q",
//    ".Q.."]
//  ]



class Solution {
    private int results = 0;
    
    public int totalNQueens(int n) {
        if (n <= 0) {
            return results;
        }
        
        ArrayList<Integer> col = new ArrayList<>();
        search(col, n);
        return results;
    }
    
    private void search(ArrayList<Integer> col, int n) {
        if (col.size() == n) {
            results++;
            return;
        }
        
        for (int column = 0; column < n; column++) {
            if (!isValid(col, column)) {
                continue;
            }
            
            col.add(column);
            search(col, n);
            col.remove(col.size() - 1);
        }
    }
    
    private boolean isValid(ArrayList<Integer> col, int column) {
        for (int row = 0; row < col.size(); row++) {
            //排除两个Queen在同一列的情况
            if (col.get(row) == column) {
                return false;
            }
            
            //排除两个Queen处于右上左下的情况
            if (row + col.get(row) == col.size() + column) {
                return false;
            }
            
            //排除两个Queen处于左上右下的情况
            if (row - col.get(row) == col.size() - column) {
                return false;
            }
        }
        return true;
    }
}




// solution 2
//
class Solution {
    private int result = 0;
    
    public int totalNQueens(int n) {            
        List<Integer> res = new ArrayList<>();
        DFS(res, n);
        return result;
    }

    private void DFS(List<Integer> res, int n) {
        if (res.size() == n) {
            result++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (valid(res, i)) {
                res.add(i);
                DFS(res, n);
                res.remove(res.size() - 1);
            }
        }
    }

    private boolean valid(List<Integer> res, int col) {
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i) == col || Math.abs(res.get(i) - col) == res.size() - i) {
                return false;
            }
        }
        return true;
    }
}



