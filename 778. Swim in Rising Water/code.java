//On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

//Now rain starts to fall. At time t, the depth of the water everywhere is t. 
//You can swim from a square to another 4-directionally adjacent square 
//if and only if the elevation of both squares individually are at most t. 
//You can swim infinite distance in zero time. 
//Of course, you must stay within the boundaries of the grid during your swim.

//You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

//Example 1:
//
//Input: [[0,2],[1,3]]
//Output: 3
//Explanation:
//At time 0, you are in grid location (0, 0).
//You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
//You cannot reach point (1, 1) until time 3.
//When the depth of water is 3, we can swim anywhere inside the grid.

//Example 2:
//
//Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
//Output: 16
//Explanation:
// **0  1  2  3  4**
//24 23 22 21  **5**
// **12 13 14 15 16**
// **11** 17 18 19 20
// **10  9  8  7  6**
//
//The final route is marked in bold.
//We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

//Note:
//
//2 <= N <= 50.
//grid[i][j] is a permutation of [0, ..., N*N - 1].




class Solution {
    // Dijkstra
    // Time: O(n^2 * logn)
    // Space: O(n^2)
    public int swimInWater(int[][] grid) {   
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        PriorityQueue<int[]> minheap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {                
                return a[2] - b[2];
            }
        });
        int res = Integer.MIN_VALUE;
        minheap.offer(new int[] {0, 0, grid[0][0]});
        visited[0][0] = true;
        
        while (!minheap.isEmpty()) {
            int[] curr = minheap.poll();
            res = Math.max(res, curr[2]);
            if (curr[0] == row - 1 && curr[1] == col - 1) {
                return res;
            }
            for (int[] dir : dirs) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y]) {
                    continue;
                }
                minheap.offer(new int[] {x, y, grid[x][y]});
                visited[x][y] = true;
            }
        }
        return -1;
    }
}




