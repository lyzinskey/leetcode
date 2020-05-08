//Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s 
//and removing them causing the left and the right side of the deleted substring to concatenate together.

//We repeatedly make k duplicate removals on s until we no longer can.

//Return the final string after all such duplicate removals have been made.

//It is guaranteed that the answer is unique.

//Example 1:
//
//Input: s = "abcd", k = 2
//Output: "abcd"
//Explanation: There's nothing to delete.

//Example 2:
//
//Input: s = "deeedbbcccbdaa", k = 3
//Output: "aa"
//Explanation: 
//First delete "eee" and "ccc", get "ddbbbdaa"
//Then delete "bbb", get "dddaa"
//Finally delete "ddd", get "aa"

//Example 3:
//
//Input: s = "pbbcggttciiippooaais", k = 2
//Output: "ps"

//Constraints:
//
//1 <= s.length <= 10^5
//2 <= k <= 10^4
//s only contains lower case English letters.




class Solution {
    // Time: O(n)
    // Space: O(n)
    public String removeDuplicates(String s, int k) {
        Deque<Pair<Integer, Character>> stack = new ArrayDeque<>();
        stack.push(new Pair(0, '#'));

        for (char c : s.toCharArray()) {
            if (stack.peek().getValue() != c) {
                stack.push(new Pair(1, c));
            } else {
                int count = stack.pop().getKey() + 1;
                if (count != k) {
                    stack.push(new Pair(count, c));
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair<Integer, Character> pair = stack.pop();
            for (int i = 0; i < pair.getKey(); i++) {
                stringBuilder.append(pair.getValue());
            }
        }
        return stringBuilder.reverse().toString();
    }
}



