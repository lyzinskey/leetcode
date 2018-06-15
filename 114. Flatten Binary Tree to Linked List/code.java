//Given a binary tree, flatten it to a linked list in-place.

//For example, given the following tree:
//
//      1
//     / \
//    2   5
//   / \   \
//  3   4   6
//
//The flattened tree should look like:
//
//  1
//   \
//    2
//     \
//      3
//       \
//        4
//         \
//          5
//           \
//            6



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
    //traverse
    //    
    private TreeNode lastNode = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}


    //non-recursion
    //
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            
            node.left = null;
            if (stack.isEmpty()) {
                node.right = null;
            }
            else {
                node.right = stack.peek();
            }
            
        }
    }
    
    
