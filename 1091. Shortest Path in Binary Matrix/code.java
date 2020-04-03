//In an N by N square grid, each cell is either empty (0) or blocked (1).

//A clear path from top-left to bottom-right has length k 
//if and only if it is composed of cells C_1, C_2, ..., C_k such that:

//Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
//C_1 is at location (0, 0) (ie. has value grid[0][0])
//C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
//If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
//Return the length of the shortest such clear path from top-left to bottom-right.  
//If such a path does not exist, return -1.

//Example 1:
//
//Input: [[0,1],[1,0]]
//
//Output: 2

//Example 2:
//
//Input: [[0,0,0],[1,1,0],[1,1,0]]
//
//Output: 4

//Note:
//
//1 <= grid.length == grid[0].length <= 100
//grid[r][c] is 0 or 1




class Solution {
    // Time: O(mn)
    // Space: O(mn)
    public int shortestPathBinaryMatrix(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        if (grid.length == 0 || grid[0].length == 0 || grid[0][0]==1 || grid[row - 1][col - 1]==1) {
            return -1;
        }
        
        int step = 0;                
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});        
        
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                
                if (node[0] == row - 1 && node[1] == col - 1) {
                    return step;
                }
                
                for (int[] dir : dirs) {
                    int dx = node[0] + dir[0];
                    int dy = node[1] + dir[1];
                    if (isValid(row, col, dx, dy) && grid[dx][dy] == 0) {
                        grid[dx][dy] = 1;
                        queue.offer(new int[] {dx, dy});
                    }
                }
            }            
        }
        return -1;
    }
    
    private boolean isValid(int row, int col, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col) {
            return false;
        }
        return true;
    }
}



