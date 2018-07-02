//Write a program to find the n-th ugly number.

//Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

//Example:
//
//Input: n = 10
//Output: 12
//Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

//Note:  
//
//1 is typically treated as an ugly number.
//n does not exceed 1690.


class Solution {
    public int nthUglyNumber(int n) {
        Queue<Long> queue = new PriorityQueue<>();
        Set<Long> hashset = new HashSet<>();
        Long[] prime = new Long[3];
        prime[0] = (long) 2;
        prime[1] = (long) 3;
        prime[2] = (long) 5;
        
        Long number = (long) 1;
        queue.add(number);
        hashset.add(number);
        
        for (int i = 0; i < n; i++) {
            number = queue.poll();
            for (int j = 0; j < 3; j++) {
                if (!hashset.contains(number * prime[j])) {
                    queue.add(number * prime[j]);
                    hashset.add(number * prime[j]);
                }
            }
        }
        
        return number.intValue();        
    }
}


