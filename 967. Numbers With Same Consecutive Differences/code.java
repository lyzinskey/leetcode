//Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

//Note that every number in the answer must not have leading zeros except for the number 0 itself. 
//For example, 01 has one leading zero and is invalid, but 0 is valid.

//You may return the answer in any order.

//Example 1:
//
//Input: N = 3, K = 7
//Output: [181,292,707,818,929]
//Explanation: Note that 070 is not a valid number, because it has leading zeroes.

//Example 2:
//
//Input: N = 2, K = 1
//Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]

//Note:
//
//1 <= N <= 9
//0 <= K <= 9





class Solution {
    // dfs
    // Time: O(2^N)
    // Space: O(N)
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> list = new ArrayList<>();
        if (N == 1) {
            list.add(0);
        }
        for (int i = 1; i <= 9; i++) {            
            dfs(list, N - 1, K, i, i);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;        
    }
    
    private void dfs(List<Integer> list, int N, int K, int num, int digit) {
        if (N == 0) {
            list.add(num);
            return;
        }
        if (digit + K <= 9) {
            dfs(list, N - 1, K, num * 10 + digit + K, digit + K);
        }
        if (K != 0 && digit - K >= 0) {
            dfs(list, N - 1, K, num * 10 + digit - K, digit - K);
        }
    }
}





class Solution {
    // bfs
    // Time: O(2^N)
    // Space: O(2^N)
    public int[] numsSameConsecDiff(int N, int K) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= 9; i++) {
            deque.offerLast(i);
        }
        if (N == 1) {
            deque.offerFirst(0);
        }
        
        while (N > 1) {
            N--;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int num = deque.pollFirst();
                int digit = num % 10;
                if (digit + K <= 9) {
                    int next = num * 10 + digit + K;
                    deque.offerLast(next);
                }
                if (K != 0 && digit - K >= 0) {
                    int next = num * 10 + digit - K;
                    deque.offerLast(next);
                }
            }
        }
        int[] res = new int[deque.size()];
        int index = 0;
        while (!deque.isEmpty()) {
            res[index++] = deque.pollFirst();
        }
        return res;
    }
}





