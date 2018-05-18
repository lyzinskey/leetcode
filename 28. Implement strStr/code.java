//Implement strStr().
//
//Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
//
//Example 1:
//
//Input: haystack = "hello", needle = "ll"
//Output: 2
//Example 2:
//
//Input: haystack = "aaaaa", needle = "bba"
//Output: -1
//Clarification:
//
//What should we return when needle is an empty string? This is a great question to ask during an interview.
//
//For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

//    An O(mn) time solution
public int strStr(String haystack, String needle) {
        if (needle.isEmpty()){
            return 0;
        }
        if (haystack.isEmpty()){
            return -1;
        }
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()){
            //compare letter by letter
            if (haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            }
            //back to the first character of last comparison and start comparing from the next character
            else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == needle.length()){
            return i - j;
        }
        return -1;
    }



//another cheating way is to use int indexOf(String str)
//This method returns the index within this string of the first occurrence of the specified character or -1, 
//if the character does not occur.
//
//
//    public int strStr(String haystack, String needle) {
//        if (needle.isEmpty()) {
//            return 0;
//        }
//        return haystack.indexOf(needle);
//    }



//    An O(m+n) time solution
//    public int strStr2(String source, String target) {
//        if(target == null) {
//            return -1;
//        }
//        int m = target.length();
//        if(m == 0 && source != null) {
//            return 0;
//        }
//
//        if(source == null) {
//            return -1;
//        }
//        int n = source.length();
//        if(n == 0) {
//            return -1;
//        }
//
//        // mod could be any big integer
//        // just make sure mod * 33 wont exceed max value of int.
//        int mod = Integer.MAX_VALUE / 33;
//        int hash_target = 0;
//
//        // 33 could be something else like 26 or whatever you want
//        for (int i = 0; i < m; ++i) {
//            hash_target = (hash_target * 33 + target.charAt(i) - 'a') % mod;
//            if (hash_target < 0) {
//                hash_target += mod;
//            }
//        }
//
//        int m33 = 1;
//        for (int i = 0; i < m - 1; ++i) {
//            m33 = m33 * 33 % mod;
//        }
//
//        int value = 0;
//        for (int i = 0; i < n; ++i) {
//            if (i >= m) {
//                value = (value - m33 * (source.charAt(i - m) - 'a')) % mod;
//            }
//
//            value = (value * 33 + source.charAt(i) - 'a') % mod;
//            if (value < 0) {
//                value += mod;
//            }
//
//            if (i >= m - 1 && value == hash_target) {
//                // you have to double check by directly compare the string
//                if (target.equals(source.substring(i - m + 1, i + 1))) {
//                    return i - m + 1;
//                }
//            }
//        }
//        return -1;
//    }
