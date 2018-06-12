//Given a set of distinct integers, nums, return all possible subsets (the power set).

//Note: The solution set must not contain duplicate subsets.

//Example:
//
//Input: nums = [1,2,3]
//Output:
//  [
//    [3],
//    [1],
//    [2],
//    [1,2,3],
//    [1,3],
//    [2,3],
//    [1,2],
//    []
//  ]


// 递归：实现方式，一种实现DFS算法的一种方式
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null){
            return null;
        }
        
        if (nums.length == 0){
            result.add(new ArrayList<Integer>());
            return result;
        }
        
        Arrays.sort(nums);
        helper(new ArrayList<Integer>(), nums, 0, result);
        return result;
    }
    
    // 递归三要素
    // 1. 递归的定义：在 Nums 中找到所有以 subset 开头的的集合，并放到 results    
    private void helper(ArrayList<Integer> subset, int[] nums, int index, List<List<Integer>> result){

        // 2. 递归的拆解
        // deep copy
        // results.add(subset);        
        result.add(new ArrayList<Integer>(subset));
        
        for (int i = index; i < nums.length; i++){
            // [1] -> [1,2]
            subset.add(nums[i]);
            // 寻找所有以 [1,2] 开头的集合，并扔到 results
            helper(subset, nums, i + 1, result);
            // [1,2] -> [1]  回溯
            subset.remove(subset.size() - 1);            
        }
        // 3. 递归的出口
        // return;        
    }
    
    
//非递归：使用宽度优先搜索算法的做法（BFS）
//一层一层的找到所有的子集：
//
//  [] 
//  [1] [2] [3]
//  [1, 2] [1, 3] [2, 3]
//  [1, 2, 3]    
        public List<List<Integer>> subsets(int[] nums) {
        // List vs ArrayList （google）
        List<List<Integer>> results = new LinkedList<>();
        
        if (nums == null) {
            return results; // 空列表
        }
        
        Arrays.sort(nums);
        
        // BFS
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new LinkedList<Integer>());
        
        while (!queue.isEmpty()) {
            List<Integer> subset = queue.poll();
            results.add(subset);
            
            for (int i = 0; i < nums.length; i++) {
                if (subset.size() == 0 || subset.get(subset.size() - 1) < nums[i]) {
                    List<Integer> nextSubset = new LinkedList<Integer>(subset);
                    nextSubset.add(nums[i]);
                    queue.offer(nextSubset);
                }
            }
        }
        
        return results;
    }

