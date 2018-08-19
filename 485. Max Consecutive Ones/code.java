//Given a binary array, find the maximum number of consecutive 1s in this array.

//Example 1:
//Input: [1,1,0,1,1,1]
//Output: 3
//Explanation: The first two digits or the last three digits are consecutive 1s.
//             The maximum number of consecutive 1s is 3.

//Note:
//
//The input array will only contain 0 and 1.
//The length of input array is a positive integer and will not exceed 10,000




class Solution {
    public int findMaxConsecutiveOnes(int[] array) {
        // if (array.length == 0) {
        //     return 0;
        // }
        int last = array[0];
        int max = last;

        for (int i = 1; i < array.length; i++) {
            last = array[i] == 0 ? 0 : last + 1;
            max = Math.max(last, max);
        }
        return max;
    }    
}



