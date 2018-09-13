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
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        if (inOrder.length != postOrder.length || inOrder.length == 0){
            return null;
        }

        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            idxMap.put(inOrder[i], i);
        }

        return reconstruct(inOrder, 0, inOrder.length - 1,
                postOrder, 0, postOrder.length - 1,
                idxMap);
    }

    private TreeNode reconstruct(int[] inOrder, int inLeft, int inRight,
                                 int[] postOrder, int postLeft, int postRight,
                                 Map<Integer, Integer> idxMap) {
        if (inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(postOrder[postRight]);
        int leftSize = idxMap.get(root.val) - inLeft;

        root.left = reconstruct(inOrder, inLeft, inLeft + leftSize - 1,
                postOrder, postLeft, postLeft + leftSize - 1,
                idxMap);
        root.right = reconstruct(inOrder, inLeft + leftSize + 1, inRight,
                postOrder, postLeft + leftSize, postRight - 1,
                idxMap);
        return root;
    }
}



