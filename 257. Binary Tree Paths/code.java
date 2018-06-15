//Given a binary tree, return all root-to-leaf paths.

//Note: A leaf is a node with no children.

//Example:
//
//Input:
//
//     1
//   /   \
//  2     3
//   \
//    5
//
//Output: ["1->2->5", "1->3"]
//
//Explanation: All root-to-leaf paths are: 1->2->5, 1->3


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

    //divide & conquer
    //
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> path = new ArrayList<String>();
        
        if (root == null) {
            return path;
        }
        
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        
        for (String leftSub : left) {
            path.add(root.val + "->" + leftSub);
        }
        for (String rightSub : right) {
            path.add(root.val + "->" + rightSub);
        }
        
        if (path.size() == 0) {
            path.add("" + root.val);
        }
        return path;
    }
}



    //traversal
    //
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> path = new ArrayList<>();
        
        if (root == null) {
            return path;
        }
        
        helper(root, path, "" + root.val);
        return path;
    }
    
    private void helper(TreeNode root, List<String> path, String string) {
        if (root == null) {
            return;
        }
        
        if (root.left == null & root.right == null) {
            path.add(string);
        }
        
        if (root.left != null) {
            helper(root.left, path, string + "->" + root.left.val);
        }
        if (root.right != null) {
            helper(root.right, path, string + "->" + root.right.val);
        }
    }

