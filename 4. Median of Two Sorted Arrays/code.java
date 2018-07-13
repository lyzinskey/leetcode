//There are two sorted arrays nums1 and nums2 of size m and n respectively.

//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

//Example 1:
//nums1 = [1, 3]
//nums2 = [2]
//
//The median is 2.0

//Example 2:
//nums1 = [1, 2]
//nums2 = [3, 4]
//
//The median is (2 + 3)/2 = 2.5




// 基于 FindKth 的算法
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int length = A.length + B.length;
        
        if (length % 2 == 0) {
            return (findKth(A, B, 0, 0, length / 2) + findKth(A, B, 0, 0, length / 2 + 1)) / 2.0;
        }
        else {
            return findKth(A, B, 0, 0, length / 2 + 1) * 1.0;
        }        
    }
    
    private int findKth(int[] A, int[] B, int indexA, int indexB, int k) {
        if (indexA >= A.length) {
            return B[indexB + k - 1];
        }
        if (indexB >= B.length) {
            return A[indexA + k - 1];
        }
        
        if (k == 1) {
            return Math.min(A[indexA], B[indexB]);
        }
        
        int half_Kth_of_A = indexA + k / 2 - 1 < A.length ? A[indexA + k / 2 - 1] : Integer.MAX_VALUE;
        int half_Kth_of_B = indexB + k / 2 - 1 < B.length ? B[indexB + k / 2 - 1] : Integer.MAX_VALUE;
        
        if (half_Kth_of_A < half_Kth_of_B) {
            return findKth(A, B, indexA + k / 2, indexB, k - k / 2);
        }
        else {
            return findKth(A, B, indexA, indexB + k / 2, k - k / 2);
        }
    }    
}


