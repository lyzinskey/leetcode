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




// recursion
//
class Solution {
    // Time: O(n!)
    // Space: O(n)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        dfs(nums, 0, res);
        return res;
    }
    
    private void dfs(int[] nums, int index, List<List<Integer>> res) {		
		if (index == nums.length) {   
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
			res.add(list);
			return;
		}

		for (int i = index; i < nums.length; i++) {			
			swap(nums, i, index);
			dfs(nums, index + 1, res);
            swap(nums, i, index);
		}
	}   
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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



