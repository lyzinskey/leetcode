//On a 2-dimensional grid, there are 4 types of squares:

//1 represents the starting square.  There is exactly one starting square.
//2 represents the ending square.  There is exactly one ending square.
//0 represents empty squares we can walk over.
//-1 represents obstacles that we cannot walk over.
//Return the number of 4-directional walks from the starting square to the ending square, 
//that walk over every non-obstacle square exactly once.

//Example 1:
//
//Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
//Output: 2
//Explanation: We have the following two paths: 
//1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
//2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)

//Example 2:
//
//Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
//Output: 4
//Explanation: We have the following four paths: 
//1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
//2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
//3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
//4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)

//Example 3:
//
//Input: [[0,1],[2,0]]
//Output: 0
//Explanation: 
//There is no path that walks over every empty square exactly once.
//Note that the starting and ending square can be anywhere in the grid.

//Note:
//
//1 <= grid.length * grid[0].length <= 20






class Solution {
    // dfs
    // Time: O(4^mn)
    // Space: O(mn)
    public int uniquePathsIII(int[][] grid) {
        int[] start = new int[2];
        int steps = 1;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    steps++;
                } else if (grid[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;         
                }
            }
        }
        return dfs(grid, start[0], start[1], steps);
    }
    
    private int dfs(int[][] grid, int x, int y, int steps) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == -1) {
            return 0;
        }
        if (grid[x][y] == 2) {            
            return steps == 0 ? 1 : 0;
        }
        grid[x][y] = -1;
        int res = dfs(grid, x + 1, y, steps - 1) + 
                  dfs(grid, x - 1, y, steps - 1) + 
                  dfs(grid, x, y + 1, steps - 1) + 
                  dfs(grid, x, y - 1, steps - 1);
        grid[x][y] = 0;
        return res;
    }
}




