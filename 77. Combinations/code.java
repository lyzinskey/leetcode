//Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

//Example:
//
//Input: n = 4, k = 2
//Output:
//  [
//    [2,4],
//    [3,4],
//    [2,3],
//    [1,2],
//    [1,3],
//    [1,4],
//  ]



    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> resultList = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        
        combineHelper(resultList, result, n, k, 1);
        return resultList;
    }
    
    private void combineHelper(List<List<Integer>> resultList, ArrayList<Integer> result, int n, int k, int start) {
        if (result.size() == k) {
            resultList.add(new ArrayList<Integer> (result));
        }
        
        for (int i = start; i <= n; i++) {
            result.add(i);
            combineHelper(resultList, result, n, k, i + 1);
            result.remove(result.size() - 1);
        }
    }
    
    
