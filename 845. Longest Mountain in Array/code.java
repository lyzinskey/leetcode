//Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

//B.length >= 3
//There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
//(Note that B could be any subarray of A, including the entire array A.)

//Given an array A of integers, return the length of the longest mountain. 

//Return 0 if there is no mountain.

//Example 1:
//
//Input: [2,1,4,7,3,2,5]
//Output: 5
//Explanation: The largest mountain is [1,4,7,3,2] which has length 5.

//Example 2:
//
//Input: [2,2,2]
//Output: 0
//Explanation: There is no mountain.

//Note:
//0 <= A.length <= 10000
//0 <= A[i] <= 10000

//Follow up:
//Can you solve it using only one pass?
//Can you solve it in O(1) space?




class Solution {
    // Time: O(n)
    // Space: O(1)
    public int longestMountain(int[] A) {
        int res = 0;
        int i = 1;
        while (i < A.length) {
            while (i < A.length && A[i - 1] == A[i]) {
                i++;
            }
            
            int up = 0;
            while (i < A.length && A[i - 1] < A[i]) {
                i++;
                up++;
            }
            
            int down = 0;
            while (i < A.length && A[i - 1] > A[i]) {
                i++;
                down++;
            }
            
            if (up > 0 && down > 0) {
                res = Math.max(res, up + down + 1);
            }
        }
        return res;
    }
}



