//Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

//Return the intersection of these two interval lists.

//(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  
//The intersection of two closed intervals is a set of real numbers that is either empty, 
//or can be represented as a closed interval.  
//For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

//Example 1:
//
//Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
//Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
//Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 
//Note:
//
//0 <= A.length < 1000
//0 <= B.length < 1000
//0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
//NOTE: input types have been changed on April 15, 2019. 
//Please reset to default code definition to get new method signature.




class Solution {
    // Time: O(n)
    // Space: O(n)
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList();
        int i1 = 0;
        int i2 = 0;
        int start = 0;
        int end = 0;
        
        while (i1 < A.length && i2 < B.length) {          
          start = Math.max(A[i1][0], B[i2][0]);
          end = Math.min(A[i1][1], B[i2][1]);
          if (start <= end) {
            list.add(new int[]{start, end});
          }
          
          if (A[i1][1] < B[i2][1]) {
              i1++;
          }            
          else {
              i2++;
          }            
        }
        
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            int[] arr = list.get(i);
            res[i][0] = arr[0];
            res[i][1] = arr[1];
        }
        return res;        
    }
}



