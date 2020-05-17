//Given an array of integers arr.

//We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).

//Let's define a and b as follows:
//
//a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
//b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
//Note that ^ denotes the bitwise-xor operation.
//
//Return the number of triplets (i, j and k) Where a == b.

//Example 1:
//
//Input: arr = [2,3,1,6,7]
//Output: 4
//Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)

//Example 2:
//
//Input: arr = [1,1,1,1,1]
//Output: 10

//Example 3:
//
//Input: arr = [2,3]
//Output: 0

//Example 4:
//
//Input: arr = [1,3,5,7,9]
//Output: 3

//Example 5:
//
//Input: arr = [7,11,12,9,5,2,7,17,22]
//Output: 8
 
//Constraints:
//
//1 <= arr.length <= 300
//1 <= arr[i] <= 10^8




class Solution {
    // solution 1
    //
    // Time: O(n ^ 3)
    // Space: O(n)
    //
    // xor[i] = A[0] ^ A[1] ^ ... ^ A[i - 1]
    // A[i] ^ A[i + 1] ^ ... ^ A[j] 
    // = (A[0] ^ ... ^ A[i - 1]) ^ (A[0] ^ ... ^ A[i - 1]) ^ (A[i] ^ ... ^ A[j])
    // = sor[i] ^ sor[j + 1]
    //
    // a = xor[i] ^ xor[j] (i ~ j - 1)
    // b = xor[j] ^ xor[k + 1] (j ~ k)
    public int countTriplets(int[] arr) {
        int len = arr.length;
        int[] xor = new int[len + 1];
        int res = 0;
        
        for (int i = 0; i < len; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }
        
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j; k < len; k++) {
                    int a = xor[i] ^ xor[j];
                    int b = xor[j] ^ xor[k + 1];
                    if (a == b) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}




class Solution {
    // solution 2
    //
    // Time: O(n ^ 2)
    // Space: O(n)
    //
    // a = b -> a ^ b = 0
    // (A[i] ^ ... ^ A[j - 1]) = (A[j] ^ ... ^ A[k])
    // -> (A[i] ^ ... ^ A[j - 1]) ^ (A[j] ^ ... ^ A[k]) = 0
    //
    // A[0] ^ A[1] ^ ... ^ A[k]
    // = (A[0] ^ A[1] ^ ... ^ A[i - 1]) ^ (A[i] ^ ... ^ A[j - 1]) = (A[j] ^ ... ^ A[k])
    // = A[0] ^ A[1] ^ ... ^ A[i - 1]
    //
    // So the problem is converted to find all pairs (i, k) such that xor[i] == xor[k + 1]
    // For each pair of (i, k), we can pick j at i + 1 to k, 
    // so there are k - i different pair of (i, j, k)
    public int countTriplets(int[] arr) {
        int len = arr.length;
        int[] xor = new int[len + 1];
        int res = 0;
        
        for (int i = 0; i < len; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }
        
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (xor[i] == xor[j + 1]) {
                    res += j - i;
                }
            }
        }
        return res;
    }
}





