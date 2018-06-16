//Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

//Note: A leaf is a node with no children.

//Example:
//
//Given the below binary tree and sum = 22,
//  
//        5
//       / \
//      4   8
//     /   / \
//    11  13  4
//   /  \      \
//  7    2      1
//return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.



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
    
    private List<List<Integer>> result = new ArrayList<>();
    
    public boolean hasPathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        helper(root, path, root.val, target);
        return result.size() == 0 ? false : true;        
    }
    
    private void helper(TreeNode root, ArrayList<Integer> path, int sum, int target) {
                            
        // meet leaf
        if (root.left == null && root.right == null) {
            if (sum == target) {
                result.add(new ArrayList<Integer>(path));
            }
            return;
        }
        
        // go left
        if (root.left != null) {
            path.add(root.left.val);
            helper(root.left, path, sum + root.left.val, target);
            path.remove(path.size() - 1);
        }
        
        // go right
        if (root.right != null) {
            path.add(root.right.val);
            helper(root.right, path, sum + root.right.val, target);
            path.remove(path.size() - 1);
        }
    }    
}

