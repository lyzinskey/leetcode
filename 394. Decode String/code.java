//Given an encoded string, return its decoded string.

//The encoding rule is: k[encoded_string], 
//where the encoded_string inside the square brackets is being repeated exactly k times. 
//Note that k is guaranteed to be a positive integer.

//You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

//Furthermore, you may assume that the original data does not contain any digits 
//and that digits are only for those repeat numbers, k. 
//For example, there won't be input like 3a or 2[4].

//Examples:
//
//s = "3[a]2[bc]", return "aaabcbc".
//s = "3[a2[c]]", return "accaccacc".
//s = "2[abc]3[cd]ef", return "abcabccdcdcdef".



class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        Deque<Integer> count = new ArrayDeque<>();
        Deque<String> encoded = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int slow = 0;
        int fast = 0;

        while (fast < s.length()) {
            char ch = s.charAt(fast);
            if (Character.isDigit(ch)) {
                slow = fast;
                while (fast < s.length() && Character.isDigit(s.charAt(fast))) {
                    fast++;
                }
                int digit = Integer.valueOf(s.substring(slow, fast));
                count.push(digit);
            } else if (ch == '[') {
                encoded.push(sb.toString());
                sb = new StringBuilder();
                fast++;
            } else if (Character.isLetter(ch)) {
                slow = fast;
                while (fast < s.length() && Character.isLetter(s.charAt(fast))) {
                    sb.append(s.charAt(fast));
                    fast++;
                }
            } else {
                int repeat = count.pop() - 1;
                String repeated = sb.toString();
                for (int i = 0; i < repeat; i++) {
                    sb.append(repeated);
                }
                if (!encoded.isEmpty()) {
                    sb.insert(0, encoded.pop());
                }
                fast++;
            }
        }
        return sb.toString();
    }
}



