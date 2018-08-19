//Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

//Example:
//
//Input: 
//
//  1 0 1 0 0
//  1 0 1 1 1
//  1 1 1 1 1
//  1 0 0 1 0
//
//Output: 4



class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        int max = 0;
        int[][] DP = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 || j == 0) {
                    DP[i][j] = matrix[i][j] == '0' ? 0 : 1;
                }
                else if (matrix[i][j] == '1') {
                    DP[i][j] = getMin(DP[i - 1][j - 1], DP[i - 1][j], DP[i][j - 1]) + 1;
                }
                max = Math.max(max, DP[i][j]);
            }
        }
        return max * max;
    }

    private int getMin(int a, int b, int c) {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }    
}



