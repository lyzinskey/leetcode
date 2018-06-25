//Given a 32-bit signed integer, reverse digits of an integer.

//Example 1:
//
//Input: 123
//Output: 321

//Example 2:
//
//Input: -123
//Output: -321

//Example 3:
//
//Input: 120
//Output: 21

//Note:
//Assume we are dealing with an environment 
//which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. 
//For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.



    public int reverse(int n) {
        int reversed_n = 0;
        
        while (n != 0) {
            int temp = reversed_n * 10 + n % 10;
            n = n / 10;
            if (temp / 10 != reversed_n) {
                reversed_n = 0;
                break;
            }
            reversed_n = temp;
        }
        return reversed_n;        
    }
    
    
