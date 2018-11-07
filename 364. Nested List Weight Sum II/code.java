//Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

//Each element is either an integer, or a list -- whose elements may also be integers or other lists.

//Different from the previous question where weight is increasing from root to leaf, 
//now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, 
//and the root level integers have the largest weight.

//Example 1:
//
//Input: [[1,1],2,[1,1]]
//Output: 8 
//Explanation: Four 1's at depth 1, one 2 at depth 2.

//Example 2:
//
//Input: [1,[4,[6]]]
//Output: 17 
//Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.







/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 
// 通过BFS遍历nestedList中的所有元素
// level表示当前层遇到的所有integer的和
// prev表示从第一层到当前层遇到的所有integer的和
// total表示最后结果
// example：[1 [4 8 [6]]]
// level  1
// prev   1
// total  1
//
// level  4+8
// prev   1+(4+8)
// total  1+(1+(4+8))
//
// level  6
// prev   1+(4+8)+(6)
// total  1+(1+(4+8))+(1+(4+8)+(6))
// 不计算深度和权重，只通过重复计算来达到权重的效果，
// 1的深度是3就相当于重复计算3次

class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList  == null || nestedList.size() == 0) {
            return 0;
        }
        
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger list : nestedList) {
            queue.offer(list);
        }
        
        int prev = 0;
        int total = 0;
        
        while (!queue.isEmpty()) {
            int level = 0;
            int size = queue.size();            
            for (int i = 0; i < size; i++) {
                NestedInteger currentList = queue.poll();
                if (currentList.isInteger()) {
                    level += currentList.getInteger();
                } else {
                    List<NestedInteger> nextLevel = currentList.getList();
                    for (NestedInteger next : nextLevel) {
                       queue.offer(next);
                    }
                }
            }
            prev += level;
            total += prev;
        }        
        return total;
    }
}



