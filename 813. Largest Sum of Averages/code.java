//We partition a row of numbers A into at most K adjacent (non-empty) groups, 
//then our score is the sum of the average of each group. What is the largest score we can achieve?

//Note that our partition must use every number in A, and that scores are not necessarily integers.

//Example:
//Input: 
//A = [9,1,2,3,9]
//K = 3
//Output: 20
//Explanation: 
//The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
//We could have also partitioned A into [9, 1], [2], [3, 9], for example.
//That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.

//Note:
//
//1 <= A.length <= 100.
//1 <= A[i] <= 10000.
//1 <= K <= A.length.
//Answers within 10^-6 of the correct answer will be accepted as correct.




class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        int len = A.length;
        double[] prefixSum = new double[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] += prefixSum[i] + A[i];
        }

        double[][] dp = new double[len][K + 1];
        for (int i = 0; i < len; i++) {
            dp[i][1] = (prefixSum[i + 1] - prefixSum[0]) / (i + 1);
            for (int j = 0; j < i; j++) {
                for (int k = 2; k <= K; k++) {
                    dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + (prefixSum[i + 1] - prefixSum[j + 1]) / (i - j));
                }
            }
        }
        return dp[len - 1][K];
    }
}



