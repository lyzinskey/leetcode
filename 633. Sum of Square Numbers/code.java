//Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

//Example 1:
//Input: 5
//Output: True
//Explanation: 1 * 1 + 2 * 2 = 5

//Example 2:
//Input: 3
//Output: False



class Solution {
    public boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }
        
        for (long a = 0; a * a <= c; a++) {
            int b = c - (int) (a * a);
            if (binarySearch(b, 0, b)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean binarySearch(int target, long start, long end) {
        while (start <= end) {
            long mid = start + (end - start) / 2;
            if (mid * mid == target) {
                return true;
            }
            else if (mid * mid > target) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        
        return false;
    }    
}


