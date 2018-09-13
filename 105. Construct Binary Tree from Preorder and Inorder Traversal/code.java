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
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (inOrder.length != preOrder.length || inOrder.length == 0){
            return null;
        }

        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            idxMap.put(inOrder[i], i);
        }

        return reconstruct(inOrder, 0, inOrder.length - 1,
                preOrder, 0, preOrder.length - 1,
                idxMap);
    }

    private TreeNode reconstruct(int[] inOrder, int inLeft, int inRight,
                                 int[] preOrder, int preLeft, int preRight,
                                 Map<Integer, Integer> idxMap) {
        if (inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preLeft]);
        int leftSize = idxMap.get(root.val) - inLeft;

        root.left = reconstruct(inOrder, inLeft, inLeft + leftSize - 1,
                preOrder, preLeft + 1, preLeft + leftSize,
                idxMap);
        root.right = reconstruct(inOrder, inLeft + leftSize + 1, inRight,
                preOrder, preLeft + leftSize + 1, preRight,
                idxMap);
        return root;
    }
}




