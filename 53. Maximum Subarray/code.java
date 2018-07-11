//Given an integer array nums, find the contiguous subarray (containing at least one number) 
//which has the largest sum and return its sum.

//Example:
//
//Input: [-2,1,-3,4,-1,2,1,-5,4],
//Output: 6
//Explanation: [4,-1,2,1] has the largest sum = 6.

//Follow up:
//
//If you have figured out the O(n) solution, 
//try coding another solution using the divide and conquer approach, which is more subtle.



class Solution {
    
    // 思路：Sum(i~j) = PrefixSum[j + 1] - PrefixSum[i]
    // PrefixSum[j + 1]的值是固定的，就是前j个数的和，
    // 因此想让Sum(i~j)最大，PrefixSum[i]必须最小    
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }        
        
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int minSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // sum相当于PrefixSum[j + 1]，每次累加数组中的元素            
            sum += nums[i];
            
            // max相当于Sum(i~j)，
            // 不停更新PrefixSum[j + 1] - PrefixSum[i]以保持最大            
            max = Math.max(max, sum - minSum);
            
            // 找到最小的PrefixSum[i]            
            minSum = Math.min(minSum, sum);
        }
        
        return max;
    }
}


