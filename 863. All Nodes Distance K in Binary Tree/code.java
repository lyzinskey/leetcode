//We are given a binary tree (with root node root), a target node, and an integer value K.

//Return a list of the values of all nodes that have a distance K from the target node.  
//The answer can be returned in any order.

//Example 1:
//
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//
//Output: [7,4,1]
//
//Explanation: 
//The nodes that are a distance 2 from the target node (with value 5)
//have values 7, 4, and 1.

//Note that the inputs "root" and "target" are actually TreeNodes.
//The descriptions of the inputs above are just serializations of these objects.

//Note:
//
//The given tree is non-empty.
//Each node in the tree has unique values 0 <= node.val <= 500.
//The target node is a node in the tree.
//0 <= K <= 1000.





class Solution {
    // recursion dfs
    // Time: O(n)
    // Space: O(n)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        distanceToTarget(root, target, K, res);
        return res;
    }

    // Returns the distance from root to target.
    // Returns -1 if target does not in the tree.
    private int distanceToTarget(TreeNode root, TreeNode target, int K, List<Integer> res) {
        if (root == null) {
            return -1;
        }
        if (root.val == target.val) {
            collect(target, K, res);
            return 0;
        }

        int l = distanceToTarget(root.left, target, K, res);
        int r = distanceToTarget(root.right, target, K, res);

        // Target in the left subtree
        if (l >= 0) {
            if (l == K - 1) {
                res.add(root.val);
            }
            // Collect nodes in right subtree with depth K - l - 2
            collect(root.right, K - l - 2, res);
            return l + 1;
        }
        // Target in the right subtree
        if (r >= 0) {
            if (r == K - 1) {
                res.add(root.val);
            }
            // Collect nodes in left subtree with depth K - r - 2
            collect(root.left, K - r - 2, res);
            return r + 1;
        }
        return -1;
    }

    // Collect nodes that are k steps from node.
    private void collect(TreeNode node, int k, List<Integer> res) {
        if (node == null || k < 0) {
            return;
        }
        if (k == 0) {
            res.add(node.val);
        }
        collect(node.left, k - 1, res);
        collect(node.right, k - 1, res);
    }    
}





class Solution {
    // build graph + bfs
    // Time: O(n)
    // Space: O(n)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<Integer, List<TreeNode>> hashmap = new HashMap<>();
        buildGraph(null, root, hashmap);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        List<Integer> res = new ArrayList<>();
        int k = 0;

        while (!queue.isEmpty() && k <= K) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (k == K) {
                    res.add(node.val);
                }
                List<TreeNode> children = hashmap.containsKey(node.val) ? hashmap.get(node.val): new ArrayList<>();
                for (TreeNode child : children) {
                    if (visited.contains(child.val)) {
                        continue;
                    }
                    queue.offer(child);
                    visited.add(child.val);
                }
            }
            k++;
        }
        return res;
    }

    private void buildGraph(TreeNode parent, TreeNode child, Map<Integer, List<TreeNode>> hashmap) {
        if (parent != null) {
            if (!hashmap.containsKey(parent.val)) {
                hashmap.put(parent.val, new ArrayList<>());
            }
            if (!hashmap.containsKey(child.val)) {
                hashmap.put(child.val, new ArrayList<>());
            }
            hashmap.get(parent.val).add(child);
            hashmap.get(child.val).add(parent);
        }
        if (child.left != null) {
            buildGraph(child, child.left, hashmap);
        }
        if (child.right != null) {
            buildGraph(child, child.right, hashmap);
        }
    }
}



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 
 
 
 
