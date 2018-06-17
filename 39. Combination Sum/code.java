//Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
//find all unique combinations in candidates where the candidate numbers sums to target.

//The same repeated number may be chosen from candidates unlimited number of times.

//Note:
//
//All numbers (including target) will be positive integers.
//The solution set must not contain duplicate combinations.

//Example 1:
//
//Input: candidates = [2,3,6,7], target = 7,
//A solution set is:
//  [
//    [7],
//    [2,2,3]
//  ]

//Example 2:
//  
//Input: candidates = [2,3,5], target = 8,
//A solution set is:
//  [
//    [2,2,2,2],
//    [2,3,3],
//    [3,5]
//  ]



class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (candidates == null || target <= 0) {
            return result;
        }
        ArrayList<Integer> subset = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, result, subset);
        
        return result;
    }
    
    private void dfs(int[] candidates, int target, int index, List<List<Integer>> result, ArrayList<Integer> subset) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(subset));
            return;
        }
        
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            subset.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, result, subset);
            subset.remove(subset.size() - 1);
        }
    }
}

