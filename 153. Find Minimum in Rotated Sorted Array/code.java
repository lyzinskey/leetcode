//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

//Find the minimum element.

//You may assume no duplicate exists in the array.

//Example 1:
//
//Input: [3,4,5,1,2]
//Output: 1

//Example 2:
//
//Input: [4,5,6,7,0,1,2]
//Output: 0


    //    In-place, O(1) extra space and O(n) time.
    public int findMin(int[] nums) {

        if (nums.length > 0) {
            for (int index = 0; index < nums.length - 1; index++) {
                if (nums[index] > nums[index + 1]) {
                    rotation(nums, 0, index);
                    rotation(nums, index + 1, nums.length - 1);
                    rotation(nums, 0, nums.length - 1);
                }
            }
        }
        return nums[0];
    }

    //    rotate character by character in the given range
    private static void rotation(int[] nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
