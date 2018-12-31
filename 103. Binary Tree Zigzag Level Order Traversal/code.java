//Given a binary tree, return the zigzag level order traversal of its nodes' values. 
//(ie, from left to right, then right to left for the next level and alternate between).

//For example:
//Given binary tree [3,9,20,null,null,15,7],
//      3
//     / \
//    9  20
//      /  \
//     15   7
//
//return its zigzag level order traversal as:
//  [
//    [3],
//    [20,9],
//    [15,7]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        int layer = 1;

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> layerList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                if (layer == 0) {
                    TreeNode node = deque.pollLast();
                    layerList.add(node.val);
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }
                } else {
                    TreeNode node = deque.pollFirst();
                    layerList.add(node.val);
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                }                
            }
            layer = 1 - layer;
            result.add(layerList);
        }
        return result;
    }
}




