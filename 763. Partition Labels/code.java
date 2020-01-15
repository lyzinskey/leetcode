//A string S of lowercase letters is given. 
//We want to partition this string into as many parts as possible so that each letter appears in at most one part, 
//and return a list of integers representing the size of these parts.

//Example 1:
//Input: S = "ababcbacadefegdehijhklij"
//Output: [9,7,8]
//Explanation:
//The partition is "ababcbaca", "defegde", "hijhklij".
//This is a partition so that each letter appears in at most one part.
//A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

//Note:
//
//S will have length in range [1, 500].
//S will consist of lowercase letters ('a' to 'z') only.




class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] lastIndex = new int[26];
        List<Integer> result = new ArrayList<>();
        char[] array = S.toCharArray();
        for (int i = array.length - 1; i >= 0; i--) {            
            int index = array[i] - 'a';
            lastIndex[index] = Math.max(lastIndex[index], i);
        }
        
        int start = 0;
        int end = 0;
        for (int i = 0; i < array.length; i++) {
            int index = array[i] - 'a';
            end = Math.max(end, lastIndex[index]);
            if (end == i) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }
}



