//Given a binary tree, return the preorder traversal of its nodes' values.

//Example:
//
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [1,2,3]

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
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> nodeList = new ArrayList<>();
        preorder(root, nodeList);
        return nodeList;
    }
    
    private void preorder(TreeNode root, ArrayList<Integer> nodeList){
        if (root == null){
            return;
        }
        
        nodeList.add(root.val);
        preorder(root.left, nodeList);
        preorder(root.right, nodeList);
    }
}



//non-recursion (iterative)
//
//    public static List<Integer> preorderTraversal(TreeNode root) {
//        ArrayList<Integer> nodeList = new ArrayList<>();
//        Stack<TreeNode> treeStack = new Stack<>();
//
//        if (root == null){
//            return nodeList;
//        }
//
//        treeStack.push(root);
//
//        while (!treeStack.empty()){
//            TreeNode node = treeStack.pop();
//            nodeList.add(node.val);
//
//            if (node.right != null){
//                treeStack.push(node.right);
//            }
//            if (node.left != null){
//                treeStack.push(node.left);
//            }
//        }
//
//        return nodeList;
//    }

