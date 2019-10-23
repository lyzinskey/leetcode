//Given a complete binary tree, count the number of nodes.

//Note:
//
//Definition of a complete binary tree from Wikipedia:
//In a complete binary tree every level, except possibly the last, is completely filled, 
//and all nodes in the last level are as far left as possible. 
//It can have between 1 and 2h nodes inclusive at the last level h.

//Example:
//
//Input: 
//    1
//   / \
//  2   3
// / \  /
//4  5 6
//
//Output: 6





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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = getDepth(root);
        if (depth == 0) {
            return 1;
        }
        
        int left = 1;
        int right = (int) Math.pow(2, depth) - 1;
        int count = right;        
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (exist(mid, depth, root)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return count + left;
    }
    
    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }        
        int depth = 0;
        while (root.left != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }
    
    private boolean exist(int pivot, int depth, TreeNode node) {
        int left = 0;
        int right = (int) Math.pow(2, depth) - 1;
        for (int i = 0; i < depth; i++) {
            int mid = left + (right - left) / 2;
            if (mid >= pivot) {
                right = mid;
                node = node.left;
            } else {
                left = mid + 1;
                node = node.right;
            }
        }
        return node != null;
    }
}



