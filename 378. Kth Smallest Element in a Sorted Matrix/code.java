//Given a n x n matrix where each of the rows and columns are sorted in ascending order, 
//find the kth smallest element in the matrix.

//Note that it is the kth smallest element in the sorted order, not the kth distinct element.

//Example:
//
//matrix = [
//   [ 1,  5,  9],
//   [10, 11, 13],
//   [12, 13, 15]
//],
//k = 8,
//return 13.

//Note: 
//You may assume k is always valid, 1 ≤ k ≤ n2.



class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        int column = matrix[0].length;
        boolean[][] visited = new boolean[row][column];

        PriorityQueue<Cell> minheap = new PriorityQueue<>(k, new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                if (c1.val == c2.val) {
                    return 0;
                }
                else {
                    return c1.val < c2.val ? -1 : 1;
                }
            }
        });

        minheap.offer(new Cell(0, 0, matrix[0][0]));

        for (int i = 0; i < k - 1; i++) {
            Cell cell = minheap.poll();
            if (cell.row + 1 < row && !visited[cell.row + 1][cell.col]) {
                minheap.offer(new Cell(cell.row + 1, cell.col, matrix[cell.row + 1][cell.col]));
                visited[cell.row + 1][cell.col] = true;
            }

            if (cell.col + 1 < column && !visited[cell.row][cell.col + 1]) {
                minheap.offer(new Cell(cell.row, cell.col + 1, matrix[cell.row][cell.col + 1]));
                visited[cell.row][cell.col + 1] = true;
            }
        }

        return minheap.poll().val;
    }

    class Cell {
        int row;
        int col;
        int val;

        Cell(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}


