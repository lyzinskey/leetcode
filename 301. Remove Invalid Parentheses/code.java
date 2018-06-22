//Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

//Note: The input string may contain letters other than the parentheses ( and ).

//Example 1:
//
//Input: "()())()"
//Output: ["()()()", "(())()"]

//Example 2:
//
//Input: "(a)())()"
//Output: ["(a)()()", "(a())()"]

//Example 3:
//
//Input: ")("
//Output: [""]



class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int invalidLeft = 0; 
        int invalidRight = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                invalidLeft++;
            } else if (s.charAt(i) == ')') {
                if (invalidLeft != 0) {
                    invalidLeft--;
                } else {
                    invalidRight++;
                }
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, 0, res, new StringBuilder(), invalidLeft, invalidRight, 0);
        return new ArrayList<String>(res);
    }
    
    public void dfs(String s, int index, Set<String> res, StringBuilder sb, int invalidLeft, int invalidRight, int openParentheses) {
        if (invalidLeft < 0 || invalidRight < 0 || openParentheses < 0) {
            return;
        }
        if (index == s.length()) {
            if (invalidLeft == 0 && invalidRight == 0 && openParentheses == 0) {
                res.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = sb.length();

        if (c == '(') {
            dfs(s, index + 1, res, sb, invalidLeft - 1, invalidRight, openParentheses);            // not use (
            dfs(s, index + 1, res, sb.append(c), invalidLeft, invalidRight, openParentheses + 1);       // use (

        } else if (c == ')') {
            dfs(s, index + 1, res, sb, invalidLeft, invalidRight - 1, openParentheses);                // not use  )
            dfs(s, index + 1, res, sb.append(c), invalidLeft, invalidRight, openParentheses - 1);        // use )

        } else {
            dfs(s, index + 1, res, sb.append(c), invalidLeft, invalidRight, openParentheses);
        }

        sb.setLength(len);
    }
}

