//With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
//word contains the first letter of puzzle.
//For each letter in word, that letter is in puzzle.
//For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage"; 
//while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
//Return an array answer, 
//where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].

//Example :
//
//Input: 
//words = ["aaaa","asas","able","ability","actt","actor","access"], 
//puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
//Output: [1,1,3,2,4,0]
//Explanation:
//1 valid word for "aboveyz" : "aaaa" 
//1 valid word for "abrodyz" : "aaaa"
//3 valid words for "abslute" : "aaaa", "asas", "able"
//2 valid words for "absoryz" : "aaaa", "asas"
//4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
//There're no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.

//Constraints:
//
//1 <= words.length <= 10^5
//4 <= words[i].length <= 50
//1 <= puzzles.length <= 10^4
//puzzles[i].length == 7
//words[i][j], puzzles[i][j] are English lowercase letters.
//Each puzzles[i] doesn't contain repeated characters.





class Solution {
    // Compress each word to a binary string,
    // and compute the frequency of each binary string
    //
    // "a" => 1, "aa" => 1, "ab" => 11, "bc" => 110
    // {1->2, 11->1, 110->1}
    //
    // For each puzzle, compress it to a binary string using the same method.
    // Try all possible subsets of the binary string.
    // There are at most 2^7 = 128 subsets, and we must contain the first character.
    // 
    // How to generate all subsets?
    // 1. DFS
    // 2. for i = 0 to 1 << n
    // 3. i = (i - 1) & x (x is the bit map)
    //
    // Time: O(sum(len of every word) + 2^7 * array len of puzzles)
    // Space: O(|words|)
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        
        for (String word : words) {
            int mask = 0;
            for (char ch : word.toCharArray()) {
                mask |= 1 << (ch - 'a');
            }
            if (!freq.containsKey(mask)) {
                freq.put(mask, 0);
            }
            freq.put(mask, freq.get(mask) + 1);
        }
        
        for (String puzzle : puzzles) {
            int mask = 0;
            for (char ch : puzzle.toCharArray()) {
                mask |= 1 << (ch - 'a');
            }
            
            int first = puzzle.charAt(0) - 'a';
            int curr = mask;
            int total = 0;
            while (curr != 0) {
                if (((curr >> first) & 1) == 1 && freq.containsKey(curr)) {
                    total += freq.get(curr);
                }
                curr = (curr - 1) & mask;
            }
            res.add(total);
        }
        return res;
    }
}



