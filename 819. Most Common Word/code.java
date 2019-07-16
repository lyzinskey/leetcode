//Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  
//It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

//Words in the list of banned words are given in lowercase, and free of punctuation.  
//Words in the paragraph are not case sensitive.  The answer is in lowercase.

//Example:
//
//Input: 
//paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
//banned = ["hit"]
//Output: "ball"

//Explanation: 
//"hit" occurs 3 times, but it is a banned word.
//"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
//Note that words in the paragraph are not case sensitive,
//that punctuation is ignored (even if adjacent to words, such as "ball,"), 
//and that "hit" isn't the answer even though it occurs more because it is banned.

//Note:
//
//1 <= paragraph.length <= 1000.
//0 <= banned.length <= 100.
//1 <= banned[i].length <= 10.
//The answer is unique, and written in lowercase 
//(even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
//paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
//There are no hyphens or hyphenated words.
//Words only consist of letters, never apostrophes or other punctuation symbols.




class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // 大写W表示非word的元素，W+表示所有非word，replaceAll把所有不是word的元素替换为空格
        paragraph = paragraph.replaceAll("\\W+", " ").toLowerCase();
        Set<String> bannedSet = new HashSet<>();
        Map<String, Integer> hashmap = new HashMap<>();
        
        for (String word : banned) {
            bannedSet.add(word);
        }
        
        for (String word : paragraph.split(" ")) {
            if (!bannedSet.contains(word)) {
                if (hashmap.containsKey(word)) {
                    hashmap.put(word, hashmap.get(word) + 1);
                } else {
                    hashmap.put(word, 1);
                }
            }         
        }
        
        int count = 0;
        String result = "";
        for (String word : hashmap.keySet()) {
            if (hashmap.get(word) > count) {
                count = hashmap.get(word);
                result = word;
            }
        }
        return result;
    }
}



