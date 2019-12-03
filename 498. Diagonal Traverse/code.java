//Given a matrix of M x N elements (M rows, N columns), 
//return all elements of the matrix in diagonal order as shown in the below image.

//Example:
//
//Input:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//
//Output:  [1,2,4,7,5,3,6,8,9]

//Explanation:
//https://assets.leetcode.com/uploads/2018/10/12/diagonal_traverse.png

//Note:
//
//The total number of elements of the given matrix will not exceed 10,000.




class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {};
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        int col = 0;
        int[] res = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            res[i] = matrix[row][col];
            // for both inner if-else, the order of checking condition is crucial, 
            // if you are at a corner then for moving up you always first check column then row, 
            // for moving down you always first check row then column
            
            // The direction is always up when the sum of row & col is even
            if ((row + col) % 2 == 0) {
                // For last column, go down
                if (col == n - 1) {
                    row++;
                } 
                // For first row & non-last columns, go right
                else if (row == 0) {
                    col++;
                } 
                // For not first row & non-last columns, go up and to the right
                else {
                    row--;
                    col++;
                }
            } 
            // The direction is always down when the sum of row & col is odd
            else {
                // For last row, go right
                if (row == m - 1) {
                    col++;
                } 
                //  For non-last row & first column, go down
                else if (col == 0) {
                    row++;
                } 
                // For non-last row & non-first column, go down and to the left
                else {
                    row++;
                    col--;
                }
            }            
        }
        return res;
    }
}



