//Return the root node of a binary search tree that matches the given preorder traversal.
//(Recall that a binary search tree is a binary tree where for every node, 
//any descendant of node.left has a value < node.val, 
//and any descendant of node.right has a value > node.val.  
//Also recall that a preorder traversal displays the value of the node first, 
//then traverses node.left, then traverses node.right.)

//Example 1:
//
//Input: [8,5,1,7,10,12]
//Output: [8,5,10,1,7,null,12]

//Note: 
//
//1 <= preorder.length <= 100
//The values of preorder are distinct.




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
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] index = new int[] {0};
        return dfs(preorder, index, Integer.MAX_VALUE);
    }
    
    private TreeNode dfs(int[] pre, int[] index, int max) {
        if (index[0] == pre.length || pre[index[0]] > max) {
            return null;
        }
        TreeNode root = new TreeNode(pre[index[0]++]);
        root.left = dfs(pre, index, root.val);
        root.right = dfs(pre, index, max);
        return root;
    }
}



