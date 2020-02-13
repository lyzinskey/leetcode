//Given an array of integers nums and a positive integer k, 
//find whether it's possible to divide this array into sets of k consecutive numbers
//Return True if its possible otherwise return False.

//Example 1:
//
//Input: nums = [1,2,3,3,4,4,5,6], k = 4
//Output: true
//Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].

//Example 2:
//
//Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
//Output: true
//Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].

//Example 3:
//
//Input: nums = [3,3,2,2,1,1], k = 3
//Output: true

//Example 4:
//
//Input: nums = [1,2,3,4], k = 3
//Output: false
//Explanation: Each array should be divided in subarrays of size 3.

//Constraints:
//
//1 <= nums.length <= 10^5
//1 <= nums[i] <= 10^9
//1 <= k <= nums.length




class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }

        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        for (int num : nums) {
            if (!treemap.containsKey(num)) {
                treemap.put(num, 0);
            }
            treemap.put(num, treemap.get(num) + 1);
        }

        while (!treemap.isEmpty()) {
            int first = treemap.firstKey();
            for (int i = 0; i < k; i++) {
                int next = first + i;
                if (!treemap.containsKey(next)) {
                    return false;
                }
                treemap.put(next, treemap.get(next) - 1);
                if (treemap.get(next) == 0) {
                    treemap.remove(next);
                }
            }
        }
        return true;
    } 
}



