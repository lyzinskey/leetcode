//Given a string containing digits from 2-9 inclusive, 
//return all possible letter combinations that the number could represent.

//A mapping of digit to letters (just like on the telephone buttons) is given below. 
// https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png
//Note that 1 does not map to any letters.

//Example:
//
//Input: "23"
//Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

//Note:
//Although the above answer is in lexicographical order, your answer could be in any order you want.



// Recursion, DFS
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        if (digits.length() == 0) {
            return result;
        }
        
        Map<Character, char[]> hashmap = new HashMap<>();
        hashmap.put('2', new char[] {'a', 'b', 'c'});
        hashmap.put('3', new char[] {'d', 'e', 'f'});
        hashmap.put('4', new char[] {'g', 'h', 'i'});
        hashmap.put('5', new char[] {'j', 'k', 'l'});
        hashmap.put('6', new char[] {'m', 'n', 'o'});
        hashmap.put('7', new char[] {'p', 'q', 'r', 's'});
        hashmap.put('8', new char[] {'t', 'u', 'v'});
        hashmap.put('9', new char[] {'w', 'x', 'y', 'z'});
        
        DFS(digits, result, hashmap, new StringBuilder(), 0);
        return result;    
    }
    
    private void DFS(String digits, List<String> result, Map<Character, char[]> hashmap, StringBuilder sb, int index) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }
        
        for (char ch : hashmap.get(digits.charAt(index))) {
            sb.append(ch);
            DFS(digits, result, hashmap, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}




// Iteration, BFS
class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> list = new LinkedList<>();
        
        if (digits.length() == 0) {
            return list;
        }
        
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        list.add("");
        
        while (list.peek().length() != digits.length()) {
            String peak = list.remove();
            String map = mapping[digits.charAt(peak.length()) - '0'];
            char[] charArray = map.toCharArray();
            for (char ch : charArray) {
                list.add(peak + ch);
            }
        }
        return list;
    }
}



