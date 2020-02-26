//Given two words (beginWord and endWord), and a dictionary's word list, 
//find the length of shortest transformation sequence from beginWord to endWord, such that:
//
//Only one letter can be changed at a time.
//Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

//Note:
//
//Return 0 if there is no such transformation sequence.
//All words have the same length.
//All words contain only lowercase alphabetic characters.
//You may assume no duplicates in the word list.
//You may assume beginWord and endWord are non-empty and are not the same.

//Example 1:
//
//Input:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//Output: 5
//
//Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//return its length 5.

//Example 2:
//
//Input:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//Output: 0
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.




class Solution {
    // bidirectional bfs
    // Time: O(n * 26^l)  n: lengh of wordList, l: 
    // Space: O(n)    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        if (beginWord.equals(endWord) || !dict.contains(endWord)) {
            return 0;
        }

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        Set<String> visited = new HashSet<>();
        set1.add(beginWord);
        set2.add(endWord);
        visited.add(beginWord);
        visited.add(endWord);
        int step = 0;

        while (!set1.isEmpty() && !set2.isEmpty()) {
            step++;
            Set<String> delete = new HashSet<>();
            Set<String> add = new HashSet<>();
            int counter = 0;
            int size;
            if (set1.size() < set2.size()) {
                size = set1.size();
                for (String word : set1) {
                    counter++;
                    delete.add(word);
                    if (expand(dict, word, set1, visited, set2, add)) {
                        return step + 1;
                    }
                    if (counter >= size) {
                        break;
                    }
                }
                update(set1, delete, add);
            } else {
                size = set2.size();
                for (String word : set2) {
                    counter++;
                    delete.add(word);
                    if (expand(dict, word, set2, visited, set1, add)) {
                        return step + 1;
                    }
                    if (counter >= size) {
                        break;
                    }
                }
                update(set2, delete, add);
            }
        }
        return 0;
    }

    private boolean expand(Set<String> dict, String word, Set<String> expandSet, Set<String> visited, Set<String> set, Set<String> add) {
        char[] array = word.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char ch = array[i];
            for (char j = 'a'; j < 'z'; j++) {
                array[i] = j;
                String temp = new String(array);
                if (set.contains(temp)) {
                    return true;
                }
                if (visited.contains(temp) || !dict.contains(temp)) {
                    continue;
                }
                add.add(temp);
                visited.add(temp);
            }
            array[i] = ch;
        }
        return false;
    }

    private void update(Set<String> set, Set<String> delete, Set<String> add) {
        for (String word : delete) {
            set.remove(word);
        }
        for (String word : add) {
            set.add(word);
        }
    }
}



