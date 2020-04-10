//Given a binary tree where every node has a unique value, and a target key k, 
//find the value of the nearest leaf node to target k in the tree.

//Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. 
//Also, a node is called a leaf if it has no children.

//In the following examples, the input tree is represented in flattened form row by row. 
//The actual root tree given will be a TreeNode object.

//Example 1:
//
//Input:
//root = [1, 3, 2], k = 1
//Diagram of binary tree:
//          1
//         / \
//        3   2
//
//Output: 2 (or 3)
//
//Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.

//Example 2:
//
//Input:
//root = [1], k = 1
//Output: 1
//
//Explanation: The nearest leaf node is the root node itself.

//Example 3:
//
//Input:
//root = [1,2,3,4,null,null,null,5,null,6], k = 2
//Diagram of binary tree:
//             1
//            / \
//           2   3
//          /
//         4
//        /
//       5
//      /
//     6
//
//Output: 3
//Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.

//Note:
//root represents a binary tree with at least 1 node and at most 1000 nodes.
//Every node has a unique node.val in range [1, 1000].
//There exists some node in the given binary tree for which node.val == k.




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
    // First, preform DFS on root in order to find the node whose val = k, 
    // at the meantime use HashMap to keep record of all back edges from child to parent;
    // Then perform BFS on this node to find the closest leaf node.
    //
    // Time: O(n)
    // Space: O(n)
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> backMap = new HashMap<>();   // store all edges that trace node back to its parent
        Queue<TreeNode> queue = new LinkedList<>();          // the queue used in BFS
        Set<TreeNode> visited = new HashSet<>();             // store all visited nodes
        
        // DFS: search for node whoes val == k
        TreeNode kNode = DFS(root, k, backMap);
        queue.add(kNode);
        visited.add(kNode);
        
        // BFS: find the shortest path
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr.left == null && curr.right == null) {
                return curr.val;
            }
            if(curr.left != null && visited.add(curr.left)) {
                queue.add(curr.left);
            }
            if(curr.right != null && visited.add(curr.right)) {
                queue.add(curr.right);
            }
            if(backMap.containsKey(curr) && visited.add(backMap.get(curr))) {  // go alone the back edge
                queue.add(backMap.get(curr));
            }
        }
        return -1; // never hit
    }
    
    private TreeNode DFS(TreeNode root, int k, Map<TreeNode, TreeNode> backMap) {
        if(root.val == k) {
            return root;
        }
        if(root.left != null) {
            backMap.put(root.left, root);        // add back edge
            TreeNode left = DFS(root.left, k, backMap);
            if(left != null) {
                return left;
            }
        }
        if(root.right != null) {
            backMap.put(root.right, root);       // add back edge
            TreeNode right = DFS(root.right, k, backMap);
            if(right != null) {
                return right;
            }
        }
        return null;
    }
}



