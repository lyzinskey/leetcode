//Implement pow(x, n), which calculates x raised to the power n (x^n).

//Example 1:
//
//Input: 2.00000, 10
//Output: 1024.00000

//Example 2:
//
//Input: 2.10000, 3
//Output: 9.26100

//Example 3:
//
//Input: 2.00000, -2
//Output: 0.25000
//Explanation: 2-2 = 1/22 = 1/4 = 0.25

//Note:
//-100.0 < x < 100.0
//n is a 32-bit signed integer, within the range [−231, 231 − 1]


    //recursion solution
    public double myPow(double x, int n) {
        double ans = Integer.MIN_VALUE;
        if (n > 0){
            ans = fastPow(x, n);
        }
        else {
            ans = fastPow(x, -n);
            ans = 1 / ans;
        }
        return ans;
    }

    private static double fastPow(double x, int n){
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        //n is double
        if (n % 2 == 0) {
            double temp = fastPow(x, n / 2);
            return temp * temp;
        }
        //n is odd, have to multiple x one more time
        else {
            double temp = fastPow(x, n / 2);
            return temp * temp * x;
        }
    }
