//Numbers can be regarded as product of its factors. For example,

//8 = 2 x 2 x 2;
//  = 2 x 4.
//Write a function that takes an integer n and return all possible combinations of its factors.

//Note:
//
//You may assume that n is always positive.
//Factors should be greater than 1 and less than n.

//Example 1:
//
//Input: 1
//Output: []

//Example 2:
//
//Input: 37
//Output:[]

//Example 3:
//
//Input: 12
//Output:
//[
//  [2, 6],
//  [2, 2, 3],
//  [3, 4]
//]

//Example 4:
//
//Input: 32
//Output:
//[
//  [2, 16],
//  [2, 2, 8],
//  [2, 2, 2, 4],
//  [2, 2, 2, 2, 2],
//  [2, 4, 4],
//  [4, 8]
//]




class Solution {
    public List<List<Integer>> getFactors(int target) {
        if (target <= 1) {
            return new ArrayList<>();
        }
        
        List<Integer> factors = new ArrayList<>();
        for (int i = target / 2; i >= 2; i--) {
            if (target % i == 0) {
                factors.add(i);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), target, 0, factors);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> list, int remain, int index, List<Integer> factors) {
        if (index == factors.size()) {
            if (remain == 1) {
                result.add(new ArrayList<>(list));
            }
            return;
        }
        dfs(result, list, remain, index + 1, factors);
        int factor = factors.get(index);
        if (remain < factor || remain % factor != 0) {
            return;
        }
        int size = getNum(remain, factor);
        for (int i = 0; i < size; i++) {
            list.add(factor);
            remain /= factor;
            dfs(result, list, remain, index + 1, factors);
        }
        for (int i = 0; i < size; i++) {
            list.remove(list.size() - 1);
        }
    }

    private int getNum(int target, int factor) {
        if (factor > target) {
            return 0;
        }
        int count = 0;
        int base = factor;
        while (target % factor == 0) {
            factor *= base;
            count++;
        }
        return count;
    }    
}



