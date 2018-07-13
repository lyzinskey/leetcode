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
// 时间复杂度 log(n + m)
//
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




// 基于二分的算法
// 时间复杂度 O(log(range) * (log(n) + log(m)))
// 其中 range 为最小和最大的整数之间的范围。
//
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int length = A.length + B.length;
        
        if (length % 2 == 0) {
            return (findKth(A, B, length / 2) + findKth(A, B, length / 2 + 1)) / 2.0;
        }
        else {
            return findKth(A, B, length / 2 + 1) * 1.0;
        }
    }
    
    
    private int findKth(int[] A, int[] B, int k) {
        if (A.length == 0) {
            return B[k - 1];
        }
        if (B.length == 0) {
            return A[k - 1];
        }
        
        int start = Math.min(A[0], B[0]);
        int end = Math.max(A[A.length - 1], B[B.length - 1]);
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (countSmallerOrEqual(A, mid) + countSmallerOrEqual(B, mid) < k) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (countSmallerOrEqual(A, start) + countSmallerOrEqual(B, start) >= k) {
            return start;
        }
        else {
            return end;
        }
    }
    
    private int countSmallerOrEqual(int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (A[mid] <= target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (A[start] > target) {
            return start;
        }
        else if (A[end] > target) {
            return end;
        }
        else {
            return A.length;
        }
    }    
}


