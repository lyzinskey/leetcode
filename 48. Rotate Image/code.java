//You are given an n x n 2D matrix representing an image.
//Rotate the image by 90 degrees (clockwise).

//Note:
//You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
//DO NOT allocate another 2D matrix and do the rotation.

//Example 1:
//Given input matrix = 
//  [
//    [1,2,3],
//    [4,5,6],
//    [7,8,9]
//  ],
//rotate the input matrix in-place such that it becomes:
//  [
//    [7,4,1],
//    [8,5,2],
//    [9,6,3]
//  ]

//Example 2:
//Given input matrix =
//  [
//    [ 5, 1, 9,11],
//    [ 2, 4, 8,10],
//    [13, 3, 6, 7],
//    [15,14,12,16]
//  ], 
//rotate the input matrix in-place such that it becomes:
//  [
//    [15,13, 2, 5],
//    [14, 3, 4, 1],
//    [12, 6, 8, 9],
//    [16, 7,10,11]
//  ]






class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        helper(matrix, 0, matrix.length);
    }

    private void helper(int[][] matrix, int offset, int size) {
        if (size <= 1) {
            return;
        }
        List<Integer> topRow = getTopRow(matrix, offset, size);
        List<Integer> bottomRow = getBottomRow(matrix, offset, size);
        List<Integer> leftCol = getLeftCol(matrix, offset, size);
        List<Integer> rightCol = getRightCol(matrix, offset, size);

        copyTopRow(matrix, topRow, offset, size);
        copyBottomRow(matrix, bottomRow, offset, size);
        copyLeftCol(matrix, leftCol, offset, size);
        copyRightCol(matrix, rightCol, offset, size);
        helper(matrix, offset + 1, size - 2);
    }

    private List<Integer> getTopRow(int[][] matrix, int offset, int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(matrix[offset][i + offset]);
        }
        return result;
    }
    private List<Integer> getBottomRow(int[][] matrix, int offset, int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(matrix[offset + size - 1][i + offset]);
        }
        return result;
    }
    private List<Integer> getLeftCol(int[][] matrix, int offset, int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            result.add(matrix[i + offset][offset]);
        }
        return result;
    }
    private List<Integer> getRightCol(int[][] matrix, int offset, int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            result.add(matrix[offset + i][offset + size - 1]);
        }
        return result;
    }

    private void copyTopRow(int[][] matrix, List<Integer> topRow, int offset, int size) {
        for (int i = 0; i < size; i++) {
            matrix[offset + i][offset + size - 1] = topRow.get(i);
        }
    }
    private void copyRightCol(int[][] matrix, List<Integer> rightCol, int offset, int size) {
        for (int i = 0; i < size; i++) {
            matrix[offset + size - 1][i + offset] = rightCol.get(i);
        }
    }
    private void copyBottomRow(int[][] matrix, List<Integer> bottomRow, int offset, int size) {
        for (int i = 0; i < size; i++) {
            matrix[i + offset][offset] = bottomRow.get(i);
        }
    }
    private void copyLeftCol(int[][] matrix, List<Integer> leftCol, int offset, int size) {
        for (int i = 0; i < size; i++) {
            matrix[offset][i + offset] = leftCol.get(i);
        }
    }    
}



