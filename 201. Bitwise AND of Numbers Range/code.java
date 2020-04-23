//Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

//Example 1:
//
//Input: [5,7]
//Output: 4

//Example 2:
//
//Input: [0,1]
//Output: 0




class Solution {
    // 最后的数是该数字范围内所有的数的左边共同的部分，范围[26, 30]，它们的二进制如下：
    // 11010　　11011　　11100　　11101　　11110    
    // res = 11000
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;                
        while(m < n) {
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift;
    }
}



