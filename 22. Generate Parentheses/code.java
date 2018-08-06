//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

//For example, given n = 3, a solution set is:
//
//  [
//    "((()))",
//    "(()())",
//    "(())()",
//    "()(())",
//    "()()()"
//  ]



class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        
        if (n <= 0) {
            return result;
        }
        
        DFS(result, n, 0, 0, new StringBuilder());
        
        return result;
    }
    
    private void DFS(List<String> result, int n, int left, int right, StringBuilder sb) {
        if (left + right == 2* n) {
            result.add(new String(sb));
            return;
        }
        
        if (left < n) {
            sb.append("(");
            DFS(result, n, left + 1, right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (left > right) {
            sb.append(")");
            DFS(result, n, left, right + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }    
}



