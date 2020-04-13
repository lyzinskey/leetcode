//Given an array of integers, 
//find out whether there are two distinct indices i and j in the array 
//such that the absolute difference between nums[i] and nums[j] is at most t 
//and the absolute difference between i and j is at most k.

//Example 1:
//
//Input: nums = [1,2,3,1], k = 3, t = 0
//Output: true

//Example 2:
//
//Input: nums = [1,0,1,1], k = 1, t = 2
//Output: true

//Example 3:
//
//Input: nums = [1,5,9,1,5,9], k = 2, t = 3
//Output: false




class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket) 
                || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t)) {
                return true;
            }
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }
}



