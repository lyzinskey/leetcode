//Given preorder and inorder traversal of a tree, construct the binary tree.

//Note:
//You may assume that duplicates do not exist in the tree.

//For example, given
//
//preorder = [3,9,20,15,7]
//inorder = [9,3,15,20,7]
//Return the following binary tree:
//
//      3
//     / \
//    9  20
//      /  \
//     15   7


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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length != preorder.length){
            return null;
        }

        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend){
        if (instart > inend){
            return null;
        }

        TreeNode root = new TreeNode(preorder[prestart]);

        int position = findRootPosition(inorder, instart, inend, root.val);

        //找到root的position之后，左子树点的个数等于position - instart
        root.left = build(preorder, prestart + 1, prestart + position - instart, inorder, instart, position - 1);
        root.right = build(preorder, prestart + 1 + position - instart, preend, inorder, position + 1, inend);

        return root;
    }

    private int findRootPosition(int[] tree, int start, int end, int key){
        for (int i = start; i <= end; i++) {
            if (tree[i] == key){
                return i;
            }
        }
        return -1;
    }
}

