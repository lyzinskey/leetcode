//There is a new alien language which uses the latin alphabet. 
//However, the order among letters are unknown to you. 
//You receive a list of non-empty words from the dictionary, 
//where words are sorted lexicographically by the rules of this new language. 
//Derive the order of letters in this language.

//Example 1:
//
//Input:
//  [
//    "wrt",
//    "wrf",
//    "er",
//    "ett",
//    "rftt"
//  ]
//
//Output: "wertf"

//Example 2:
//
//Input:
//  [
//    "z",
//    "x"
//  ]
//
//Output: "zx"

//Example 3:
//
//Input:
//  [
//    "z",
//    "x",
//    "z"
//  ] 
//
//Output: "" 
//Explanation: The order is invalid, so return "".

//Note:
//
//You may assume all letters are in lowercase.
//You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
//If the order is invalid, return an empty string.
//There may be multiple valid order of letters, return any one of them is fine.




class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        Map<Character, Set<Character>> hashmap = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                indegree.put(ch, 0);
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            int length = Math.min(cur.length(), next.length());
            
            for (int j = 0; j < length; j++) {
                char curChar = cur.charAt(j);
                char nextChar = next.charAt(j);
                
                if (curChar != nextChar) {
                    Set<Character> hashset = new HashSet<>();
                    if (hashmap.containsKey(curChar)) {
                        hashset = hashmap.get(curChar);
                    }
                    if (!hashset.contains(nextChar)) {
                        hashset.add(nextChar);
                        indegree.put(nextChar, indegree.get(nextChar) + 1);
                        hashmap.put(curChar, hashset);
                    }
                    break;
                }
            }
        }
        
        Queue<Character> queue = new LinkedList<>();
        
        for (char ch : indegree.keySet()) {
            if (indegree.get(ch) == 0) {
                queue.offer(ch);
            }
        }
        
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            sb.append(ch);
            if (hashmap.containsKey(ch)) {
                for (char nextChar : hashmap.get(ch)) {
                    indegree.put(nextChar, indegree.get(nextChar) - 1);
                    if (indegree.get(nextChar) == 0) {
                        queue.offer(nextChar);
                    }                        
                }
            }
        }
        if (sb.length() != indegree.size()) {
            return "";
        }
        return sb.toString();
    }
}



