//Given a binary tree, return the inorder traversal of its nodes' values.

//Example:
//
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [1,3,2]

//Follow up: Recursive solution is trivial, could you do it iteratively?


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


//recursion
// 
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> nodeList = new ArrayList<>();
        inorder(root, nodeList);
        return nodeList;
    }
    
    private void inorder(TreeNode root, ArrayList<Integer> nodeList){
        if (root == null){
            return;
        }
        
        inorder(root.left, nodeList);
        nodeList.add(root.val);
        inorder(root.right, nodeList);
    }
}


//non-recursion (iterative)
//
//     public List<Integer> inorderTraversal(TreeNode root) {
//         ArrayList<Integer> nodeList = new ArrayList<>();
//         Stack<TreeNode> tree = new Stack<>();
        
//         if (root == null){
//             return nodeList;
//         }
        
//         while (root != null){
//             tree.push(root);
//             root = root.left;
//         }
        
//         while (!tree.empty()){
//             TreeNode curr = tree.peek();
//             nodeList.add(curr.val);
            
//             if (curr.right != null){
//                 curr = curr.right;
//                 while (curr != null){
//                     tree.push(curr);
//                     curr = curr.left;
//                 }
//             }
//             else {
//                 curr = tree.pop();
//                 while (!tree.empty() && tree.peek().right == curr){
//                     curr = tree.pop();
//                 }
//             }
//         }
        
//         return nodeList;
//     }

