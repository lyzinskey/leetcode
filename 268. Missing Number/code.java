//Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

//Example 1:
//
//Input: [3,0,1]
//Output: 2

//Example 2:
//
//Input: [9,6,4,2,3,5,7,0,1]
//Output: 8

//Note:
//Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?


// use hashset
//

    public int missingNumber(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        
        for (int num : nums) {
            hashSet.add(num);
        }
        
        for (int i = 0; i <= nums.length; i++) {
            if (!hashSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }
    
    

// bit operation
//
class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0;
        for (int i = 0; i <= nums.length; i++) {
            xor ^= i;
        }

        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }

        return xor;
    }
}



