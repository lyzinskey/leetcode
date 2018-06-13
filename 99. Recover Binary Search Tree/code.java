//Two elements of a binary search tree (BST) are swapped by mistake.

//Recover the tree without changing its structure.

//Example 1:
//
//Input: [1,3,null,null,2]
//     1
//    /
//   3
//    \
//     2
//
//Output: [3,1,null,null,2]
//     3
//    /
//   1
//    \
//     2

//Example 2:
//
//Input: [3,1,4,null,null,2]
//    3
//   / \
//  1   4
//     /
//    2
//
//Output: [2,1,4,null,null,3]
//    2
//   / \
//  1   4
//     /
//    3

//Follow up:
//
//A solution using O(n) space is pretty straight forward.
//Could you devise a constant space solution?


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
    
    //对整个树中序遍历一次，并存储结果
    //对结果从前往后，从后往前遍历两次     
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        ArrayList<TreeNode> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);

        if (inorder.size() < 2) {
            return;
        }

        ArrayList<TreeNode> result = new ArrayList<>();
        for (int i = 0; i < inorder.size() - 1; i++) {
            if (inorder.get(i).val > inorder.get(i + 1).val) {
                swap(inorder.get(i), inorder.get(i + 1));
            }
        }

        for (int i = inorder.size() - 1; i > 0; i--) {
            if (inorder.get(i).val < inorder.get(i - 1).val) {
                swap(inorder.get(i), inorder.get(i - 1));
            }
        }
        
    }
    
    private void inorderTraversal(TreeNode root, ArrayList<TreeNode> swap) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, swap);
        swap.add(root);
        inorderTraversal(root.right, swap);
    }

    private void swap(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }   
    
}


//O(n) time, O(1) space solution
//
class Solution {
    TreeNode first = null;
    TreeNode last = null;
    TreeNode prev = null;
    
    public void recoverTree(TreeNode root) {
        if (root == null){
            return;
        }
        
        inorder(root);
        
        int temp = first.val;
        first.val = last.val;
        last.val = temp;
        return;        
    }
    
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        
        if(prev != null && prev.val >= root.val){
            if(first == null) {
                first = prev;
            }
            last = root;
        }
        prev = root;
        
        inorder(root.right);
    }    
}
