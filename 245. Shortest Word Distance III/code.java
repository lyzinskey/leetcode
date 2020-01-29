//Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

//word1 and word2 may be the same and they represent two individual words in the list.

//Example:
//Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//Input: word1 = “makes”, word2 = “coding”
//Output: 1
//
//Input: word1 = "makes", word2 = "makes"
//Output: 3

//Note:
//You may assume word1 and word2 are both in the list.





class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {        
        int p1 = -1;
        int p2 = -1;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                p1 = i;
                if (p2 != -1 && p1 != p2) {
                    res = Math.min(res, p1 - p2);
                }                
            }
            if (words[i].equals(word2)) {
                p2 = i;
                if (p1 != -1 && p1 != p2) {
                    res = Math.min(res, p2 - p1);
                } 
            }
        }
        return res;
    }
}



