//Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. 
//Find that single one.

//Note:
//
//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

//Example 1:
//
//Input: [2,2,3,2]
//Output: 3

//Example 2:
//
//Input: [0,1,0,1,0,1,99]
//Output: 99




class Solution {
    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            // first appearence: 
            // add num to seen_once 
            // don't add to seen_twice because of presence in seen_once

            // second appearance: 
            // remove num from seen_once 
            // add num to seen_twice

            // third appearance: 
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }
        return seenOnce;
    }
}



