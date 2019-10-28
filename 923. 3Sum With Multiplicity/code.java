//Given an integer array A, and an integer target, return the number of tuples i, j, k  
//such that i < j < k and A[i] + A[j] + A[k] == target.

//As the answer can be very large, return it modulo 10^9 + 7.

//Example 1:
//
//Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
//Output: 20
//Explanation: 
//Enumerating by the values (A[i], A[j], A[k]):
//(1, 2, 5) occurs 8 times;
//(1, 3, 4) occurs 8 times;
//(2, 2, 4) occurs 2 times;
//(2, 3, 3) occurs 2 times.

//Example 2:
//
//Input: A = [1,1,2,2,2,2], target = 5
//Output: 12
//Explanation: 
//A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
//We choose one 1 from [1,1] in 2 ways,
//and two 2s from [2,2,2,2] in 6 ways.

//Note:
//
//3 <= A.length <= 3000
//0 <= A[i] <= 100
//0 <= target <= 300




class Solution {
    public int threeSumMulti(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }

        List<Integer> nums = new ArrayList<>();
        
        // bucket类型必须是long，result类型也必须是long，任意一个类型不对会integer溢出
        long[] bucket = new long[101];
        long result = 0;
        int pow = 1000000007;

        for (int i = 0; i < A.length; i++) {
            bucket[A[i]]++;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                nums.add(i);
            }
        }

        for (int i = 0; i < nums.size(); i++) {
            for (int j = i; j < nums.size(); j++) {
                int num1 = nums.get(i);
                int num2 = nums.get(j);
                int num3 = target - num1 - num2;
                if (num3 < 0 || num3 > 100) {
                    continue;
                }
                if (bucket[num3] != 0 && num3 >= num2) {
                    if (num1 != num2 && num2 != num3 && num1 != num3) {
                        result += bucket[num1] * bucket[num2] * bucket[num3];
                    } else if (num1 != num2 && num2 == num3) {
                        result += bucket[num1] * bucket[num2] * (bucket[num2] - 1) / 2;
                    } else if (num1 == num2 && num2 != num3) {
                        result += bucket[num3] * bucket[num2] * (bucket[num2] - 1) / 2;
                    } else {
                        result += bucket[num2] * (bucket[num2] - 1) * (bucket[num2] - 2) / 6;
                    }
                    result %= pow;
                }
            }
        }
        return (int)result;
    }
}



