Given a list of phrases, generate a list of Before and After puzzles.

//A phrase is a string that consists of lowercase English letters and spaces only. 
//No space appears in the start or the end of a phrase. There are no consecutive spaces in a phrase.

//Before and After puzzles are phrases that are formed by merging two phrases 
//where the last word of the first phrase is the same as the first word of the second phrase.

//Return the Before and After puzzles that can be formed by every two phrases phrases[i] and phrases[j] where i != j. 
//Note that the order of matching two phrases matters, we want to consider both orders.

//You should return a list of distinct strings sorted lexicographically.

//Example 1:
//
//Input: phrases = ["writing code","code rocks"]
//Output: ["writing code rocks"]

//Example 2:
//
//Input: phrases = ["mission statement",
//                  "a quick bite to eat",
//                  "a chip off the old block",
//                  "chocolate bar",
//                  "mission impossible",
//                  "a man on a mission",
//                  "block party",
//                  "eat my words",
//                  "bar of soap"]
//Output: ["a chip off the old block party",
//         "a man on a mission impossible",
//         "a man on a mission statement",
//         "a quick bite to eat my words",
//         "chocolate bar of soap"]

//Example 3:
//
//Input: phrases = ["a","b","a"]
//Output: ["a"]

//Constraints:
//1 <= phrases.length <= 100
//1 <= phrases[i].length <= 100




class Solution {
    //Use 2 HashMap's to store first and last words as keys respectively, 
    //and the corresponding phrases as the values.
    
    //Use the 3rd HashMap to check if there are duplicate phrases - a sole one can NOT create a puzzle, 
    //even if its first and last word are same.
    
    //Use a TreeSet to guarantee distinct and ascending resutl.
    
    //Traverse the 2 HashMap's to construct the result.
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String, Set<String>> head = new HashMap<>();
        Map<String, Set<String>> tail = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        for (String str : phrases) {
            String[] split = str.split(" ");
            if (!head.containsKey(split[0])) {
                head.put(split[0], new HashSet<>());
            }
            head.get(split[0]).add(str);

            if (!tail.containsKey(split[split.length - 1])) {
                tail.put(split[split.length - 1], new HashSet<>());
            }
            tail.get(split[split.length - 1]).add(str);
            
            count.put(str, 1 + count.getOrDefault(str, 0));
        }
        TreeSet<String> ans = new TreeSet<>();
        for (String end : tail.keySet())
            for (String p : head.getOrDefault(end, Collections.emptySet()))
                for (String t : tail.get(end))
                    if (!t.equals(p) || count.get(p) > 1)
                        ans.add(t + p.substring(end.length()));
        return new ArrayList<>(ans);
    }
}



