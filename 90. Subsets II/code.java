//Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

//Note: The solution set must not contain duplicate subsets.

//Example:
//
//Input: [1,2,2]
//Output:
//  [
//    [2],
//    [1],
//    [1,2,2],
//    [2,2],
//    [1,2],
//    []
//  ]



class Solution {    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null) {
            return result;
        }
        
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        
        Arrays.sort(nums);
        
        dfs(nums, result, 0, new ArrayList<Integer>());

        return result;
    }
    
    private void dfs(int[] nums, List<List<Integer>> result, int index, ArrayList<Integer> subset) {
        result.add(new ArrayList<Integer> (subset));
        
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            
            subset.add(nums[i]);
            dfs(nums, result, i + 1, subset);
            subset.remove(subset.size() - 1);            
        }
    }    
}

