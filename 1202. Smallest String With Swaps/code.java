//You are given a string s, 
//and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

//You can swap the characters at any pair of indices in the given pairs any number of times.

//Return the lexicographically smallest string that s can be changed to after using the swaps.

//Example 1:
//
//Input: s = "dcab", pairs = [[0,3],[1,2]]
//Output: "bacd"
//Explaination: 
//Swap s[0] and s[3], s = "bcad"
//Swap s[1] and s[2], s = "bacd"

//Example 2:
//
//Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
//Output: "abcd"
//Explaination: 
//Swap s[0] and s[3], s = "bcad"
//Swap s[0] and s[2], s = "acbd"
//Swap s[1] and s[2], s = "abcd"

//Example 3:
//
//Input: s = "cba", pairs = [[0,1],[1,2]]
//Output: "abc"
//Explaination: 
//Swap s[0] and s[1], s = "bca"
//Swap s[1] and s[2], s = "bac"
//Swap s[0] and s[1], s = "abc"

//Constraints:
//
//1 <= s.length <= 10^5
//0 <= pairs.length <= 10^5
//0 <= pairs[i][0], pairs[i][1] < s.length
//s only contains lower case English letters.




class Solution {
    // union find
    // Time: O(VlogV)
    // Space: O(V)
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] array = s.toCharArray();
        int[] parent = new int[array.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (List<Integer> pair : pairs) {
            int pa = find(parent, pair.get(0));
            int pb = find(parent, pair.get(1));
            if (pa != pb) {
                parent[pb] = pa;
            }
        }
        
        Map<Integer, PriorityQueue<Character>> hashmap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int root = find(parent, i);
            if (!hashmap.containsKey(root)) {
                hashmap.put(root, new PriorityQueue<>());
            }
            hashmap.get(root).offer(array[i]);
        }
        
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = hashmap.get(parent[i]).poll();
        }
        return new String(result);
    }
    
    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }
}




