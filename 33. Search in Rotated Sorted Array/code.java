//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

//(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

//You are given a target value to search. If found in the array return its index, otherwise return -1.
//You may assume no duplicate exists in the array.
//Your algorithm's runtime complexity must be in the order of O(log n).

//Example 1:
//
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4

//Example 2:
//
//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1


    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        
        // 这里 start + 1 < end 是为了防止死循环，start和end之间必须间隔一个数字
        // 例：nums = [1, 3], target = 2
        // 若改为 start < end 会导致死循环
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > nums[start]){
                // 注意 “<=“ 不能忘
                // 例：nums = [1, 3, 5], target = 1
                // 若改为 "<" 会导致答案错误
                if (nums[start] <= target && nums[mid] >= target){
                    end = mid;
                }
                else {
                    start = mid;
                }
            }
            else {
                if (nums[mid] <= target && nums[end] >= target){
                    start = mid;
                }
                else {
                    end = mid;
                }
            }
        }

        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }


