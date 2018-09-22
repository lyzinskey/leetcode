//Given n non-negative integers representing an elevation map where the width of each bar is 1, 
//compute how much water it is able to trap after raining.

//http://www.leetcode.com/static/images/problemset/rainwatertrap.png
//The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
//In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

//Example:
//
//Input: [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6




class Solution {
    public int trap(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
    
        int[] left = new int[array.length];
        int[] right = new int[array.length];
    
        left[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            left[i] = Math.max(left[i - 1], array[i]);
        }
        right[right.length - 1] = array[array.length - 1];
        for (int i = array.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], array[i]);
        }
    
        int[] water = new int[array.length];
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            water[i] = Math.min(left[i], right[i]) - array[i];
            sum += water[i];
        }
        return sum;
    }    
}



