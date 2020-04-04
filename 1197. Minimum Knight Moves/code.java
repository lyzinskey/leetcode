//In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

//A knight has 8 possible moves it can make, as illustrated below. 
//Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

//Return the minimum number of steps needed to move the knight to the square [x, y].  
//It is guaranteed the answer exists.

//Example 1:
//
//Input: x = 2, y = 1
//Output: 1
//Explanation: [0, 0] → [2, 1]

//Example 2:
//
//Input: x = 5, y = 5
//Output: 4
//Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 
//Constraints:
//|x| + |y| <= 300




class Solution {    
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);        
        int[][] dirs = new int[][] {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});        
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int curX = cur[0];
                int curY = cur[1];
                if (curX == x && curY == y) {
                    return result;
                }
                
                for (int[] dir : dirs) {
                    int newX = curX + dir[0];
                    int newY = curY + dir[1];
                    if (!visited.contains(newX + "," + newY) && newX >= -1 && newY >= -1) {
                        queue.add(new int[] {newX, newY});
                        visited.add(newX + "," + newY);
                    }
                }
            }
            result++;
        }
        return -1;
    }
}



