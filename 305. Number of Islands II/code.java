//A 2d grid map of m rows and n columns is initially filled with water. 
//We may perform an addLand operation which turns the water at position (row, col) into a land. 
//Given a list of positions to operate, count the number of islands after each addLand operation. 
//An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
//You may assume all four edges of the grid are all surrounded by water.

//Example:
//
//Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
//Output: [1,1,2,3]
//Explanation:
//
//Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
//
//0 0 0
//0 0 0
//0 0 0
//Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
//
//1 0 0
//0 0 0   Number of islands = 1
//0 0 0
//Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
//
//1 1 0
//0 0 0   Number of islands = 1
//0 0 0
//Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
//
//1 1 0
//0 0 1   Number of islands = 2
//0 0 0
//Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
//
//1 1 0
//0 0 1   Number of islands = 3
//0 1 0

//Follow up:
//Can you do it in time complexity O(k log mn), where k is the length of the positions?





class Solution {
    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (m == 0 || n == 0 || positions.length == 0 || positions[0].length == 0) {
            return result;
        }
        
        int counter = 0;
        int[] roots = new int[m * n];
        Arrays.fill(roots, -1);
        
        for (int[] pos : positions) {
            int root = n * pos[0] + pos[1];
            roots[root] = root;
            counter++;
            
            for (int[] dir : dirs) {
                int x = pos[0] + dir[0]; 
                int y = pos[1] + dir[1];
                int nb = n * x + y;
                if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) {
                    continue;
                }
            
                int rootNb = findRoot(roots, nb);
                if(root != rootNb) {
                    roots[root] = rootNb;
                    root = rootNb;
                    counter--;         
                }
            }
            
            result.add(counter);
        }
        return result;
    }
    
    private int findRoot(int[] roots, int id) {
        while (id != roots[id]) {
            id = roots[id];
        }
        return id;
    }
}



