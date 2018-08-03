//Write a function that takes a string as input and returns the string reversed.

//Example:
//Given s = "hello", return "olleh".



class Solution {
    public String reverseString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
    
        char[] charArray = input.toCharArray();    
        int left = 0; 
        int right = charArray.length - 1;
    
        while (left < right) {
            swap(charArray, left++, right--);      
        }
    
        return new String(charArray);
    }
  
    private void swap(char[] charArray, int left, int right) {
        char temp = charArray[left];
        charArray[left] = charArray[right];
        charArray[right] = temp;
    }    
}


