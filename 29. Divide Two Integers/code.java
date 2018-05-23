//Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

//Return the quotient after dividing dividend by divisor.

//The integer division should truncate toward zero.

//Example 1:
//
//Input: dividend = 10, divisor = 3
//Output: 3

//Example 2:
//
//Input: dividend = 7, divisor = -3
//Output: -2

//Note:
//
//Both dividend and divisor will be 32-bit signed integers.
//The divisor will never be 0.
//Assume we are dealing with an environment,
//which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1]. 
//For the purpose of this problem, assume that your function returns 2^31 − 1 when the division result overflows.


    //based on long division
    //reference: https://stackoverflow.com/questions/5386377/division-without-using
    public int divide(int dividend, int divisor) {
        //corner case
        if (dividend == 0){
            return 0;
        }
        if (divisor == 1){
            return dividend;
        }        

        //pay attention to this corner case!!!
        if (dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }

        //since we will change dividend and divisor to unsigned number
        //so check whether the answer should be negative first
        boolean negative = false;
        if (dividend > 0 && divisor < 0){
            negative = true;
        }
        if (dividend < 0 && divisor > 0){
            negative = true;
        }

        //change dividend and divisor to positive (unsigned)
        long longDividend = Math.abs((long) dividend);
        long longDivisor = Math.abs((long) divisor);

        int currentBit = 1;
        int ans = 0;

        while ((longDivisor << 1) <= longDividend){
            currentBit <<= 1;
            longDivisor <<= 1;
        }

        while (currentBit != 0){
            if (longDividend >= longDivisor){
                longDividend -= longDivisor;
                ans += currentBit;
            }
            currentBit >>= 1;
            longDivisor >>= 1;
        }

        if (negative){
            return -ans;
        }
        else {
            return ans;
        }
    }
