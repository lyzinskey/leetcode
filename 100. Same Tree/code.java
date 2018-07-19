//Given two binary trees, write a function to check if they are the same or not.

//Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

//Example 1:
//
//Input:     1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
//Output: true

//Example 2:
//
//Input:     1         1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
//Output: false

//Example 3:
//
//Input:     1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
//Output: false



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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return symmetric(p, q);
    }
  
    private boolean symmetric(TreeNode left, TreeNode right) {
      if (left == null && right == null) {
        return true;
      }
    
      if (left == null || right == null) {
        return false;
      }
    
      if (left.val != right.val) {
        return false;
      }    
    
      return symmetric(left.left, right.left) && symmetric(left.right, right.right);    
    }    
}


