//Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, 
//find a water cell such that its distance to the nearest land cell is maximized and return the distance.

//The distance used in this problem is the Manhattan distance: 
//the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

//If no land or water exists in the grid, return -1.

//Example 1:
//
//Input: [[1,0,1],[0,0,0],[1,0,1]]
//Output: 2
//Explanation: 
//The cell (1, 1) is as far as possible from all the land with distance 2.

//Example 2:
//
//Input: [[1,0,0],[0,0,0],[0,0,0]]
//Output: 4
//Explanation: 
//The cell (2, 2) is as far as possible from all the land with distance 4.

//Note:
//
//1 <= grid.length == grid[0].length <= 100
//grid[i][j] is 0 or 1




class Solution {
    // bfs
    // Time: O(mn)
    // Space: O(mn)
    public int maxDistance(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int land = 0;
        int res = 0;
        int water = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    land++;
                    queue.offer(new int[] {i, j});
                } else {
                    water++;
                }
            }
        }
        
        if (land == 0 || water == 0) {
            return -1;
        }
        
        while (!queue.isEmpty()) {            
            if (water == 0) {
                return res;
            }
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                for (int[] dir : dirs) {
                    int dx = node[0] + dir[0];
                    int dy = node[1] + dir[1];
                    if (dx < 0 || dx >= row || dy < 0 || dy >= col || grid[dx][dy] == 1) {
                        continue;
                    }
                    grid[dx][dy] = 1;
                    water--;
                    queue.offer(new int[] {dx, dy});
                }
            }
        }
        return res;
    }
}



