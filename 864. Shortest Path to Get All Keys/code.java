//We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, 
//"@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

//We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  
//We cannot walk outside the grid, or walk into a wall.  
//If we walk over a key, we pick it up.  
//We can't walk over a lock unless we have the corresponding key.

//For some 1 <= K <= 6, 
//there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  
//This means that there is exactly one key for each lock, and one lock for each key; 
//and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

//Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.
 
//Example 1:
//
//Input: ["@.a.#","###.#","b.A.B"]
//Output: 8

//Example 2:
//
//Input: ["@..aA","..B#.","....b"]
//Output: 6

//Note:
//
//1 <= grid.length <= 30
//1 <= grid[0].length <= 30
//grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
//The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.






class Solution {
    // bfs
    // using int to represent keys, every bit represents a key
    // at most 6 keys => 111111 = 63
    //
    // Time: O(m*n*64)
    // Space: O(m*n*64)
    public int shortestPathAllKeys(String[] input) {
        int row = input.length;
        int col = input[0].length();
        char[][] grid = new char[row][col];
        for (int i = 0; i < row; i++) {
            grid[i] = input[i].toCharArray();
        }
        
        boolean[][][] visited = new boolean[row][col][64];
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int allKeys = 0;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '@') {
                    queue.offer(new int[] {i, j, 0});
                    visited[i][j][0] = true;
                } else if (grid[i][j] >= 'a' && grid[i][j] <= 'f') {
                    allKeys |= (1 << (grid[i][j] - 'a'));
                }
            }
        }
        
        int step = 0;        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] tuple = queue.poll();                
                int key = tuple[2];
                if (key == allKeys) {
                    return step;
                }
                for (int[] dir : dirs) {
                    int dx = tuple[0] + dir[0];
                    int dy = tuple[1] + dir[1];
                    int newKey = key;
                    if (dx < 0 || dx >= row || dy < 0 || dy >= col) {
                        continue;
                    }
                    if (grid[dx][dy] == '#') {
                        continue;
                    }
                    if (grid[dx][dy] >= 'A' && grid[dx][dy] <= 'F' && (newKey & (1 << (grid[dx][dy] - 'A'))) == 0) {
                        continue;
                    }
                    if (grid[dx][dy] >= 'a' && grid[dx][dy] <= 'f') {
                        newKey |= (1 << (grid[dx][dy] - 'a'));
                    }
                    if (visited[dx][dy][newKey]) {
                        continue;
                    }
                    queue.offer(new int[] {dx, dy, newKey});
                    visited[dx][dy][newKey] = true;
                }
            }
            step++;
        }
        return -1;
    }
}




