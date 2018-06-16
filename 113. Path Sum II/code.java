//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

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
//   /  \    / \
//  7    2  5   1
//Return:
//  
//  [
//     [5,4,11,2],
//     [5,8,4,5]
//  ]



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
    
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return result;
        }
        
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        helper(root, path, root.val, target);
        return result;        
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

