//Given a pattern and a string str, find if str follows the same pattern.

//Here follow means a full match, 
//such that there is a bijection between a letter in pattern and a non-empty substring in str.

//Example 1:
//
//Input: pattern = "abab", str = "redblueredblue"
//Output: true

//Example 2:
//
//Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
//Output: true

//Example 3:
//
//Input: pattern = "aabb", str = "xyzabcxzyabc"
//Output: false

//Notes:
//You may assume both pattern and str contains only lowercase letters.


class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        
        return dfs(pattern, str, map, set);
    }
    
    private boolean dfs(String pattern, String str, Map<Character, String> map, Set<String> set) {
        if (pattern == null || pattern.length() == 0) {
            return str.length() == 0;
        }
        
        char c = pattern.charAt(0);
        
        if (map.containsKey(c)) {
            if (!str.startsWith(map.get(c))) {
                return false;
            }
            else {
                return dfs(pattern.substring(1), str.substring(map.get(c).length()), map, set);
            }
        }
        else {
            for (int i = 0; i < str.length(); i++) {
                String word = str.substring(0, i + 1);
                if (set.contains(word)) {
                    continue;
                }
                
                map.put(c, word);
                set.add(word);
                if (dfs(pattern.substring(1), str.substring(i + 1), map, set)) {
                    return true;
                }
                map.remove(c);
                set.remove(word);
            }
            return false;
        }
    }
}

