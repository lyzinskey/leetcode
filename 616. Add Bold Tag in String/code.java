//Given a string s and a list of strings dict, 
//you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. 
//If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. 
//Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

//Example 1:
//Input: 
//s = "abcxyz123"
//dict = ["abc","123"]
//Output:
//"<b>abc</b>xyz<b>123</b>"

//Example 2:
//Input: 
//s = "aaabbcc"
//dict = ["aaa","aab","bc"]
//Output:
//"<b>aaabbc</b>c"

//Note:
//The given dict won't contain duplicates, and its length won't exceed 100.
//All the strings in input have length in range [1, 1000].




class Solution {
    // same logic as LC370. Range Addition
    public String addBoldTag(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n + 1];
        
        for(String d : dict) {
            int i = -1;
            while((i = s.indexOf(d, i + 1)) >= 0) {
                mark[i]++;
                mark[i + d.length()]--;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        
        for(int i = 0; i <= n; i++) {
            int cur = sum + mark[i];
            if (cur > 0 && sum == 0) {
                sb.append("<b>");
            }
            if (cur == 0 && sum > 0) {
                sb.append("</b>");
            }
            if (i == n) {
                break;
            }
            sb.append(s.charAt(i));
            sum = cur;
        }
        return sb.toString();
    }
}



