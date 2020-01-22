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
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int res = 0;
        
        for (int i = 0; i < row; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res = Math.max(dfs(matrix, dp, i, j, dirs), res);
            }
        }
        return res;
    }
    
    private int dfs(int[][] matrix, int[][] dp, int i, int j, int[][] dirs) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        dp[i][j] = 1;
        for (int[] dir : dirs) {
            int dx = i + dir[0];
            int dy = j + dir[1];
            if (dx < 0 || dx >= matrix.length || dy < 0 || dy >= matrix[0].length || matrix[i][j] >= matrix[dx][dy]) {
                continue;
            }
            dp[i][j] = Math.max(dp[i][j], dfs(matrix, dp, dx, dy, dirs) + 1);
        }
        return dp[i][j];
    }
}




