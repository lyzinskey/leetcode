//In a given grid, each cell can have one of three values:
//
//the value 0 representing an empty cell;
//the value 1 representing a fresh orange;
//the value 2 representing a rotten orange.
//Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
//
//Return the minimum number of minutes that must elapse until no cell has a fresh orange.  
//If this is impossible, return -1 instead.

//Example 1:
//
//Input: [[2,1,1],[1,1,0],[0,1,1]]
//Output: 4

//Example 2:
//
//Input: [[2,1,1],[0,1,1],[1,0,1]]
//Output: -1
//Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, 
//because rotting only happens 4-directionally.

//Example 3:
//
//Input: [[0,2]]
//Output: 0
//Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

//Note:
//
//1 <= grid.length <= 10
//1 <= grid[0].length <= 10
//grid[i][j] is only 0, 1, or 2.





class Solution {
    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        int res = 0;
        int row = grid.length; 
        int col = grid[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();        
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int num = grid[i][j];
                if (num == 1) {
                    fresh++;
                } else if (num == 2) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        
        if (fresh == 0) {
            return 0;
        }
        
        while (!queue.isEmpty()) {
            if (fresh == 0) {
                return res;
            }
            res++;
            int size = queue.size();            
            for (int i = 0; i < size; i++) {
                int[] orange = queue.poll();
                for (int[] dir : dirs) {
                    int dx = orange[0] + dir[0];
                    int dy = orange[1] + dir[1];
                    if (dx < 0 || dx >= row || dy < 0 || dy >= col || grid[dx][dy] == 0 || grid[dx][dy] == 2) {
                        continue;
                    }                    
                    grid[dx][dy] = 2;
                    queue.offer(new int[] {dx, dy});
                    fresh--;                    
                }
            }
        }
        return fresh == 0 ? res : -1;
    }
}



