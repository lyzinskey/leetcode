//Given two arrays, write a function to compute their intersection.

//Example:
//Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

//Note:
//Each element in the result should appear as many times as it shows in both arrays.
//The result can be in any order.

//Follow up:
//What if the given array is already sorted? How would you optimize your algorithm?
//What if nums1's size is small compared to nums2's size? Which algorithm is better?
//What if elements of nums2 are stored on disk, 
//and the memory is limited such that you cannot load all elements into the memory at once?



class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        
        for (int num : nums1) {
            if (hashmap.containsKey(num)) {
                hashmap.put(num, hashmap.get(num) + 1);
            }
            else {
                hashmap.put(num, 1);
            }
        }
        
        List<Integer> duplicate = new ArrayList<>();
        
        for (int num : nums2) {
            if (hashmap.containsKey(num) && hashmap.get(num) > 0) {
                hashmap.put(num, hashmap.get(num) - 1);
                duplicate.add(num);
            }
        }
        
        int[] result = new int[duplicate.size()];
        for (int i = 0; i < duplicate.size(); i++) {
            result[i] = duplicate.get(i);
        }
        
        return result;        
    }
}


