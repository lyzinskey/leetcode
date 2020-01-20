//Given a single positive integer x, we will write an expression of the form x (op1) x (op2) x (op3) x ... 
//where each operator op1, op2, etc. is either addition, subtraction, multiplication, or division (+, -, *, or /).  
//For example, with x = 3, we might write 3 * 3 / 3 + 3 - 3 which is a value of 3.

//When writing such an expression, we adhere to the following conventions:

//The division operator (/) returns rational numbers.
//There are no parentheses placed anywhere.
//We use the usual order of operations: multiplication and division happens before addition and subtraction.
//It's not allowed to use the unary negation operator (-).  
//For example, "x - x" is a valid expression as it only uses subtraction, but "-x + x" is not because it uses negation.
//We would like to write an expression with the least number of operators such that the expression equals the given target. 
//Return the least number of operators used.

//Example 1:
//
//Input: x = 3, target = 19
//Output: 5
//Explanation: 3 * 3 + 3 * 3 + 3 / 3.  The expression contains 5 operations.

//Example 2:
//
//Input: x = 5, target = 501
//Output: 8
//Explanation: 5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5.  The expression contains 8 operations.

//Example 3:
//
//Input: x = 100, target = 100000000
//Output: 3
//Explanation: 100 * 100 * 100 * 100.  The expression contains 3 operations.

//Note:
//
//2 <= x <= 100
//1 <= target <= 2 * 10^8





class Solution {
    // Dijkstra
    // Find the shortest path from target to 0, the cost is the number of operators
    // Time: O(nlogn)
    // Space: O(nlogn)
    // n = x * log(target) / log(x)
    public int leastOpsExpressTarget(int x, int target) {
        // int[2] => {cost, remain}
        PriorityQueue<int[]> minheap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        Set<Integer> hashset = new HashSet<>();
        minheap.offer(new int[]{0, target});

        while (!minheap.isEmpty()) {
            int[] curr = minheap.poll();
            int cost = curr[0];
            int remain = curr[1];
            if (remain == 0) {
                return cost - 1;
            }
            if (hashset.contains(remain)) {
                continue;
            }
            hashset.add(remain);
            int pow = (int) (Math.log(remain) / Math.log(x));
            int l = (int) (remain - Math.pow(x, pow));
            if (!hashset.contains(l)) {
                int add = (pow == 0) ? 2 : pow;
                minheap.offer(new int[]{cost + add, l});
            }
            int r = (int) (Math.pow(x, pow + 1) - remain);
            if (!hashset.contains(r)) {
                minheap.offer(new int[]{cost + pow + 1, r});
            }
        }
        return -1;
    }
}






class Solution {
    // dp: Memoization
    // Time: O(x * log(t)/log(x))
    // Space: O(x * log(t)/log(x))
    public int leastOpsExpressTarget(int x, int target) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        return dp(x, target, hashmap);
    }

    private static int dp(int x, int t, Map<Integer, Integer> hashmap) {
        if (t == 0) {
            return 0;
        }
        if (t < x) {            
            return Math.min(2 * t - 1, 2 * (x - t));
        }
        if (hashmap.containsKey(t)) {
            return hashmap.get(t);
        }
        
        int k = (int) (Math.log(t) / Math.log(x));
        int p = (int) Math.pow(x, k);
        if (t == p) {
            hashmap.put(t, k - 1);
            return k - 1;
        }

        int ans = dp(x, t - p, hashmap) + k;
        int left = p * x - t;
        if (left < t) {
            ans = Math.min(ans, dp(x, left, hashmap) + k + 1);
        }
        hashmap.put(t, ans);
        return ans;
    }    
}




