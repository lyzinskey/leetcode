//Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

//Example:
//
//Input: nums = [3,5,2,1,6,4]
//Output: One possible answer is [3,5,1,6,2,4]



//开始是排序以后然后把奇数位和后面的交换一下， 但是那样复杂度是O(N*lgN)
//但是这个题目其实可以直接一次遍历， 步骤如下
//a. 先对前面两位进行判断或者交换， 使得 nums[0] <= nums[1]， 这时候前面两个数就已经满足要求了
//b. 然后如果nums[2] <= nums[1], 那么, 前面三个数就已经符合要求了， 继续处理后面的数
//如果nums[2] > nums[1], 那么， 可以把他们交换， 因为nums[0] <= nums[1], 而nums[2] > nums[1],
//那么， 必然有nums[0] < nums[2], 所以，交换以后也数符合要求的；
//也就是说交换不改变前面的结果。
//c. 小于符合也一样， 所以后面的结果也都一样, 每次对当前数操作不会改变前面已经完成的结果。

// swap
// O(n)
class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 0) {
                if (nums[i] > nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            } else {
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// sort
// Time: O(nlogn)
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i += 2) {
            if (i + 1 < nums.length) {
                int temp = nums[i + 1];
                nums[i + 1] = nums[i];
                nums[i] = temp;
            }
        }
    }
}



