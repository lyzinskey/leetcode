//The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, 
//such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

//F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), for N > 1.
//Given N, calculate F(N).
 
//Example 1:
//
//Input: 2
//Output: 1
//Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.

//Example 2:
//
//Input: 3
//Output: 2
//Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.

//Example 3:
//
//Input: 4
//Output: 3
//Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 
//Note:
//0 ≤ N ≤ 30.





class Solution {
    // public int fib(int k) {
    //     if (k == 0) {
    //         return 0;
    //     }
    //     if (k == 1) {
    //         return 1;
    //     }
    //     return fib(k - 1) + fib(k - 2);
    // }
    
    public int fib(int k) {
        if(k < 0){
            return 0;
        }
        if(k == 0 || k == 1){
            return k;
        }
        int a = 0;
        int b = 1;
        int c = 1;
        for(int i = 1; i < k; i++){
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}



