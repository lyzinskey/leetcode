//Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

//Example 1:
//Input: [0,1]
//Output: 2
//Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

//Example 2:
//Input: [0,1,0]
//Output: 2
//Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
//Note: The length of the given binary array will not exceed 50,000.





class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> hashmap = new HashMap<>();
        int sum = 0;
        int result = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (sum == 0) {
                result = i + 1;
            } else if (hashmap.containsKey(sum)) {
                result = Math.max(result, i - hashmap.get(sum));
            } else {
                hashmap.put(sum, i);
            }
        }
        return result;
    }
}



