//In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

//After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

//Example 1:
//
//Input: [[1, 0], [0, 1]]
//Output: 3
//Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

//Example 2:
//
//Input: [[1, 1], [1, 0]]
//Output: 4
//Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

//Example 3:
//
//Input: [[1, 1], [1, 1]]
//Output: 4
//Explanation: Can't change any 0 to 1, only one island with area = 4.

//Notes:
//
//1 <= grid.length = grid[0].length <= 50.
//0 <= grid[i][j] <= 1.




class Solution {
    // use hashmap to store island index and corresponding area
    // check every "0" for merging distinct island
    // Time: O(n^2)
    // Space: O(n^2)    
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int largestIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 2;
        boolean[][] visited = new boolean[row][col];
        Map<Integer, Integer> hashmap = new HashMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    hashmap.put(count, dfs(grid, visited, i, j, count));
                    count++;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    res = Math.max(res, getSize(hashmap, grid, i, j));
                }
            }
        }
        return res == 0 ? row * col : res;
    }
    
    // walk through every island and return its area size
    private int dfs(int[][] grid, boolean[][] visited, int i, int j, int count) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        visited[i][j] = true;
        grid[i][j] = count;
        int size = 1;
        for (int[] dir : dirs) {
            int dx = i + dir[0];
            int dy = j + dir[1];
            if (dx < 0 || dx >= grid.length || dy < 0 || dy >= grid[0].length || visited[dx][dy]) {
                continue;
            }
            if (grid[dx][dy] == 1) {
                size += dfs(grid, visited, dx, dy, count);
            }
        }
        return size;
    }

    // for every "0", try to merge adjacent distinct island    
    private int getSize(Map<Integer, Integer> hashmap, int[][] grid, int i, int j) {
        Set<Integer> hashset = new HashSet<>();
        for (int[] dir : dirs) {
            int dx = i + dir[0];
            int dy = j + dir[1];
            if (dx < 0 || dx >= grid.length || dy < 0 || dy >= grid[0].length) {
                continue;
            }
            hashset.add(grid[dx][dy]);
        }
        int size = 1;
        for (int index : hashset) {
            if (hashmap.containsKey(index)) {
                size += hashmap.get(index);
            }            
        }
        return size;
    }
}



