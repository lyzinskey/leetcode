//Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
//
//This is case sensitive, for example "Aa" is not considered a palindrome here.
//
//Note:
//Assume the length of given string will not exceed 1,010.
//
//Example:
//
//Input:
//"abccccdd"
//
//Output:
//7
//
//Explanation:
//One longest palindrome that can be built is "dccaccd", whose length is 7.


public static int longestPalindrome(String s) {
        //use hashset to store characters
        Set<Character> set = new HashSet<>();
        int len = 0;
        for (char c : s.toCharArray()) {
        
            //public boolean remove(Object o)
            //The method call returns 'true' if the set contained the specified element.
            if (set.remove(c)){
                len += 2;
            }
            else {
                set.add(c);
            }
        }
        
        //if the set is empty, it means the palindrome only consists of letters with equal partners
        //else the palindrome consists of a unique center (without a partner)
        return set.size() > 0 ? len+1: len;
    }
