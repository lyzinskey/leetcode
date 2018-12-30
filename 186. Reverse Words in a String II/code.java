//Given an input string , reverse the string word by word. 

//Example:
//
//Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
//Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]

//Note: 
//
//A word is defined as a sequence of non-space characters.
//The input string does not contain leading or trailing spaces.
//The words are always separated by a single space.
//Follow up: Could you do it in-place without allocating extra space?




class Solution {
    public void reverseWords(char[] charArray) {        
        reverse(charArray, 0, charArray.length - 1);

        int space = 0;
        for (int i = 0; i < charArray.length; i++) {
            // 待翻转单词的第一个字母
            if (charArray[i] != ' ' && (i == 0 || charArray[i - 1] == ' ')) {
                space = i;
            }
            // 待翻转单词的最后一个字母
            if (charArray[i] != ' ' && (i == charArray.length - 1 || charArray[i + 1] == ' ')) {
                reverse(charArray, space, i);
            }
        }        
    }

    private void reverse(char[] charArray, int left, int right) {
        while (left < right) {
            swap(charArray, left++, right--);
        }
    }

    private void swap(char[] charArray, int left, int right) {
        char temp = charArray[left];
        charArray[left] = charArray[right];
        charArray[right] = temp;
    }    
}



