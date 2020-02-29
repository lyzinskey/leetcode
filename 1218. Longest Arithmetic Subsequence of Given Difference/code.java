//Given an integer array arr and an integer difference, 
//return the length of the longest subsequence in arr which is an arithmetic sequence 
//such that the difference between adjacent elements in the subsequence equals difference.

//Example 1:
//
//Input: arr = [1,2,3,4], difference = 1
//Output: 4
//Explanation: The longest arithmetic subsequence is [1,2,3,4].

//Example 2:
//
//Input: arr = [1,3,5,7], difference = 1
//Output: 1
//Explanation: The longest arithmetic subsequence is any single element.

//Example 3:
//
//Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
//Output: 4
//Explanation: The longest arithmetic subsequence is [7,5,3,1].

//Constraints:
//
//1 <= arr.length <= 10^5
//-10^4 <= arr[i], difference <= 10^4




class Solution {
    // Time: O(n)
    // Space: O(n)
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        int max = 1;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (hashmap.containsKey(num - difference)) {                
                hashmap.put(num, hashmap.get(num - difference) + 1);                
                max = Math.max(max, hashmap.get(num));
            } else {
                hashmap.put(num, 1);                
            }
        }
        return max;
    }
}



