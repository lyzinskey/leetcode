//Given the root of a binary tree, each node in the tree has a distinct value.

//After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

//Return the roots of the trees in the remaining forest.  You may return the result in any order.

//Example 1:
//
//Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
//Output: [[1,2,null,4],[6],[7]]

//Constraints:
//
//The number of nodes in the given tree is at most 1000.
//Each node has a distinct value between 1 and 1000.
//to_delete.length <= 1000
//to_delete contains distinct values between 1 and 1000.




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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null || to_delete == null || to_delete.length == 0) {
            return new ArrayList<>();
        }
        
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> hashset = new HashSet<>();
        for (int num : to_delete) {
            hashset.add(num);
        }
        
        dfs(root, result, hashset);
        if (!hashset.contains(root.val)) {
            result.add(root);
        }
        return result;
    }
    
    private TreeNode dfs(TreeNode root, List<TreeNode> result, Set<Integer> hashset) {
        if (root == null) {
            return null;
        }
        
        root.left = dfs(root.left, result, hashset);
        root.right = dfs(root.right, result, hashset);
        if (hashset.contains(root.val)) {
            if (root.left != null) {
                result.add(root.left);
            }
            if (root.right != null) {
                result.add(root.right);
            }
            return null;
        }
        return root;        
    }
}



