//Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

//Example:
//
//Input: 3
//
//Output:
//  [
//   [ 1, 2, 3 ],
//   [ 8, 9, 4 ],
//   [ 7, 6, 5 ]
//  ]



class Solution {
    public int[][] generateMatrix(int n) {                
        int[][] matrix = new int[n][n];
        DFS(matrix, 0, matrix.length, 1);
        return matrix;
    }

    private void DFS(int[][] matrix, int offset, int size, int counter) {
        if (size <= 1) {
            if (size == 1) {
                matrix[offset][offset] = counter;
            }
            return;
        }

        for (int i = 0; i < size; i++) {
            matrix[offset][i + offset] = counter++;
        }
        for (int i = 1; i < size - 1; i++) {
            matrix[offset + i][offset + size - 1] = counter++;
        }
        for (int i = size - 1; i >= 0; i--) {
            matrix[offset + size - 1][i + offset] = counter++;
        }
        for (int i = size - 2; i >= 1; i--) {
            matrix[i + offset][offset] = counter++;
        }

        DFS(matrix, offset + 1, size - 2, counter);
    }    
}



