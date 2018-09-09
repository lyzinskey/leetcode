//Given a non-empty binary tree, find the maximum path sum.

//For this problem, a path is defined as any sequence of nodes 
//from some starting node to any node in the tree along the parent-child connections. 
//The path must contain at least one node and does not need to go through the root.

//Example 1:
//
//Input: [1,2,3]
//
//       1
//      / \
//     2   3
//
//Output: 6

//Example 2:
//
//Input: [-10,9,20,null,null,15,7]
//
//   -10
//   / \
//  9  20
//    /  \
//   15   7
//
//Output: 42



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
    public int maxPathSum(TreeNode root) {
        int[] max = {Integer.MIN_VALUE};
        getPath(root, max);
        return max[0];
    }
    
    private int getPath(TreeNode root, int[] max) {            
        if (root == null) {
            return 0;
        }
        // step 1：以当前节点为LCA的左右子树沿着一条path延伸的最大sum 
        // (即每次要么向左要么向右)
        int left = Math.max(getPath(root.left, max), 0);
        int right = Math.max(getPath(root.right, max), 0);
        // step 2：更新max
        max[0] = Math.max(max[0], left + right + root.val);
        // step 3：same as step 1
        return Math.max(left, right) + root.val;
    }
}



