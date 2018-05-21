//Given an array, rotate the array to the right by k steps, where k is non-negative.

//Example 1:
//
//Input: [1,2,3,4,5,6,7] and k = 3
//Output: [5,6,7,1,2,3,4]
//Explanation:
//rotate 1 steps to the right: [7,1,2,3,4,5,6]
//rotate 2 steps to the right: [6,7,1,2,3,4,5]
//rotate 3 steps to the right: [5,6,7,1,2,3,4]

//Example 2:
//
//Input: [-1,-100,3,99] and k = 2
//Output: [3,99,-1,-100]
//Explanation:
//rotate 1 steps to the right: [99,-1,-100,3]
//rotate 2 steps to the right: [3,99,-1,-100]


    public void rotate(int[] nums, int k) {
        if (nums.length != 0){
            k %= nums.length;
            rotation(nums, 0, nums.length - k - 1);
            rotation(nums, nums.length - k, nums.length - 1);
            rotation(nums, 0, nums.length - 1);
        }
    }

    private static void rotation(int[] str, int start, int end){
        for (int i = start, j = end; i < j; i++, j--) {
            int temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }
