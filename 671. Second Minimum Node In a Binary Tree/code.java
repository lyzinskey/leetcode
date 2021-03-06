//Given a non-empty special binary tree consisting of nodes with the non-negative value, 
//where each node in this tree has exactly two or zero sub-node. 
//If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. 
//More formally, the property root.val = min(root.left.val, root.right.val) always holds.

//Given such a binary tree, 
//you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
//If no such second minimum value exists, output -1 instead.

//Example 1:
//
//Input: 
//    2
//   / \
//  2   5
//     / \
//    5   7
//
//Output: 5
//Explanation: The smallest value is 2, the second smallest value is 5.

//Example 2:
//
//Input: 
//    2
//   / \
//  2   2
//
//Output: -1
//Explanation: The smallest value is 2, but there isn't any second smallest value.




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
    // Time: O(n)
    // Space: O(height)
    public int findSecondMinimumValue(TreeNode root) {
        int[] array = {root.val, root.val};
        preOrder(root, array);        
        return array[0] == root.val ? -1 : array[0];
    }
    
    private void preOrder(TreeNode root, int[] array) {
        if (root == null) {
            return;
        }
        if (array[0] == array[1] || root.val != array[1] && root.val < array[0]) {
            array[0] = root.val;
        }
        preOrder(root.left, array);
        preOrder(root.right, array);
    }
}






class Solution {
    // Time: O(n) there could be pruning, but the worst case is still O(n)
    // Space: O(height)    
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }
    
        int left = root.left.val;
        int right = root.right.val;
    
        // if value same as root value, need to find the next candidate
        // we need to find candidate in the subtree
        if (root.left.val == root.val) {
            left = findSecondMinimumValue(root.left);
        }
        if (root.right.val == root.val) {
            right = findSecondMinimumValue(root.right);
        }
        // left and right are both candidate
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }
        return left != -1 ? left : right;
    }  
}





