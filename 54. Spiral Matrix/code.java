//Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

//Example 1:
//Input:
//  [
//   [ 1, 2, 3 ],
//   [ 4, 5, 6 ],
//   [ 7, 8, 9 ]
//  ]
//Output: [1,2,3,6,9,8,7,4,5]

//Example 2:
//Input:
//  [
//    [1, 2, 3, 4],
//    [5, 6, 7, 8],
//    [9,10,11,12]
//  ]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]



class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {    
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }

        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;
        int startRow = 0;
        int startCol = 0;

        while (startRow < endRow && startCol < endCol) {
            // left to right
            for (int i = startCol; i <= endCol; i++) {
                result.add(matrix[startRow][i]);
            }
            // top to bottom
            for (int i = startRow + 1; i <= endRow - 1; i++) {
                result.add(matrix[i][endCol]);
            }
            // right to left
            for (int i = endCol; i >= startCol; i--) {
                result.add(matrix[endRow][i]);
            }
            // bottom to top
            for (int i = endRow - 1; i >= startRow + 1; i--) {
                result.add(matrix[i][startRow]);
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }

        if (startRow > endRow || startCol > endCol) {
            return result;
        }
        // one row left
        if (startRow == endRow) {
            for (int i = startCol; i <= endCol; i++) {
                result.add(matrix[startRow][i]);
            }
        }
        // one col left
        else {
            for (int i = startRow; i <= endRow; i++) {
                result.add(matrix[i][endCol]);
            }
        }
        return result;
    }    
}



