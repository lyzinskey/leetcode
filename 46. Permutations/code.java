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


//recursion
//
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



//non-recursion
//
class Solution {
    public List<List<Integer>> permute(int[] A) {
        Arrays.sort(A);
        List<List<Integer>> result = new ArrayList<>();
        
        boolean next = true;  // next 为 true 时，表示可以继续迭代
        while (next)  {
            List<Integer> current = new ArrayList<>();  // 进行数组复制
            for (int a : A) {
                current.add(a);
            }
            
            result.add(current);
            next = nextPermutation(A);
        }
        return result;
    }
    
    public boolean nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }
        else {
            int j = len - 1;
            while (nums[j] <= nums[i - 1]) {
                j--;
            }
            swapItems(nums, i - 1, j);
        }
        swapList(nums, i, len - 1);
        return true;
    }
    
    private void swapItems(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void swapList(int[] nums, int i, int j) {
        while (i < j) {
            swapItems(nums, i, j);
            i++;
            j--;
        }
    }
}


