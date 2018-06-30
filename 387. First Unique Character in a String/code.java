//Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

//Examples:
//
//s = "leetcode"
//return 0.
//
//s = "loveleetcode",
//return 2.
//Note: You may assume the string contain only lowercase letters.



class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                if (map.get(s.charAt(i)) != null) {
                    map.remove(s.charAt(i));
                }
            } else {
                map.put(s.charAt(i), i);
                set.add(s.charAt(i));
            }
        }
        return map.size() == 0 ? -1 : map.entrySet().iterator().next().getValue();
    }
}




    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        
        int[] charCounter = new int[256];
        char[] character = s.toCharArray();
        for (char c : character) {
            charCounter[c]++;
        }
        
        for (int i = 0; i < character.length; i++) {
            if (charCounter[character[i]] == 1) {
                return i;
            }
        }
        
        return -1;        
    }


