//Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

//You have the following 3 operations permitted on a word:
//
//Insert a character
//Delete a character
//Replace a character

//Example 1:
//
//Input: word1 = "horse", word2 = "ros"
//Output: 3
//Explanation: 
//horse -> rorse (replace 'h' with 'r')
//rorse -> rose (remove 'r')
//rose -> ros (remove 'e')

//Example 2:
//
//Input: word1 = "intention", word2 = "execution"
//Output: 5
//Explanation: 
//intention -> inention (remove 't')
//inention -> enention (replace 'i' with 'e')
//enention -> exention (replace 'n' with 'x')
//exention -> exection (replace 'n' with 'c')
//exection -> execution (insert 'u')




class Solution {
    public int minDistance(String one, String two) {
        int lengthOne = one.length();
        int lengthTwo = two.length();
        if (lengthOne == 0 && lengthTwo == 0) {
            return 0;
        }
        if (lengthOne == 0) {
            return lengthTwo;
        }
        if (lengthTwo == 0) {
            return lengthOne;
        }

        int[][] DP = new int[lengthOne + 1][lengthTwo + 1];
        DP[0][0] = 0;
        for (int i = 0; i < lengthOne + 1; i++) {
            for (int j = 0; j < lengthTwo + 1; j++) {
                if (i == 0) {
                    DP[i][j] = j;
                }
                else if (j == 0) {
                    DP[i][j] = i;
                }
                else if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    DP[i][j] = DP[i - 1][j - 1];
                }
                else {
                    DP[i][j] = getMin(DP[i - 1][j - 1], DP[i - 1][j], DP[i][j - 1]) + 1;
                }
            }
        }
        return DP[lengthOne][lengthTwo];
    }

    private int getMin(int a, int b, int c) {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }    
}



