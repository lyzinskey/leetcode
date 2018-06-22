//Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

//If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

//The replacement must be in-place and use only constant extra memory.

//Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
//
//  1,2,3 → 1,3,2
//  3,2,1 → 1,2,3
//  1,1,5 → 1,5,1



class Solution {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        
        if (nums == null || len <= 1) {
            return;
        }
        
        int i = len - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        
        if (i > 0) {
            int j = len - 1;
            while (nums[j] <= nums[i - 1]) {
                j--;
            }
            swapItems(nums, i - 1, j);
        }
        swapList(nums, i, len - 1);
    }
    
    private void swapItems(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void swapList(int[] nums, int i, int j) {
        while (i < j) {
            swapItems(nums, i, j);
            i++;
            j--;
        }
    }
}

