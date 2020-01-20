//In a given 2D binary array A, there are two islands.  
//(An island is a 4-directionally connected group of 1s not connected to any other 1s.)

//Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

//Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

//Example 1:
//
//Input: [[0,1],[1,0]]
//Output: 1

//Example 2:
//
//Input: [[0,1,0],[0,0,0],[0,0,1]]
//Output: 2

//Example 3:
//
//Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
//Output: 1

//Note:
//
//1 <= A.length = A[0].length <= 100
//A[i][j] == 0 or A[i][j] == 1





class Solution {
    // Time: O(mn)
    // Space: O(mn)
    public int shortestBridge(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        boolean found = false;
        for (int i = 0; i < row && !found; i++) {
            for (int j = 0; j < col && !found; j++) {
                if (A[i][j] == 1) {
                    dfs(A, visited, i, j, queue);
                    found = true;
                }
            }
        }        

        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] coor = queue.poll();                
                for (int[] dir : dirs) {
                    int dx = coor[0] + dir[0];
                    int dy = coor[1] + dir[1];
                    if (dx >= 0 && dx < row && dy >= 0 && dy < col) {
                        if (visited[dx][dy]) {
                            continue;
                        } else {
                            if (A[dx][dy] == 1) {
                                return count - 1;
                            }
                            visited[dx][dy] = true;
                            queue.offer(new int[] {dx, dy});
                        }
                    }
                }
            }
        }
        return - 1;
    }
    
    private void dfs(int[][] A, boolean[][] visited, int i, int j, Queue<int[]> queue) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length || A[i][j] == 0 || visited[i][j]) {
            return;
        }
        
        visited[i][j] = true;
        queue.offer(new int[] {i, j});
        dfs(A, visited, i + 1, j, queue);
        dfs(A, visited, i - 1, j, queue);
        dfs(A, visited, i, j + 1, queue);
        dfs(A, visited, i, j - 1, queue);
    }
}




