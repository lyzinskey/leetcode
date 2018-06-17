//Given a collection of candidate numbers (candidates) and a target number (target), 
//find all unique combinations in candidates where the candidate numbers sums to target.

//Each number in candidates may only be used once in the combination.

//Note:
//
//All numbers (including target) will be positive integers.
//The solution set must not contain duplicate combinations.

//Example 1:
//
//Input: candidates = [10,1,2,7,6,1,5], target = 8,
//A solution set is:
//  [
//    [1, 7],
//    [1, 2, 5],
//    [2, 6],
//    [1, 1, 6]
//  ]

//Example 2:
//
//Input: candidates = [2,5,2,1,2], target = 5,
//A solution set is:
//  [
//    [1,2,2],
//    [5]
//  ]



class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            
            //去重，[1, 1, 2, 2，2]保证只使用重复元素的第一个
            //即只用第一个1和第一个2，第二个1和第二第三个2跳过            
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            subset.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1, result, subset);
            subset.remove(subset.size() - 1);
        }
    }     
}

