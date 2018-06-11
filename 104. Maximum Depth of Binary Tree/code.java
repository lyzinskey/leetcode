//Given a binary tree, find its maximum depth.

//The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

//Note: A leaf is a node with no children.

//Example:
//
//Given binary tree [3,9,20,null,null,15,7],
//
//      3
//     / \
//    9  20
//      /  \
//     15   7
//return its depth = 3.



/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


//BFS
//
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer
     */
     
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        
        while (!queue.isEmpty()){
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }
}


//traverse
//
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer
     */
    
    private int depth; 
     
    public int maxDepth(TreeNode root) {
        depth = 0;
        traverse(root, 1);
        return depth;
    }
    
    private void traverse(TreeNode root, int currDepth){
        if (root == null){
            return;
        }
        
        depth = Math.max(depth, currDepth);
        traverse(root.left, currDepth + 1);
        traverse(root.right, currDepth + 1);
    }
}


//Divide & Conquer
//
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer
     */
    
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        return Math.max(left, right) + 1;
    }

}

