//Given two binary search trees root1 and root2.

//Return a list containing all the integers from both trees sorted in ascending order.

//Example 1:
//
//Input: root1 = [2,1,4], root2 = [1,0,3]
//Output: [0,1,1,2,3,4]

//Example 2:
//
//Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
//Output: [-10,0,0,1,2,5,7,10]

//Example 3:
//
//Input: root1 = [], root2 = [5,1,7,0,2]
//Output: [0,1,2,5,7]

//Example 4:
//
//Input: root1 = [0,-10,10], root2 = []
//Output: [-10,0,10]

//Example 5:
//
//Input: root1 = [1,null,8], root2 = [8,1]
//Output: [1,1,8,8]

//Constraints:
//
//Each tree has at most 5000 nodes.
//Each node's value is between [-10^5, 10^5].




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
    // inorder traverse + 谁小移谁
    // Time: O(n)
    // Space: O(n)
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        inorder(list1, root1);
        inorder(list2, root2);
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            if (list1.get(index1) < list2.get(index2)) {
                res.add(list1.get(index1));
                index1++;
            } else {
                res.add(list2.get(index2));
                index2++;
            }
        }
        
        while (index1 < list1.size()) {
            res.add(list1.get(index1));
            index1++;
        }
        while (index2 < list2.size()) {
            res.add(list2.get(index2));
            index2++;
        }
        return res;
    }
    
    private void inorder(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        
        inorder(list, root.left);
        list.add(root.val);
        inorder(list, root.right);
    }
}




