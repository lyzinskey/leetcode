//Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

//Return the maximum possible length of s.

//Example 1:
//
//Input: arr = ["un","iq","ue"]
//Output: 4
//Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
//Maximum length is 4.

//Example 2:
//
//Input: arr = ["cha","r","act","ers"]
//Output: 6
//Explanation: Possible solutions are "chaers" and "acters".

//Example 3:
//
//Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
//Output: 26

//Constraints:
//
//1 <= arr.length <= 16
//1 <= arr[i].length <= 26
//arr[i] contains only lower case English letters.




class Solution {
    // use int to represent arr strings
    // "abc" = 00000111
    // "def" = 00111000
    // & = 00000000 -> valid
    // | = 00111111
    //
    // "abc" = 00000111
    // "cef" = 00110100
    // & = 00000100 -> invalid
    // | = 00110111
    //
    // if the number of bits is less than the length of string,
    // it means this string contains duplicate characters
    //
    // Time: O(2^n)
    // Space: O(n)
    public int maxLength(List<String> arr) {
        List<Integer> combination = new ArrayList<>();
        combination.add(0);
        int result = 0;
        
        for (String str : arr) {
            int mask = 0;
            for (char ch : str.toCharArray()) {
                mask |= 1 << (ch - 'a');
            }
            if (Integer.bitCount(mask) != str.length()) {
                continue;
            }
            int size = combination.size();
            for (int i = 0; i < size; i++) {
                int word = combination.get(i);
                if ((word & mask) != 0) {
                    continue;
                }
                int newStr = word | mask;
                combination.add(newStr);
                result = Math.max(result, Integer.bitCount(newStr));
            }
        }
        return result;
    }
}




