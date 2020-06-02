//Given an integer array nums, find the contiguous subarray within an array 
//(containing at least one number) which has the largest product.

//Example 1:
//
//Input: [2,3,-2,4]
//Output: 6
//Explanation: [2,3] has the largest product 6.

//Example 2:
//
//Input: [-2,0,-1]
//Output: 0
//Explanation: The result cannot be 2, because [-2,-1] is not a subarray.




class Solution {
    // keep track of max subarray and min subarray
    // Time: O(n)
    // Space: O(1)
    public int maxProduct(int[] nums) {        
        int max = nums[0];
        int min = nums[0];
        int res = Math.max(max, min);
        int prevMax = max;
        int prevMin = min;
        for (int i = 1; i < nums.length; i++) {
            prevMax = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            prevMin = Math.min(nums[i], Math.min(max * nums[i], min * nums[i]));            
            // 这里只要比较res和prevMax就行了
            res = Math.max(res, prevMax);
            max = prevMax;
            min = prevMin;
        }
        return res == Integer.MIN_VALUE ? Math.max(max, min) : res;
    }
}



