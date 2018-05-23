//Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

//Your algorithm's runtime complexity must be in the order of O(log n).

//If the target is not found in the array, return [-1, -1].

//Example 1:
//
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4]

//Example 2:
//
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1]


    //use binary search twice to solve this problem
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0){
            return result;
        }

        //first binary search, find the first position of target
        int start = 0;
        int end = nums.length - 1;
        int leftOccurrence = 0;
        int rightOccurrence = 0;

        while (start + 1 < end){
            int mid = (start + end) / 2;
            if (nums[mid] == target){
                end = mid;
            }
            else if (nums[mid] < target){
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (nums[start] == target){
            leftOccurrence = start;
        }
        else if (nums[end] == target){
            leftOccurrence = end;
        }
        else {
            return result;
        }


        //second binary search, find the last position of target
        start = 0;
        end = nums.length - 1;
        while (start + 1 < end){
            int mid = (start + end) / 2;
            if (nums[mid] == target){
                start = mid;
            }
            else if (nums[mid] < target){
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (nums[end] == target){
            rightOccurrence = end;
        }
        else if (nums[start] == target){
            rightOccurrence = start;
        }
        else {
            return result;
        }

        result[0] = leftOccurrence;
        result[1] = rightOccurrence;
        return result;
    }
    
    
