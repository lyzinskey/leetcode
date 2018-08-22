//Shuffle a set of numbers without duplicates.

//Example:
//
//  // Init an array with set 1, 2, and 3.
//int[] nums = {1,2,3};
//Solution solution = new Solution(nums);
//
//  // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
//solution.shuffle();
//
//  // Resets the array back to its original configuration [1,2,3].
//solution.reset();
//
//  // Returns the random shuffling of array [1,2,3].
//solution.shuffle();



class Solution {
    private int[] origin;
    private int[] shuffle;
    
    public Solution(int[] nums) {
        this.origin = nums.clone();
        this.shuffle = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < shuffle.length; i++) {
            int num = random.nextInt(shuffle.length - i) + i;
            swap(shuffle, num, i);
        }
        return shuffle;
    }
    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }      
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
 
 
 
 
