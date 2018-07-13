//Given two arrays, write a function to compute their intersection.

//Example:
//Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

//Note:
//Each element in the result must be unique.
//The result can be in any order.



class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> hashset1 = new HashSet<>();
        Set<Integer> hashset2 = new HashSet<>();
        
        for (int nums : nums1) {
            hashset1.add(nums);
        }
        
        for (int nums : nums2) {
            if (hashset1.contains(nums)) {
                hashset2.add(nums);
            }
        }
        
        int[] result = new int[hashset2.size()];
        int i = 0;
        
        for (int nums : hashset2) {
            result[i] = nums;
            i++;
        }
        
        return result;
    }
}


