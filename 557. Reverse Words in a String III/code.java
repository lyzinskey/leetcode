//Given a string, you need to reverse the order of characters in each word within a sentence 
//while still preserving whitespace and initial word order.

//Example 1:
//Input: "Let's take LeetCode contest"
//Output: "s'teL ekat edoCteeL tsetnoc"
//Note: In the string, each word is separated by single space and there will not be any extra space in the string.




class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : split) {
            sb.append(reverseWord(word));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    private String reverseWord(String word) {
        char[] array = word.toCharArray();
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
        return new String(array);
    }
}



