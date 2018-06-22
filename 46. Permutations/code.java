//Given a collection of distinct integers, return all possible permutations.

//Example:
//
//Input: [1,2,3]
//Output:
//  [
//    [1,2,3],
//    [1,3,2],
//    [2,1,3],
//    [2,3,1],
//    [3,1,2],
//    [3,2,1]
//  ]



class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null) {
            return result;
        }
        
        dfs(nums, result, new boolean[nums.length], new ArrayList<Integer>());
        return result;
    }
    
    private void dfs(int[] nums, List<List<Integer>> result, boolean[] visited, ArrayList<Integer> list) {
        if (nums.length == list.size()) {
            result.add(new ArrayList<Integer> (list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            dfs(nums, result, visited, list);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}

