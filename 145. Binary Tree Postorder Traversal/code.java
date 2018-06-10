//Given a binary tree, return the postorder traversal of its nodes' values.

//Example:
//
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [3,2,1]

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
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> nodeList = new ArrayList<>();
        postorder(root, nodeList);
        return nodeList;
    }
    
    private void postorder(TreeNode root, ArrayList<Integer> nodeList){
        if (root == null){
            return;
        }
        
        postorder(root.left, nodeList);
        postorder(root.right, nodeList);
        nodeList.add(root.val);
    }
}



//non-recursion (iterative)
//
//    public ArrayList<Integer> postorderTraversal(TreeNode root) {
//        ArrayList<Integer> result = new ArrayList<Integer>();
//        Stack<TreeNode> stack = new Stack<TreeNode>();
//        TreeNode prev = null; // previously traversed node
//        TreeNode curr = root;
//
//        if (root == null) {
//            return result;
//        }
//
//        stack.push(root);
//        while (!stack.empty()) {
//            curr = stack.peek();
//            if (prev == null || prev.left == curr || prev.right == curr) { // traverse down the tree
//                if (curr.left != null) {
//                    stack.push(curr.left);
//                } else if (curr.right != null) {
//                    stack.push(curr.right);
//                }
//            } else if (curr.left == prev) { // traverse up the tree from the left
//                if (curr.right != null) {
//                    stack.push(curr.right);
//                }
//            } else { // traverse up the tree from the right
//                result.add(curr.val);
//                stack.pop();
//            }
//            prev = curr;
//        }
//
//        return result;
//    }

