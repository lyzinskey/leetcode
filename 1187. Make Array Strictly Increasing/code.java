//Given two integer arrays arr1 and arr2, 
//return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.

//In one operation, 
//you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].

//If there is no way to make arr1 strictly increasing, return -1.

//Example 1:
//
//Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
//Output: 1
//Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].

//Example 2:
//
//Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
//Output: 2
//Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].

//Example 3:
//
//Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
//Output: -1
//Explanation: You can't make arr1 strictly increasing.
 
//Constraints:
//
//1 <= arr1.length, arr2.length <= 2000
//0 <= arr1[i], arr2[i] <= 10^9




class Solution {
    // swap[i][j]: min cost to make a[0~i] valid by a[i] = b[j]
    // keep[i]: mon cost to make a[0~i] valid by keeping a[i]
    //
    // init:
    // keep[0] = 0, num of a[0] is always valid, no cost
    // swap[0][*] = 1, num of 1 operation to assign b[j] to a[0]
    //
    // try all pairs(a[i], b[j])
    // case1: a[i] > a[i - 1], keep[i] = keep[i - 1]
    // case2: b[j] > a[i - 1], swap[i][j] = keep[i - 1] + 1
    // case3: a[i] > b[j], keep[i] = min(swap[i - 1][k]), where k <= j
    // case4: b[j] > b[k] (always true), swap[i][j] = min(swap[i - 1][k] + 1), where k < j
    //
    // ans: min( min(swap[m - 1]), keep[m - 1] )
    //
    // Time: O(mn)
    // Space: O(mn) -> O(m + n)
    public int makeArrayIncreasing(int[] a, int[] b) {
        int inf = 10000000;
        Arrays.sort(b);

        int len1 = a.length;
        int len2 = removeDuplicates(b);
        int[] keep = new int[len1];
        Arrays.fill(keep, inf);
        keep[0] = 0;

        int[][] swap = new int[len1][len2];
        Arrays.fill(swap[0], 1);
        for (int i = 1; i < len1; i++) {
            Arrays.fill(swap[i], inf);
        }

        for (int i = 1; i < len1; i++) {
            int min_keep = inf;
            int min_swap = inf;
            for (int j = 0; j < len2; j++) {
                if (j > 0) {
                    min_swap = Math.min(min_swap, swap[i - 1][j - 1] + 1);
                }
                if (a[i] > b[j]) {
                    min_keep = Math.min(min_keep, swap[i - 1][j]);
                }
                if (a[i] > a[i - 1]) {
                    keep[i] = keep[i - 1];
                }
                if (b[j] > a[i - 1]) {
                    swap[i][j] = keep[i - 1] + 1;
                }
                swap[i][j] = Math.min(swap[i][j], min_swap);
                keep[i] = Math.min(keep[i], min_keep);
            }
        }

        int min_s = Integer.MAX_VALUE;
        for (int num : swap[swap.length - 1]) {
            min_s = Math.min(min_s, num);
        }
        int res = Math.min(min_s, keep[keep.length - 1]);
        return res >= inf ? -1 : res;
    }

    public int removeDuplicates(int[] arr) {
        int j = 0;
        int i = 1;

        while (i < arr.length) {
            if (arr[i] != arr[j]) {
                j++;
                arr[j] = arr[i];
            }
            i++;
        }
        return j + 1;
    }
}



