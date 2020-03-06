//Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

//Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
//Each vowel 'a' may only be followed by an 'e'.
//Each vowel 'e' may only be followed by an 'a' or an 'i'.
//Each vowel 'i' may not be followed by another 'i'.
//Each vowel 'o' may only be followed by an 'i' or a 'u'.
//Each vowel 'u' may only be followed by an 'a'.
//Since the answer may be too large, return it modulo 10^9 + 7.

//Example 1:
//
//Input: n = 1
//Output: 5
//Explanation: All possible strings are: "a", "e", "i" , "o" and "u".

//Example 2:
//
//Input: n = 2
//Output: 10
//Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".

//Example 3: 
//
//Input: n = 5
//Output: 68

//Constraints:
//
//1 <= n <= 2 * 10^4




class Solution {
    // vowel[]: represents the output ending with 5 vowels
    // vowel[0]: ending with a
    // vowel[1]: ending with e
    // vowel[2]: ending with i
    // vowel[3]: ending with o
    // vowel[4]: ending with u
    //
    // Time: O(n)
    // Space: O(1)
    public int countVowelPermutation(int n) {
        int mod = 1000000007;
        long[] vowel = new long[5];
        for (int i = 0; i < 5; i++) {
            vowel[i] = 1;
        }
        
        for (int j = 1; j < n; j++) {
            long tempA = vowel[1] + vowel[2] + vowel[4];
            long tempE = vowel[0] + vowel[2];
            long tempI = vowel[1] + vowel[3];
            long tempO = vowel[2];
            long tempU = vowel[2] + vowel[3];
            
            vowel[0] = tempA % mod;
            vowel[1] = tempE % mod;
            vowel[2] = tempI % mod;
            vowel[3] = tempO % mod;
            vowel[4] = tempU % mod;
        }        
        
        int res = 0;
        for (int i = 0; i < 5; i++) {
            res += vowel[i];
            res %= mod;
        }
        return res;            
    }
}



