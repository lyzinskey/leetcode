//Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

//An input string is valid if:
//
//Open brackets must be closed by the same type of brackets.
//Open brackets must be closed in the correct order.

//Note that an empty string is also considered valid.

//Example 1:
//
//Input: "()"
//Output: true

//Example 2:
//
//Input: "()[]{}"
//Output: true

//Example 3:
//
//Input: "(]"
//Output: false

//Example 4:
//
//Input: "([)]"
//Output: false

//Example 5:
//
//Input: "{[]}"
//Output: true



class Solution {
    public boolean isValid(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '(') {
                stack.push(')');
            }
            else if (ch == '[') {
                stack.push(']');
            }
            else if (ch == '{') {
                stack.push('}');
            }
            else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }     
}



