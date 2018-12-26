//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
//An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
//You may assume all four edges of the grid are all surrounded by water.

//Example 1: 
//
//Input:
//  11110
//  11010
//  11000
//  00000
//
//Output: 1

//Example 2:
//
//Input:
//  11000
//  11000
//  00100
//  00011
//
//Output: 3


class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    result++;
                    BFS(grid, i, j);
                }
            }
        }
        return result;
    }

    private void BFS(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] array = queue.poll();
            int x = array[0];
            int y = array[1];

            if (!outOfBound(grid, x + 1, y) && grid[x + 1][y] == '1') {
                grid[x + 1][y] = '0';
                queue.offer(new int[]{x + 1, y});
            }
            if (!outOfBound(grid, x - 1, y) && grid[x - 1][y] == '1') {
                grid[x - 1][y] = '0';
                queue.offer(new int[]{x - 1, y});
            }
            if (!outOfBound(grid, x, y + 1) && grid[x][y + 1] == '1') {
                grid[x][y + 1] = '0';
                queue.offer(new int[]{x, y + 1});
            }
            if (!outOfBound(grid, x, y - 1) && grid[x][y - 1] == '1') {
                grid[x][y - 1] = '0';
                queue.offer(new int[]{x, y - 1});
            }

        }
    }

    private boolean outOfBound(char[][] grid, int x, int y) {
        int row = grid.length;
        int col = grid[0].length;
        return x < 0 || x >= row || y < 0 || y >= col;
    }
}



