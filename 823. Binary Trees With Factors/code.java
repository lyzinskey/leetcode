//Given an array of unique integers, each integer is strictly greater than 1.

//We make a binary tree using these integers and each number may be used for any number of times.

//Each non-leaf node's value should be equal to the product of the values of it's children.

//How many binary trees can we make?  Return the answer modulo 10 ** 9 + 7.

//Example 1:
//
//Input: A = [2, 4]
//Output: 3
//Explanation: We can make these trees: [2], [4], [4, 2, 2]

//Example 2:
//
//Input: A = [2, 4, 5, 10]
//Output: 7
//Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].

//Note:
//
//1 <= A.length <= 1000.
//2 <= A[i] <= 10 ^ 9.





class Solution {
    // dp[c]: num of trees rooted at c
    // dp[c] = sum(dp[a] * dp[b]) where c = a * b and a, b, c in A
    //
    // Time: O(n^2)
    // Space: O(n)
    public int numFactoredBinaryTrees(int[] A) {
        int mod = 1000000007;        
        Arrays.sort(A);
        Map<Integer, Long> dp = new HashMap<>();
        
        for (int i = 0; i < A.length; i++) {
            dp.put(A[i], (long) 1);
            for (int j = 0; j < i; j++) {
                if (A[i] % A[j] == 0 && dp.containsKey(A[i] / A[j])) {
                    long num = dp.get(A[j]) * dp.get(A[i] / A[j]);
                    dp.put(A[i], (dp.get(A[i]) + num) % mod);                     
                }
            }
        }
        
        long res = 0;
        for (long num : dp.values()) {
            res = (res + num) % mod;
        }
        return (int) res;
    }
}



