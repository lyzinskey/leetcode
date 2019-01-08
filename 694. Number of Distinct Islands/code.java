//Given a non-empty 2D array grid of 0's and 1's, 
//an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
//You may assume all four edges of the grid are surrounded by water.

//Count the number of distinct islands. 
//An island is considered to be the same as another if and only if one island can be translated 
//(and not rotated or reflected) to equal the other.

//Example 1:
//11000
//11000
//00011
//00011
//Given the above grid map, return 1.

//Example 2:
//11011
//10000
//00001
//11011
//Given the above grid map, return 3.

//Notice that:
//11
//1
//and
// 1
//11
//are considered different island shapes, because we do not consider reflection / rotation.
//Note: The length of each dimension in the given grid does not exceed 50.




class Solution {
    int[][] dirs= new int[][]{{1,0}, {0,1}, {-1,0}, {0,-1}};
    
    public int numDistinctIslands(int[][] grid) {
        Set<String> hashset = new HashSet<>();
        int res = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();                    
                    helper(grid, i, j, 0, 0, sb);
                    String str = sb.toString();                    
                    
                    if (!hashset.contains(str)) {
                        res++;
                        hashset.add(str);
                    }                    
                }                
            }
        }
        return res;
    }
    
    private void helper(int[][] grid, int i, int j, int xpos, int ypos, StringBuilder sb) {
        grid[i][j] = 0;
        sb.append(xpos + "" + ypos);
        
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
                continue;
            }
            helper(grid, x, y, xpos + dir[0], ypos + dir[1], sb);
        }
    }    
}



