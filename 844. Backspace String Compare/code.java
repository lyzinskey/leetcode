//Given two strings S and T, return if they are equal when both are typed into empty text editors. 
//# means a backspace character.

//Example 1:
//
//Input: S = "ab#c", T = "ad#c"
//Output: true
//Explanation: Both S and T become "ac".

//Example 2:
//
//Input: S = "ab##", T = "c#d#"
//Output: true
//Explanation: Both S and T become "".

//Example 3:
//
//Input: S = "a##c", T = "#a#c"
//Output: true
//Explanation: Both S and T become "c".

//Example 4:
//
//Input: S = "a#c", T = "b"
//Output: false
//Explanation: S becomes "c" while T becomes "b".

//Note:
//
//1 <= S.length <= 200
//1 <= T.length <= 200
//S and T only contain lowercase letters and '#' characters.

//Follow up:
//
//Can you solve it in O(N) time and O(1) space?




class Solution {
    public boolean backspaceCompare(String S, String T) {            
        int i = S.length() - 1;
        int j = T.length() - 1;
        int cnt1 = 0, cnt2 = 0;//number of '#';
        while (i >= 0 || j >= 0) {
            while (i >= 0 && (S.charAt(i) == '#' || cnt1 > 0)) {
                if (S.charAt(i) == '#') {
                    cnt1++;
                } else {
                    cnt1--;
                }
                i--;
            }
            while (j >= 0 && (T.charAt(j) == '#' || cnt2 > 0)) {
                if (T.charAt(j) == '#') {
                    cnt2++;
                } else {
                    cnt2--;
                }
                j--;
            }
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--;
                j--;
            } else {
                return i == -1 && j == -1;
            }
        }
        return true;
    }
}



