//Given a triangle, find the minimum path sum from top to bottom. 
//Each step you may move to adjacent numbers on the row below.

//For example, given the following triangle
//  
//  [
//       [2],
//      [3,4],
//     [6,5,7],
//    [4,1,8,3]
//  ]
//The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

//Note:
//
//Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.


class Solution {
    
    private int row; 
    private int[][] minSum;
    private List<List<Integer>> triangle;
    
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0) == null || triangle.get(0).size() == 0) {
            return -1;
        }

        this.row = triangle.size();
        this.triangle = triangle;
        this.minSum = new int[row][row];        
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                minSum[i][j] = Integer.MAX_VALUE;
            }
        }
        
        return search(0, 0);        
    }
    
    private int search(int x, int y) {
        if (x >= row) {
            return 0;
        }
        
        if (minSum[x][y] != Integer.MAX_VALUE) {
            return minSum[x][y];
        }
        
        minSum[x][y] = Math.min(search(x + 1, y), search(x + 1, y + 1)) + triangle.get(x).get(y);
        
        return minSum[x][y];
    }    
}

