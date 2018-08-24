//Given a string S and a string T, 
//find the minimum window in S which will contain all the characters in T in complexity O(n).

//Example:
//
//Input: S = "ADOBECODEBANC", T = "ABC"
//Output: "BANC"

//Note:
//
//If there is no such window in S that covers all characters in T, return the empty string "".
//If there is such window, you are guaranteed that there will always be only one unique minimum window in S.



// 首先统计 target 每个字母的frequency，
// left代表窗口左侧，right代表窗口右侧，count表示找到的字母个数
// 然后遍历input，每当遍历到一个target字母，更新hashmap和count
// 一旦找到的字母数目等于target长度，则说明已经全部找到，那么从头开始压缩窗口内的子串，压缩到最短的情况，然后窗口头后移，找下一个子串。
// e.g.  window = aaabc,  target = abc
// 每次压缩成功时，比较得到最短的子串

class Solution {    
    public String minWindow(String input, String target) {
        if (input == null || input.length() == 0 || input.length() < target.length()) {
            return "";
        }
        Map<Character, Integer> map = getLetterFrequency(target);

        int left = 0;
        int global_left = 0;
        int minLen = input.length() + 1;
        int count = 0;

        for (int right = 0; right < input.length(); right++) {
            if (map.containsKey(input.charAt(right))) {
                map.put(input.charAt(right), map.get(input.charAt(right)) - 1);
                if (map.get(input.charAt(right)) >= 0) {
                    count++;
                }
                // 进入while循环代表我们已经找到从left到right的一个window，
                // 这个window里完整包含了target，但可能会有重复字母
                // 例如window = aabc, target = abc
                while (count == target.length()) {
                    // 更新 global_left 以及 minLen
                    if (right - left + 1 < minLen) {
                        global_left = left;
                        minLen = right - left + 1;
                    }
                    // 更新window的left边界直到window内部不再完整包含target
                    if (map.containsKey(input.charAt(left))) {
                        char leftChar = input.charAt(left);
                        map.put(leftChar, map.get(leftChar) + 1);
                        if (map.get(leftChar) > 0) {
                            count--;
                        }
                    }
                    left++;
                }
            }
        }
        return minLen == input.length() + 1 ? "" : input.substring(global_left, global_left + minLen);
    }

    private Map<Character, Integer> getLetterFrequency(String s) {
        Map<Character, Integer> hashmap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashmap.containsKey(s.charAt(i))) {
                hashmap.put(s.charAt(i), hashmap.get(s.charAt(i)) + 1);
            } else {
                hashmap.put(s.charAt(i), 1);
            }
        }
        return hashmap;
    }    
}



