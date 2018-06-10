//Given inorder and postorder traversal of a tree, construct the binary tree.

//Note:
//You may assume that duplicates do not exist in the tree.

//For example, given
//
//inorder = [9,3,15,20,7]
//postorder = [9,15,7,20,3]
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length){
            return null;
        }

        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int instart, int inend, int[] postorder, int poststart, int postend){
        if (instart > inend){
            return null;
        }

        TreeNode root = new TreeNode(postorder[postend]);

        int position = findRootPosition(inorder, instart, inend, root.val);

        //找到root的position之后，左子树点的个数等于position - instart
        root.left = build(inorder, instart, position - 1, postorder, poststart, (poststart + position - instart) - 1);
        root.right = build(inorder, position + 1, inend, postorder, poststart + position - instart, postend - 1);

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

