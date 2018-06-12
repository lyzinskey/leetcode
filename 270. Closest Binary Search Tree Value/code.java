//Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

//Note:
//
//Given target value is a floating point.
//You are guaranteed to have only one unique value in the BST that is closest to the target.

//Example:
//
//Input: root = [4,2,5,1,3], target = 3.714286
//
//      4
//     / \
//    2   5
//   / \
//  1   3
//
//Output: 4



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


//算法思路；求出 lowerBound 和 upperBound。即 小于等于 target 的最大值和 大于 target 的最小值。
//然后在两者之中去比较谁更接近，然后返回即可。

//时间复杂度为O(h)，注意使用 in-order traversal 的话，时间复杂度会是o(n)，并不是最优的。
//另外复杂度也不是 O(logn)， 因为BST 并不保证树高是 logn 的。

class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root == null){
            return 0;
        }
        
        TreeNode lowerBound = lowerBound(root, target);
        TreeNode upperBound = upperBound(root, target);
        
        if (lowerBound == null){
            return upperBound.val;
        }
        if (upperBound == null){
            return lowerBound.val;
        }
        
        if (Math.abs(lowerBound.val - target) < Math.abs(upperBound.val - target)){
            return lowerBound.val;
        }
        else{
            return upperBound.val;
        }
        
    }
    
    
    // find the node with the largest value that smaller than target    
    private TreeNode lowerBound(TreeNode root, double target){
        if (root == null){
            return null;
        }
        
        if (root.val >= target){
            return lowerBound(root.left, target);
        }
        
        TreeNode node = lowerBound(root.right, target);
        
        if (node != null){
            return node;
        }
        
        return root;
    }
    
    
    // find the node with the smallest value that larger than or equal to target    
    private TreeNode upperBound(TreeNode root, double target){
        if (root == null){
            return null;
        }
        
        if (root.val < target){
            return upperBound(root.right, target);
        }
        
        TreeNode node = upperBound(root.left, target);
        
        if (node != null){
            return node;
        }
        
        return root;
    }
}

