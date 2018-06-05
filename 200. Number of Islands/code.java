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
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int row = grid.length;
        int column = grid[0].length;
        int island = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1'){
                    island++;
                    BFS(grid, i, j);
                }
            }
        }
        return island;        
    }
    
    private void BFS(char[][] grid, int n, int m){
        int[] x = {0, 0, -1, 1};
        int[] y = {-1, 1, 0, 0};

        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(new Coordinate(n, m));
        grid[n][m] = '0';

        while (!queue.isEmpty()){
            Coordinate coor = queue.poll();
            for (int i = 0; i < 4; i++) {
                Coordinate child = new Coordinate(coor.x + x[i], coor.y + y[i]);

                if (OutOfBound(grid, child)){
                    continue;
                }

                if (grid[child.x][child.y] == '1'){
                    grid[child.x][child.y] = '0';
                    queue.offer(child);
                }
            }
        }
    }

    private boolean OutOfBound(char[][] grid, Coordinate coor){
        int row = grid.length;
        int column = grid[0].length;
        return coor.x < 0 || coor.x >= row || coor.y < 0 || coor.y >=column;
    }
    
}

class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

