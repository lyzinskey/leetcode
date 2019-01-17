//Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

//Example 1:
//
//Input: 121
//Output: true

//Example 2:
//
//Input: -121
//Output: false
//Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

//Example 3:
//
//Input: 10
//Output: false
//Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

//Follow up:
//Coud you solve it without converting the integer to a string?



// Solution 1: integer to string
class Solution {
    public boolean isPalindrome(int x) {
        String s = Integer.toString(x);

        int headCount = 0;
        int tailCount = s.length() - 1;
        char head;
        char tail;

        while (headCount <= tailCount){
            head = s.charAt(headCount);
            tail = s.charAt(tailCount);
                
            if (Character.toLowerCase(head) != Character.toLowerCase(tail)){
                return false;
            }
            headCount++;
            tailCount--;
        }
        return true;        
    }
}



// Solution 2: reverse integer
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        
        int result = 0;
        
        while (result < x) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        
        return result == x || result / 10 == x;
    }
}



