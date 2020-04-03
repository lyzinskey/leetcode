//You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
//Now you have 2 symbols + and -. 
//For each integer, you should choose one from + and - as its new symbol.

//Find out how many ways to assign symbols to make sum of integers equal to target S.

//Example 1:
//Input: nums is [1, 1, 1, 1, 1], S is 3. 
//Output: 5
//Explanation: 
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//
//There are 5 ways to assign symbols to make the sum of nums be target 3.

//Note:
//The length of the given array is positive and will not exceed 20.
//The sum of elements in the given array will not exceed 1000.
//Your output answer is guaranteed to be fitted in a 32-bit integer.




class Solution {
    // Time: O(n)
    // Space: O(n)
    public int findTargetSumWays(int[] nums, int s) {
        Map<Integer, Integer> dp = new HashMap();
        dp.put(0, 1);
        for (int num : nums) {
            Map<Integer, Integer> dp2 = new HashMap();
            for (int tempSum : dp.keySet()) {
                int key1 = tempSum + num;
                dp2.put(key1, dp2.getOrDefault(key1, 0) + dp.get(tempSum));
                int key2 = tempSum - num;
                dp2.put(key2, dp2.getOrDefault(key2, 0) + dp.get(tempSum));
            }
            dp = dp2;
        }
        return dp.getOrDefault(s, 0);
    }  
}



