//Given an array of size n, find the majority element. 
//The majority element is the element that appears more than ⌊ n/2 ⌋ times.

//You may assume that the array is non-empty and the majority element always exist in the array.

//Example 1:
//
//Input: [3,2,3]
//Output: 3

//Example 2:
//
//Input: [2,2,1,1,1,2,2]
//Output: 2




class Solution {
    public int majorityElement(int[] array) {        
        int counter = 0;
        int major = array[0];
    
        for (int i = 0; i < array.length; i++) {
            if (counter != 0) {
                if (array[i] == major) {
                counter++;
                } else {
                    counter--;
                }
            } else {
                major = array[i];
                counter++;
            }
        }
        return major;
    }    
}



