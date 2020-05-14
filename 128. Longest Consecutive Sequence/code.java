//Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

//Your algorithm should run in O(n) complexity.

//Example:
//
//Input: [100, 4, 200, 1, 3, 2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.




class Solution {
    // hashtable: (key, len)
    //
    // case 1: no neighbor
    // h[key] = 1
    //
    // case 2: has a single neighbor, extend
    // l = h[key +1/-1]
    // h[key +1/-1] = h[key] = l + 1
    //
    // case 3: has two neighbors, a bridge
    // l = h[key - 1]
    // r = h[key + 1]
    // t = l + r + 1
    // h[key - l] = h[key + r] = t
    //
    // Time: O(n)
    // Space: O(n)
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> hashmap = new HashMap<>();
        int max = 1;
        for (int num : nums) {
            if (hashmap.containsKey(num)) {
                continue;
            }
            
            int left = hashmap.getOrDefault(num - 1, 0);
            int right = hashmap.getOrDefault(num + 1, 0);
            int sum = left + right + 1;
            hashmap.put(num, sum);
            if (left != 0 && right != 0) {
                hashmap.put(num - left, sum);
                hashmap.put(num + right, sum);
            } else if (left == 0) {
                hashmap.put(num + right, sum);
            } else if (right == 0) {
                hashmap.put(num - left, sum);
            }
            max = Math.max(max, sum);
        }
        return max;
    }  
}



