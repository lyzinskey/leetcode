//There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
//The cost of painting each house with a certain color is different. 
//You have to paint all the houses such that no two adjacent houses have the same color.

//The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
//For example, costs[0][0] is the cost of painting house 0 with color red; 
//costs[1][2] is the cost of painting house 1 with color green, and so on... 
//Find the minimum cost to paint all houses.

//Note:
//All costs are positive integers.

//Example:
//
//Input: [[17,2,17],[16,16,5],[14,3,19]]
//Output: 10
//Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
//             Minimum cost: 2 + 5 + 3 = 10.




class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        
        int red = costs[0][0];
        int blue = costs[0][1];
        int green = costs[0][2];
        
        for (int i = 1; i < costs.length; i++) {
            int nextRed = Math.min(blue, green) + costs[i][0];
            int nextBlue = Math.min(red, green) + costs[i][1];
            int nextGreen = Math.min(red, blue) + costs[i][2];
            red = nextRed;
            blue = nextBlue;
            green = nextGreen;
        }
        return Math.min(red, Math.min(blue, green));
    }
}



