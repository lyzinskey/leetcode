//Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

//The update(i, val) function modifies nums by updating the element at index i to val.

//Example:
//
//Given nums = [1, 3, 5]
//
//sumRange(0, 2) -> 9
//update(1, 2)
//sumRange(0, 2) -> 8

//Note:
//The array is only modifiable by the update function.
//You may assume the number of calls to update and sumRange function is distributed evenly.




class NumArray {
    private int[] arr, bit;
    
    /**
     * @return: nothing
     */
    public NumArray(int[] nums) {
        arr = new int[nums.length];
        bit = new int[nums.length + 1];
        
        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        }
    }
    
    public void update(int index, int val) {
        int delta = val - arr[index];
        arr[index] = val;
        
        for (int i = index + 1; i <= arr.length; i = i + lowbit(i)) {
            bit[i] += delta;
        }
    }
    
    public int getPrefixSum(int index) {
        int sum = 0;
        for (int i = index + 1; i > 0; i = i - lowbit(i)) {
            sum += bit[i];
        }
        return sum;
    }
    
    private int lowbit(int x) {
        return x & (-x);
    }

    public int sumRange(int left, int right) {
        return getPrefixSum(right) - getPrefixSum(left - 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
 
 
 
