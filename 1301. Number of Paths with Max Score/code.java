//You are given a square board of characters. 
//You can move on the board starting at the bottom right square marked with the character 'S'.

//You need to reach the top left square marked with the character 'E'. 
//The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. 
//In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.

//Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, 
//and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.

//In case there is no path, return [0, 0].

//Example 1:
//
//Input: board = ["E23","2X2","12S"]
//Output: [7,1]

//Example 2:
//
//Input: board = ["E12","1X1","21S"]
//Output: [4,2]

//Example 3:
//
//Input: board = ["E11","XXX","11S"]
//Output: [0,0]

//Constraints:
//
//2 <= board.length == board[i].length <= 100





class Solution {
    // Time: O(n^2)
    // Space: O(n^2)
    public int[] pathsWithMaxScore(List<String> board) {
        int len = board.size();
        int[][] dp = new int[len + 1][len + 1];
        int[][] path = new int[len + 1][len + 1];
        path[len - 1][len - 1] = 1;        
        int mod = 1000000007;
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= 0; j--) {
                if (board.get(i).charAt(j) != 'X') {
                    int max = Math.max(dp[i + 1][j], Math.max(dp[i][j + 1], dp[i + 1][j + 1]));   
                    if (i == len - 1 && j == len - 1 || i == 0 && j == 0) {
                        dp[i][j] = max;
                    } else {
                        dp[i][j] = max + (board.get(i).charAt(j) - '0');
                    }                    
                    if (dp[i + 1][j] == max) {
                        path[i][j] = (path[i][j] + path[i + 1][j]) % mod;
                    }
                    if (dp[i][j + 1] == max) {
                        path[i][j] = (path[i][j] + path[i][j + 1]) % mod;
                    }
                    if (dp[i + 1][j + 1] == max) {
                        path[i][j] = (path[i][j] + path[i + 1][j + 1]) % mod;
                    }
                }
            }
        }
        
        if (path[0][0] == 0) {
            return new int[] {0, 0};
        }
        return new int[] {dp[0][0], path[0][0]};
    }
}



