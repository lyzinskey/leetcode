//There is an m by n grid with a ball. 
//Given the start coordinate (i,j) of the ball, 
//you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). 
//However, you can at most move N times. 
//Find out the number of paths to move the ball out of grid boundary. 
//The answer may be very large, return it after mod 10^9 + 7.

//Example 1:
//
//Input: m = 2, n = 2, N = 2, i = 0, j = 0
//Output: 6
//Explanation:
//https://assets.leetcode.com/uploads/2018/10/13/out_of_boundary_paths_1.png

//Example 2:
//
//Input: m = 1, n = 3, N = 3, i = 0, j = 1
//Output: 12
//Explanation:
//https://assets.leetcode.com/uploads/2018/10/12/out_of_boundary_paths_2.png

//Note:
//
//Once you move the ball out of boundary, you cannot move it back.
//The length and height of the grid is in range [1,50].
//N is in range [0,50].




class Solution {
    /*
    Observation:
    Number of paths start from (i, j) to out of boundary <=>
    Number of paths start from out of boundary to (i, j)
    
    dp[N][i][j]: number of paths start from out of boundary to (i, j) by moving N steps
    dp[*][x][y] = 1, if (x, y) is out of boundary
    dp[s][i][j] = dp[s - 1][i + 1][j] 
                + dp[s - 1][i - 1][j] 
                + dp[s - 1][i][j + 1] 
                + dp[s - 1][i][j - 1]

    Time: O(Nmn)
    Space: O(Nmn) -> O(mn)
    */    
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][][] dp = new int[N + 1][m][n];
        int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int module = 1000000007;
        
        for (int k = 1; k <= N; k++) {
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    for (int s = 0; s < 4; s++) {
                        int dx = x + dir[s][0];
                        int dy = y + dir[s][1];
                        if (dx < 0 || dx >= m || dy < 0 || dy >= n) {
                            dp[k][x][y]++;
                        } else {
                            dp[k][x][y] = (dp[k][x][y] + dp[k - 1][dx][dy]) % module;
                        }
                    }
                }
            }
        }
        return dp[N][i][j];
    }
}




