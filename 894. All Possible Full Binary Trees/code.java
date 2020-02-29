//A full binary tree is a binary tree where each node has exactly 0 or 2 children.

//Return a list of all possible full binary trees with N nodes.  
//Each element of the answer is the root node of one possible tree.

//Each node of each tree in the answer must have node.val = 0.

//You may return the final list of trees in any order.

//Example 1:
//
//Input: 7
//Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],
//         [0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

//Note:
//
//1 <= N <= 20




/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // Time: O(2^n)
    // Space: O(2^n)
    public List<TreeNode> allPossibleFBT(int N) {
        Map<Integer, List<TreeNode>> hashmap = new HashMap<>();
        return dfs(N, hashmap);
    }
    
    private List<TreeNode> dfs(int N, Map<Integer, List<TreeNode>> hashmap) {
        List<TreeNode> res = new ArrayList<>();
        // if N is even, impossible to create full binary tree
        if (N % 2 == 0) {
            return res;
        }
        if (hashmap.containsKey(N)) {
            return hashmap.get(N);
        }
        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        
        for (int i = 1; i < N; i += 2) {     
            List<TreeNode> leftList = allPossibleFBT(i);
            List<TreeNode> rightList = allPossibleFBT(N - i - 1);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);                    
                }
            }            
        }
        hashmap.put(N, res);
        return res;
    }  
}




