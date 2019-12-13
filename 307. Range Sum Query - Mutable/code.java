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


//Using Binary Index Tree
//Init the tree (include building all prefix sums) takes O(nlogn)
//Update the value of an element takes O(logn)
//Query the range sum takes O(logn)
//Space complexity: O(n)
//
class NumArray {
    private BinaryIndexTree bit;
    private int[] array;
    public NumArray(int[] nums) {
        this.bit = new BinaryIndexTree(nums.length);
        this.array = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            bit.update(i + 1, nums[i]);
            array[i] = nums[i];
        }
    }
    
    public void update(int i, int val) {
        bit.update(i + 1, val - array[i]);
        array[i] = val;
    }
    
    public int sumRange(int i, int j) {
        return bit.query(j + 1) - bit.query(i);
    }
    
    class BinaryIndexTree {
        int sums[];
        
        public BinaryIndexTree(int n) {
            this.sums = new int[n + 1];
        }
        
        public void update(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += i & -i;
            }
        }
        
        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += sums[i];
                i -= i & -i;
            }
            return sum;
        }        
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

 
 
 
