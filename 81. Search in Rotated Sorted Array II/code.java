//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

//(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

//You are given a target value to search. If found in the array return true, otherwise return false.

//Example 1:
//
//Input: nums = [2,5,6,0,0,1,2], target = 0
//Output: true

//Example 2:
//
//Input: nums = [2,5,6,0,0,1,2], target = 3
//Output: false

//Follow up:
//This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
//Would this affect the run-time complexity? How and why?



// Time: avg O(logn), worst case O(n)
// Space: O(1)
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int left = 0;
        int right = nums.length - 1;        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            /*
            The only difference is that due to the existence of duplicates, 
            we can have nums[left] == nums[mid] and in that case, 
            the first half could be out of order 
            (i.e. NOT in the ascending order, e.g. [3 1 2 3 3 3 3]) 
            and we have to deal this case separately. 
            In that case, it is guaranteed that nums[right] also equals to nums[mid], 
            so what we can do is to check if nums[mid] == nums[left] == nums[right] 
            before the original logic, 
            and if so, we can move left and right both towards the middle by 1. and repeat.
            */
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}




//    In-place, O(1) extra space and O(n) time.
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        for (int index = 0; index < nums.length - 1; index++) {
            if (nums[index] > nums[index + 1]) {
                rotation(nums, 0, index);
                rotation(nums, index + 1, nums.length - 1);
                rotation(nums, 0, nums.length - 1);
            }
        }
        return binarySearch(nums, target);
    }

    //    rotate character by character in the given range
    private static void rotation(int[] nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    private boolean binarySearch(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
                // or start = mid + 1
            } else {
                end = mid;
                // or end = mid - 1
            }
        }

        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }
        return false;
    }


