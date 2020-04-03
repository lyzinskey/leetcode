//A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

//Write a function to determine if a number is strobogrammatic. The number is represented as a string.

//Example 1:
//
//Input:  "69"
//Output: true

//Example 2:
//
//Input:  "88"
//Output: true

//Example 3:
//
//Input:  "962"
//Output: false




class Solution {
    public boolean isStrobogrammatic(String num) {
        int left = 0;    
        int right = num.length() - 1;
        char[] array = num.toCharArray();
        while(left <= right) {
            if(!helper(array[left], array[right])) {
                return false;
            }                
            left++;
            right--;
        }
        return true;
    }

    public boolean helper(char a, char b) {
        if( (a=='1' && b=='1') || (a=='6' && b=='9') || (a=='9' && b=='6') || (a=='8' && b=='8')  || (a=='0' && b=='0') ) {
            return true;
        }            
        return false;
    }
}



