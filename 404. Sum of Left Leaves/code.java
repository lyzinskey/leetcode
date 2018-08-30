//Find the sum of all left leaves in a given binary tree.

//Example:
//
//      3
//     / \
//    9  20
//      /  \
//     15   7
//
//There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.



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
    public int sumOfLeftLeaves(TreeNode root) {
        int[] leftSum = {0};
        helper(root, leftSum);
        return leftSum[0];
    }
    
    private void helper(TreeNode root, int[] leftSum) {
        if (root == null) {
            return;
        }
        
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                leftSum[0] += root.left.val;
            }
            else {
                helper(root.left, leftSum);
            }
        }        
        helper(root.right, leftSum);        
    }
}



