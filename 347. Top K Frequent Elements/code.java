//Given a non-empty array of integers, return the k most frequent elements.

//Example 1:
//
//Input: nums = [1,1,1,2,2,3], k = 2
//Output: [1,2]

//Example 2:
//
//Input: nums = [1], k = 1
//Output: [1]

//Note:
//
//You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
//Your algorithm's time complexity must be better than O(n log n), where n is the array's size.




class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashmap.containsKey(nums[i])) {
                hashmap.put(nums[i], hashmap.get(nums[i]) + 1);
            } else {
                hashmap.put(nums[i], 1);
            }
        }
        
        List<Integer>[] bucket = new List[nums.length + 1];
        
        for (int key : hashmap.keySet()) {
            int count = hashmap.get(key);
            if (bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(key);
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && result.size() < k; i--) {
            if (bucket[i] != null) {
                result.addAll(bucket[i]);
            }
        }
        return result;
    }
}



