//Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, 
//determine if two sentences are similar.

//For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, 
//if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

//Note that the similarity relation is transitive. 
//For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

//Similarity is also symmetric. 
//For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

//Also, a word is always similar with itself. 
//For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, 
//even though there are no specified similar word pairs.

//Finally, sentences can only be similar if they have the same number of words. 
//So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

//Note:
//
//The length of words1 and words2 will not exceed 1000.
//The length of pairs will not exceed 2000.
//The length of each pairs[i] will be 2.
//The length of each words[i] and pairs[i][j] will be in the range [1, 20].





class Solution {    
    // union-find
    // using string as key of union-find
    // Time: O(|Pairs| + |words1|)
    // Space: O(|Pairs|)
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        
        Map<String, String> hashmap = new HashMap<>();        
        for (List<String> pair : pairs) {
            String parent1 = find(hashmap, pair.get(0));
            String parent2 = find(hashmap, pair.get(1));
            if (!parent1.equals(parent2)) {
                hashmap.put(parent1, parent2);
            }
        }
        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (w1.equals(w2)) {
                continue;
            }
            if (!find(hashmap, w1).equals(find(hashmap, w2))) {
                return false;
            }
        }
        return true;
    }
    
    private String find(Map<String, String> hashmap, String word) {
        if (!hashmap.containsKey(word)) {
            hashmap.put(word, word);
        } else if (hashmap.containsKey(hashmap.get(word))) {
            hashmap.put(word, hashmap.get(hashmap.get(word)));
        }
        return word.equals(hashmap.get(word)) ? word : find(hashmap, hashmap.get(word));
    }
}




