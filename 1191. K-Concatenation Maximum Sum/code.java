//Given an integer array arr and an integer k, modify the array by repeating it k times.

//For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].

//Return the maximum sub-array sum in the modified array. 
//Note that the length of the sub-array can be 0 and its sum in that case is 0.

//As the answer can be very large, return the answer modulo 10^9 + 7.

//Example 1:
//
//Input: arr = [1,2], k = 3
//Output: 9

//Example 2:
//
//Input: arr = [1,-2,1], k = 5
//Output: 2

//Example 3:
//
//Input: arr = [-1,-2], k = 7
//Output: 0
 
//Constraints:
//
//1 <= arr.length <= 10^5
//1 <= k <= 10^5
//-10^4 <= arr[i] <= 10^4




class Solution {        
    // Time: O(n)
    // Space: O(1)
    public int kConcatenationMaxSum(int[] arr, int k) {
        int modulo = 1000000007;
        int len = arr.length;
        int maxSoFar = arr[0];
        int maxEndingWithCurr = arr[0];
        
        for (int i = 1; i < len; i++) {
            maxEndingWithCurr = Math.max(maxEndingWithCurr + arr[i], arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingWithCurr);
        }

        if (k < 2) {
            return maxSoFar;
        }

        int leftSum = arr[0];
        int rightSum = arr[len - 1];
        int lMax = Math.max(0, arr[0]);
        int rMax = Math.max(0, arr[len - 1]);

        for (int i = 1; i < len; i++) {
            leftSum += arr[i];
            lMax = Math.max(lMax, leftSum);
        }

        for (int i = len - 2; i >= 0; i--) {
            rightSum += arr[i];
            rMax = Math.max(rMax, rightSum);
        }

        int headTailMax = lMax + rMax;

        if (leftSum < 0) {
            return Math.max(maxSoFar, headTailMax);
        } else {
            return Math.max(maxSoFar, (int) (headTailMax + ((k - 2) * (long) leftSum) % modulo));
        }
    }
}



