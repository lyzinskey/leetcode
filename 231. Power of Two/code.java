//Given an integer, write a function to determine if it is a power of two.

//Example 1:
//
//Input: 1
//Output: true

//Example 2:
//
//Input: 16
//Output: true

//Example 3:
//
//Input: 218
//Output: false


    public boolean isPowerOfTwo(int n) {        
        if (recursion(n) == 1){
            return true;
        }
        else {
            return false;
        }
    }
    
    private int recursion(int n){
        if (n > 1 && n % 2 == 0){
            return recursion(n / 2);
        }
        else {
            return n;
        }
    }

