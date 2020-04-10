//You are given a sorted array consisting of only integers where every element appears exactly twice, 
//except for one element which appears exactly once. Find this single element that appears only once.

//Example 1:
//
//Input: [1,1,2,3,3,4,4,8,8]
//Output: 2

//Example 2:
//
//Input: [3,3,7,7,10,11,11]
//Output: 10

//Note: Your solution should run in O(log n) time and O(1) space.




class Solution {
    // Time: O(logn)
    // Space: O(1)    
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int n = mid % 2 == 0 ? mid + 1 : mid - 1;            
            if (nums[mid] == nums[n]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}



