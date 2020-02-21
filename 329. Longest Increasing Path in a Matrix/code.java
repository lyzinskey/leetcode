//Given an integer matrix, find the length of the longest increasing path.

//From each cell, you can either move to four directions: left, right, up or down. 
//You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

//Example 1:
//
//Input: nums = 
//[
//  [9,9,4],
//  [6,6,8],
//  [2,1,1]
//] 
//Output: 4 
//Explanation: The longest increasing path is [1, 2, 6, 9].

//Example 2:
//
//Input: nums = 
//[
//  [3,4,5],
//  [3,2,6],
//  [2,2,1]
//] 
//Output: 4 
//Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.





class Solution {
    // dp (dfs + memorization)
    // Time: O(mn)
    // Space: O(mn)
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int res = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res = Math.max(res, dfs(matrix, dp, i, j));
            }
        }
        return res;
    }
    
    private int dfs(int[][] matrix, int[][] dp, int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        dp[x][y] = 1;
        for (int[] dir : dirs) {
            int dx = x + dir[0];
            int dy = y + dir[1];
            if (dx < 0 || dx >= matrix.length || dy < 0 || dy >= matrix[0].length || matrix[x][y] >= matrix[dx][dy]) {
                continue;
            }
            dp[x][y] = Math.max(dp[x][y], dfs(matrix, dp, dx, dy) + 1);            
        }
        return dp[x][y];
    }
}




