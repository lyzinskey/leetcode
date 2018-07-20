//Given a binary tree, determine if it is a valid binary search tree (BST).

//Assume a BST is defined as follows:
//
//The left subtree of a node contains only nodes with keys less than the node's key.
//The right subtree of a node contains only nodes with keys greater than the node's key.
//Both the left and right subtrees must also be binary search trees.

//Example 1:
//
//Input:
//      2
//     / \
//    1   3
//Output: true

//Example 2:
//
//      5
//     / \
//    1   4
//       / \
//      3   6
//Output: false
//Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
//             is 5 but its right child's value is 4.




/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


//use inorder traversal
//
class Solution {
    private boolean isValid;
    private TreeNode lastNode;
    
    public boolean isValidBST(TreeNode root) {
        isValid = true;
        lastNode = null;
        inorderTraversal(root);
        return isValid;
    }
    
    private void inorderTraversal(TreeNode root){
        if (root == null){
            return;
        }
        
        inorderTraversal(root.left);
        
        if (lastNode != null && lastNode.val >= root.val){
            isValid = false;
            return;
        }
        lastNode = root;
        inorderTraversal(root.right);
    }
}



//Divide & Conquer
//
class Solution {

    class ResultType {
        public boolean isValid;
        public TreeNode maxNode;
        public TreeNode minNode;
    
        public ResultType(boolean isValid){
            this.isValid = isValid;
            maxNode = null;
            minNode = null;
        }
    }    
    public boolean isValidBST(TreeNode root) {
        return divideConquer(root).isValid;
    }
    
    private ResultType divideConquer(TreeNode root){
        if (root == null){
            return new ResultType(true);
        }
        
        ResultType left = divideConquer(root.left);
        ResultType right = divideConquer(root.right);
        
        if (!left.isValid || !right.isValid){
            return new ResultType(false);
        }
        
        if (left.maxNode != null && left.maxNode.val >= root.val){
            return new ResultType(false);
        }
        
        if (right.minNode != null && right.minNode.val <= root.val){
            return new ResultType(false);
        }
        
        ResultType result = new ResultType(true);
        
        if (right.maxNode != null){
            result.maxNode = right.maxNode;
        }
        else {
            result.maxNode = root;
        }
        
        if (left.minNode != null){
            result.minNode = left.minNode;
        }
        else {
            result.minNode = root;
        }
        
        return result;
    }
}




// Recursion
//
class Solution {
    public boolean isValidBST(TreeNode root) {
        return BST_helper(root, 2147483647, -2147483648);
    }
  
    private boolean BST_helper(TreeNode root, long max, long min) {
        if (root == null) {
            return true;
        }    
        
        if ((long) root.val < min || (long) root.val > max) {
            return false;
        }
    
        return BST_helper(root.left, (long) root.val - 1, min) && BST_helper(root.right, max, (long) root.val + 1);
    }  
}


