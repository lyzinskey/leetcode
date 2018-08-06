//Reverse bits of a given 32 bits unsigned integer.

//Example:
//
//Input: 43261596
//Output: 964176192
//Explanation: 43261596 represented in binary as 00000010100101000001111010011100, 
//             return 964176192 represented in binary as 00111001011110000010100101000000.

//Follow up:
//If this function is called many times, how would you optimize it?



public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int i = 0;
        int j = 31;
        
        while (i < j) {
            n = reversePair(n, i++, j--);
        }
        
        return n;
    }
    
    private int reversePair(int x, int i, int j) {
        int left_bit = (x >> i) & 1;
        int right_bit = (x >> j) & 1;
        
        if (left_bit != right_bit) {
            x ^= ((1 << i) | (1 << j));
        }
        
        return x;
    }
}



