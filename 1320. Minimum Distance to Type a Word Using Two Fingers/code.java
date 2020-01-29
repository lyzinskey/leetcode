//https://assets.leetcode.com/uploads/2020/01/02/leetcode_keyboard.png
//
//You have a keyboard layout as shown above in the XY plane, 
//where each English uppercase letter is located at some coordinate, 
//for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), 
//the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).
//
//Given the string word, return the minimum total distance to type such string using only two fingers. 
//The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|. 
//
//Note that the initial positions of your two fingers are considered free so don't count towards your total distance, 
//also your two fingers do not have to start at the first letter or the first two letters.

//Example 1:
//
//Input: word = "CAKE"
//Output: 3
//Explanation: 
//Using two fingers, one optimal way to type "CAKE" is: 
//Finger 1 on letter 'C' -> cost = 0 
//Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2 
//Finger 2 on letter 'K' -> cost = 0 
//Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1 
//Total distance = 3

//Example 2:
//
//Input: word = "HAPPY"
//Output: 6
//Explanation: 
//Using two fingers, one optimal way to type "HAPPY" is:
//Finger 1 on letter 'H' -> cost = 0
//Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
//Finger 2 on letter 'P' -> cost = 0
//Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
//Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
//Total distance = 6

//Example 3:
//
//Input: word = "NEW"
//Output: 3

//Example 4:
//
//Input: word = "YEAR"
//Output: 7

//Constraints:
//
//2 <= word.length <= 300
//Each word[i] is an English uppercase letter.





class Solution {
    // recursion with memoization
    // dp[i][o]: min cost to type word[i:n], one finger is at word[i - 1], 
    // another finger is at (char) o
    //
    // dp[i][o] = min(dp[i + 1][o] + cost(p, c),    // use same finger, other finger remains
    //                dp[i + 1][p] + cost(o, c))    // use other finger, prev finger remains
    // Time: O(n * 27)
    // Space: O(n * 27)
    public int minimumDistance(String word) {
        char[] array = word.toCharArray();
        int len = word.length();
        int[][] dp = new int[len + 1][27];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);            
        }
        return dfs(0, 26, len, dp, array);
    }
    
    private int dfs(int i, int o, int len, int[][] dp, char[] array) {
        if (i == len) {
            return 0;
        }
        if (dp[i][o] != Integer.MAX_VALUE / 2) {
            return dp[i][o];
        }
        int p = i == 0 ? 26 : array[i - 1] - 'A';
        int c = array[i] - 'A';
        dp[i][o] = Math.min(dfs(i + 1, o, len, dp, array) + cost(p, c), dfs(i + 1, p, len, dp, array) + cost(o, c));
        return dp[i][o];
    }
    
    private int cost(int c1, int c2) {
        if (c1 == 26) {
            return 0;
        }
        return Math.abs(c1 / 6 - c2 / 6) + Math.abs(c1 % 6 - c2 % 6);
    }
}





class Solution {
    // dp, bottom up
    // dp[i][j]: min cost to type word[0:i], one finger is at word[i - 1], 
    // another finger is at (char) j
    //
    // dp[i + 1][j] = dp[i][j] + cost(p, c)
    // dp[i + 1][p] = dp[i][j] + cost(j, c)
    // ans = min(dp[n])
    //
    // Time: O(n * 27)
    // Space: O(n * 27) can be optimized to O(27)
    public int minimumDistance(String word) {
        int len = word.length();
        int[][] dp = new int[len + 1][27];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);            
        }
        dp[0][26] = 0;
        
        for (int i = 0; i < len; i++) {
            int prev = i == 0 ? 26 : word.charAt(i - 1) - 'A';
            int curr = word.charAt(i) - 'A';
            for (int j = 0; j <= 26; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + cost(prev, curr));
                dp[i + 1][prev] = Math.min(dp[i + 1][prev], dp[i][j] + cost(j, curr));
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 27; i++) {
            res = Math.min(res, dp[len][i]);
        }
        return res;
    }
    
    private int cost(int c1, int c2) {
        if (c1 == 26) {
            return 0;
        }
        return Math.abs(c1 / 6 - c2 / 6) + Math.abs(c1 % 6 - c2 % 6);
    }
}




