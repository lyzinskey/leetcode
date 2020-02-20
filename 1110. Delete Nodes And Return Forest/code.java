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
    // Time: O(n)
    // Space: O(n)
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> hashset = new HashSet<>();
        
        for (int num : to_delete) {
            hashset.add(num);
        }
        
        dfs(root, hashset, res);
        if (!hashset.contains(root.val)) {
            res.add(root);
        }
        return res;
    }
    
    // return the root of tree after deletion
    private TreeNode dfs(TreeNode root, Set<Integer> hashset, List<TreeNode> res) {
        if (root == null) {
            return null;
        }
        
        root.left = dfs(root.left, hashset, res);
        root.right = dfs(root.right, hashset, res);
        if (hashset.contains(root.val)) {
            if (root.left != null) {
                res.add(root.left);
            }
            if (root.right != null) {
                res.add(root.right);
            }
            return null;
        }
        return root;
    }
}



