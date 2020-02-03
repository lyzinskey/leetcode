//Given a m x n matrix mat and an integer threshold. 
//Return the maximum side-length of a square with a sum less than or equal to threshold 
//or return 0 if there is no such square.

//Example 1:
//
//Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
//Output: 2
//Explanation: The maximum side length of square with sum less than 4 is 2 as shown.

//Example 2:
//
//Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
//Output: 0

//Example 3:
//
//Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
//Output: 3

//Example 4:
//
//Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
//Output: 2

//Constraints:
//
//1 <= m, n <= 300
//m == mat.length
//n == mat[i].length
//0 <= mat[i][j] <= 10000
//0 <= threshold <= 10^5





class Solution {
    // dp: range sum
    // Time: O(m * n * min(m, n))
    // Space: O(m * n)
    public int maxSideLength(int[][] mat, int threshold) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] dp = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int res = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // k started from the largest size we have already found
                for (int k = res; k + i <= row && k + j <= col; k++) {
                    if (rangeSum(dp, i, j, i + k, j + k) > threshold) {
                        break;
                    }
                    res = Math.max(res, k + 1);
                }
            }
        }
        return res;
    }

    private int rangeSum(int[][] dp, int i, int j, int x, int y) {
        return dp[x][y] - dp[i - 1][y] - dp[x][j - 1] + dp[i - 1][j - 1];
    }
}




