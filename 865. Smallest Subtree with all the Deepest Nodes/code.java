//Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

//A node is deepest if it has the largest depth possible among any node in the entire tree.

//The subtree of a node is that node, plus the set of all descendants of that node.

//Return the node with the largest depth such that it contains all the deepest nodes in its subtree.

//Example 1:
//
//Input: [3,5,1,6,2,0,8,null,null,7,4]
//Output: [2,7,4]
//Explanation:
//
//We return the node with value 2, colored in yellow in the diagram.
//The nodes colored in blue are the deepest nodes of the tree.
//The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
//The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
//Both the input and output have TreeNode type.

//Note:
//
//The number of nodes in the tree will be between 1 and 500.
//The values of each node are unique.




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
    // get height + lowest common ancestor
    // pair<height of curr node, lca of curr node>
    // walk through the whole tree and return pair
    // 
    // Time: O(n)
    // Space: O(height)
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).getValue();
    }

    private Pair<Integer, TreeNode> dfs(TreeNode root) {
        if (root == null) {
            return new Pair<>(-1, new TreeNode(-1));
        }
        Pair<Integer, TreeNode> left = dfs(root.left);
        Pair<Integer, TreeNode> right = dfs(root.right);
        if (left.getKey() == -1 && right.getKey() == -1) {
            return new Pair<>(0, root);
        } else if (left.getKey() == -1 || right.getKey() == -1) {
            int height = left.getKey() == -1 ? right.getKey() : left.getKey();
            TreeNode node = left.getKey() == -1 ? right.getValue() : left.getValue();
            return new Pair<>(height + 1, node);
        } else {
            int height = Math.max(left.getKey(), right.getKey());
            TreeNode node;
            if (left.getKey() == right.getKey()) {
                node = root;
            } else {
                node = left.getKey() > right.getKey() ? left.getValue() : right.getValue();
            }
            return new Pair<>(height + 1, node);
        }
    }
}



