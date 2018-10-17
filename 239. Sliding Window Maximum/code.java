//Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
//You can only see the k numbers in the window. Each time the sliding window moves right by one position. 
//Return the max sliding window.

//Example:
//
//Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
//Output: [3,3,5,5,6,7] 

//Explanation: 
//
//  Window position                Max
//  ---------------               -----
//  [1  3  -1] -3  5  3  6  7       3
//   1 [3  -1  -3] 5  3  6  7       3
//   1  3 [-1  -3  5] 3  6  7       5
//   1  3  -1 [-3  5  3] 6  7       5
//   1  3  -1  -3 [5  3  6] 7       6
//   1  3  -1  -3  5 [3  6  7]      7

//Note: 
//You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

//Follow up:
//Could you solve it in linear time?





class Solution {
    public int[] maxSlidingWindow(int[] array, int k) {
        if (array == null || array.length == 0) {    
            return new int[0];
        }
        
        int[] result = new int[array.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        int index = 0;
        
        for (int i = 0; i < array.length; i++) {
            // 新来一个元素，将deque尾端所有小于新元素的全部poll出去
            while (!deque.isEmpty() && array[deque.peekLast()] <= array[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            // 移动sliding window左端点
            if (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.pollFirst();
            }

            // 只有当sliding window的size达到k才开始输出
            if (i >= k - 1) {
                result[index++] = array[deque.peekFirst()];
            }
        }
        return result;        
    }
}



