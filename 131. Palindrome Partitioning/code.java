//Given a string s, partition s such that every substring of the partition is a palindrome.

//Return all possible palindrome partitioning of s.

//Example:
//
//Input: "aab"
//Output:
//  [
//    ["aa","b"],
//    ["a","a","b"]
//  ]



class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        
        if (s == null) {
            return result;
        }
        
        ArrayList<String> subString = new ArrayList<>();
        dfs(s, 0, result, subString);
        return result;
    }
    
    private void dfs(String s, int index, List<List<String>> result, ArrayList<String> subString) {
        if (index == s.length()) {
            result.add(new ArrayList<String>(subString));
            return;
        }
        
        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if (!isPalindrome(sub)) {
                continue;
            }
            subString.add(sub);
            dfs(s, i + 1, result, subString);
            subString.remove(subString.size() - 1);
        }
    }
    
    private Boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

