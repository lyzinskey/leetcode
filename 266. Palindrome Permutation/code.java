//Given a string, determine if a permutation of the string could form a palindrome.

//Example 1:
//
//Input: "code"
//Output: false

//Example 2:
//
//Input: "aab"
//Output: true

//Example 3:
//
//Input: "carerac"
//Output: true




class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] array = new int[128];
        for (char ch : s.toCharArray()) {
            array[(int) ch]++;
        }
        int counter = 0;
        for (int num : array) {
            if (num % 2 == 1) {
                counter++;
            }
            if (counter > 1) {
                return false;
            }
        }
        return true;
    }
}



