//Return any binary tree that matches the given preorder and postorder traversals.

//Values in the traversals pre and post are distinct positive integers.

//Example 1:
//
//Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
//Output: [1,2,3,4,5,6,7]

//Note:
//
//1 <= pre.length == post.length <= 30
//pre[] and post[] are both permutations of 1, 2, ..., pre.length.
//It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.




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
    // Time: O(n^2)
    // Space: O(n)
    // complexity analysis like quick sort
    public static TreeNode constructFromPrePost(int[] pre, int[] post) {
        return construct(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    public static TreeNode construct(int[] pre, int preLeft, int preRight, int[] post, int postLeft, int postRight) {
        if (preLeft > preRight || postLeft > postRight) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        if (preLeft == preRight) {
            return root;
        }

        int index = -1;
        for (int i = postLeft; i < postRight; i++) {
            if (pre[preLeft + 1] == post[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return root;
        }

        root.left = construct(pre, preLeft + 1, preLeft + 1 + (index - postLeft), post, postLeft, index);
        root.right = construct(pre, preLeft + 1 + (index - postLeft) + 1, preRight, post, index + 1, postRight);
        return root;
    }
}



