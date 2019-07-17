//Given an array of strings, group anagrams together.

//Example:
//
//Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
//Output:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]

//Note:
//
//All inputs will be in lowercase.
//The order of your output does not matter.




class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return result;
        }
        
        Map<String, List<String>> hashmap = new HashMap<>();
        for (String word : strs) {
            int[] array = new int[26];
            for (char ch : word.toCharArray()) {
                array[ch - 'a']++;
            }
            
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
                sb.append("#");
            }
            
            String key = sb.toString();
                        
            if (!hashmap.containsKey(key)) {
                hashmap.put(key, new ArrayList<String>());                                                            
            }
            hashmap.get(key).add(word);
        }
        
        for (List<String> list : hashmap.values()) {
            result.add(list);
        }
        return result;
    }
}



