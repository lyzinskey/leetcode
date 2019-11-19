//The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

//Now your job is to find the total Hamming distance between all pairs of the given numbers.

//Example:
//Input: 4, 14, 2
//
//Output: 6
//
//Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
//showing the four bits relevant in this case). So the answer will be:
//HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

//Note:
//Elements of the given array are in the range of 0 to 10^9
//Length of the array will not exceed 10^4.




class Solution {
    // for each "column" or bit position, 
    // once you count the number of set bits 
    // you can figure out the number of pairs 
    // that will contribute to the count using combination logic.
    // Consider you have 10 numbers and only one of them is a 1 the rest are zeros. 
    // How many (1, 0) pairs can you make? 
    // Clearly you can make 9, pair the 1 with each of the other 9 zeros. 
    // If you have 2 ones, you can pair each of those with the other 8 zeros giving 2*8 = 16. 
    // Keep going and you see that you can pair each 1 with each zero 
    // so the number of pairs is just the number of 1's times the number of 0's.
    
    // Time: O(32n) -> O(n)
    // Space: O(1)
    public int totalHammingDistance(int[] nums) {        
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                count += (nums[j] >> i) & 1;
            }
            result += count * (nums.length - count);
        }
        return result;
    }
}



