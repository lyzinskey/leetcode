//Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

//Example 1:
//Input: 16
//Output: true

//Example 2:
//Input: 5
//Output: false

//Follow up: Could you solve it without loops/recursion?



class Solution {
    public boolean isPowerOfFour(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;        
    }
}



