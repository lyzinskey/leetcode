//You are given a binary tree in which each node contains an integer value.

//Find the number of paths that sum to a given value.

//The path does not need to start or end at the root or a leaf, 
//but it must go downwards (traveling only from parent nodes to child nodes).

//The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

//Example:
//  
//root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//        10
//       /  \
//      5   -3
//     / \    \
//    3   2   11
//   / \   \
//  3  -2   1
//
//Return 3. The paths that sum to 8 are:
//
//  1.  5 -> 3
//  2.  5 -> 2 -> 1
//  3. -3 -> 11



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
    private int result = 0;
    
    public int pathSum(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        getPath(root, target, path);
        
        return result; 
    }
    
    private void getPath(TreeNode root, int target, List<Integer> path) {
        int sum = 0;
        
        for(int i = path.size() - 1; i >= 0; i--) {
            sum += path.get(i);
            if (sum == target) {
                result++;
            }
        }
        
        if (root.left != null) {
            path.add(root.left.val);
            getPath(root.left, target, path);
            path.remove(path.size() - 1);
        }
        
        if (root.right != null) {
            path.add(root.right.val);
            getPath(root.right, target, path);
            path.remove(path.size() - 1);
        }
    
    }
    
}

