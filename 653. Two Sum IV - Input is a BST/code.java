//Given a Binary Search Tree and a target number, 
//return true if there exist two elements in the BST such that their sum is equal to the given target.

//Example 1:
//Input: 
//      5
//     / \
//    3   6
//   / \   \
//  2   4   7
//
//Target = 9
//
//Output: True

//Example 2:
//Input: 
//      5
//     / \
//    3   6
//   / \   \
//  2   4   7
//
//Target = 28
//
//Output: False



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
    public boolean findTarget(TreeNode root, int n) {
        if (root == null) {
            return false;
        }
                
        ArrayList<Integer> inorder = new ArrayList<>();
        
        inorder = inorderTraversal(root, inorder);
        
        int left = 0;
        int right = inorder.size() - 1;
        
        while (left < right){
            if (inorder.get(left) + inorder.get(right) == n){                                
                return true;
            }
            else if (inorder.get(left) + inorder.get(right) < n){
                left++;
            }
            else {
                right--;
            }
        }
        
        return false;
    }
    
    private ArrayList<Integer> inorderTraversal(TreeNode root, ArrayList<Integer> result){
        inorder(root, result);
        return result;
    }
    
    private void inorder(TreeNode root, ArrayList<Integer> inorder){
        if (root == null){
            return;
        }
        
        inorder(root.left, inorder);
        inorder.add(root.val);
        inorder(root.right, inorder);
    }
    
}

