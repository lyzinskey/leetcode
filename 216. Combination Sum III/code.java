//Find all possible combinations of k numbers that add up to a number n, 
//given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

//Note:
//
//All numbers will be positive integers.
//The solution set must not contain duplicate combinations.

//Example 1:
//  
//  Input: k = 3, n = 7
//  Output: [[1,2,4]]

//Example 2:
//
//  Input: k = 3, n = 9
//  Output: [[1,2,6], [1,3,5], [2,3,4]]



class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        
        int[] candidates = new int[9];        
        for (int i = 0; i < 9; i++) {
            candidates[i] = i + 1;
        }
        
        ArrayList<Integer> subset = new ArrayList<>();        
        dfs(candidates, n, 0, result, subset, k);
        
        return result;         
    }
    
    private void dfs(int[] candidates, int target, int index, List<List<Integer>> result, ArrayList<Integer> subset, int k) {
        if (target == 0) {
            if (subset.size() == k) {
                result.add(new ArrayList<Integer>(subset));
            }
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
            //index传i而不是i + 1是因为一个元素可以使用多次
            //注意这里与上方for循环去重的区别            
            dfs(candidates, target - candidates[i], i + 1, result, subset, k);
            subset.remove(subset.size() - 1);
        }
    }    
}

