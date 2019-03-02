//Given an array of non-negative integers, you are initially positioned at the first index of the array.

//Each element in the array represents your maximum jump length at that position.

//Your goal is to reach the last index in the minimum number of jumps.

//Example:
//
//Input: [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2.
//    Jump 1 step from index 0 to 1, then 3 steps to the last index.

//Note:
//You can assume that you can always reach the last index.




class Solution {
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        
        int[] dp = new int[nums.length];
        dp[dp.length - 1] = 0;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= nums.length - 1) {
                dp[i] = 1;
            } else if (nums[i] == 0) {
                dp[i] = -1;
            } else {
                int min = Integer.MAX_VALUE;
                for (int j = i + 1; j <= i + nums[i]; j++) {
                    if (dp[j] > -1 && dp[j] < min) {
                        min = dp[j];
                    }           
                }
                dp[i] = min == Integer.MAX_VALUE ? -1 : min + 1;
            }
        }
        return dp[0];
    }
}



