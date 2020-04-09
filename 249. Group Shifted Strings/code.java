//Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". 
//We can keep "shifting" which forms the sequence:
//"abc" -> "bcd" -> ... -> "xyz"
//Given a list of strings which contains only lowercase alphabets, 
//group all strings that belong to the same shifting sequence.

//Example:
//
//Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
//Output: 
//[
//  ["abc","bcd","xyz"],
//  ["az","ba"],
//  ["acef"],
//  ["a","z"]
//]




class Solution {
    // Time: O(nl)
    // Space: O(n)
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> hashmap = new HashMap<>();
        
        for (String str : strings) {
            StringBuilder sb = new StringBuilder();
            int offset = str.charAt(0) - 'a';
            
            for (char ch : str.toCharArray()) {
                int temp = ch - offset < 'a' ? ch - offset + 26 : ch - offset;
                sb.append((char) temp);
            }
            String key = sb.toString();
            if (!hashmap.containsKey(key)) {
                hashmap.put(key, new ArrayList<>());
            }
            hashmap.get(key).add(str);
        }
        
        for (List<String> list : hashmap.values()) {
            res.add(list);
        }
        return res;
    }
}



