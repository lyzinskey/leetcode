//Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. 
//Also two strings X and Y are similar if they are equal.

//For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, 
//but "star" is not similar to "tars", "rats", or "arts".

//Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  
//Notice that "tars" and "arts" are in the same group even though they are not similar.  
//Formally, each group is such that a word is in the group 
//if and only if it is similar to at least one other word in the group.

//We are given a list A of strings.  
//Every string in A is an anagram of every other string in A.  
//How many groups are there?
 
//Example 1:
//
//Input: A = ["tars","rats","arts","star"]
//Output: 2
 
//Constraints:
//
//1 <= A.length <= 2000
//1 <= A[i].length <= 1000
//A.length * A[i].length <= 20000
//All words in A consist of lowercase letters only.
//All words in A have the same length and are anagrams of each other.
//The judging time limit has been increased for this question.




class Solution {
    // loop through words in A, for every word, use dfs to search for similar words
    // change words in A to null to represent visited
    //
    // Time: O(n^2 * l), where l represents average length of string
    // Space: O(n)
    public int numSimilarGroups(String[] A) {
        if(A.length < 2) {
            return A.length;
        }
        
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == null) {
                continue;
            }
            String str = A[i];
            A[i] = null;
            res++;
            dfs(A, str);
        }
        return res;
    }
    
    public void dfs(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            }
            if (isSimilar(str, arr[i])) {
                String s = arr[i];
                arr[i] = null;
                dfs(arr, s);
            }
        }
    }
    
    public boolean isSimilar(String s, String t){        
        int res = 0; 
        int i = 0;
        while (res <= 2 && i < s.length()) {
            if (s.charAt(i) != t.charAt(i)) {
                res++;
            }
            i++;
        }
        return res == 2 || res == 0;
    }    
}



