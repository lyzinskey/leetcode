//Given a string S, 
//check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

//If possible, output any possible result.  If not possible, return the empty string.

//Example 1:
//
//Input: S = "aab"
//Output: "aba"

//Example 2:
//
//Input: S = "aaab"
//Output: ""

//Note:
//
//S will consist of lowercase letters and have length in range [1, 500].




class Solution {
    public String reorganizeString(String S) {
        int[] count = new int[26];
        int max = 0;
        int letter = 0;
        int len = S.length();
        
        for (char ch : S.toCharArray()) {
            int i = ch - 'a';
            count[i]++;
            if (count[i] > max) {
                max = count[i];
                letter = i;
            }
        }
        if (max > (len + 1) / 2) {
            return "";
        }
        
        char[] array = new char[len];
        int index = 0;
        while (count[letter] > 0) {
            array[index] = (char) (letter + 'a');
            index += 2;
            count[letter]--;
        }
        
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                if (index >= len) {
                    index = 1;
                }
                array[index] = (char) (i + 'a');
                index += 2;
                count[i]--;
            }
        }
        return new String(array);
    }
}



