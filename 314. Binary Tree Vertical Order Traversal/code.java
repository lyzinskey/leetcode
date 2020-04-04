//Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

//If two nodes are in the same row and column, the order should be from left to right.

//Examples 1:
//
//Input: [3,9,20,null,null,15,7]
//  
//     3
//    /\
//   /  \
//   9  20
//      /\
//     /  \
//    15   7 
//
//Output:
//
//  [
//    [9],
//    [3,15],
//    [20],
//    [7]
//  ]

//Examples 2:
//
//Input: [3,9,8,4,0,1,7]
//
//       3
//      /\
//     /  \
//     9   8
//    /\  /\
//   /  \/  \
//   4  01   7 
//
//Output:
//
//  [
//    [4],
//    [9],
//    [3,0,1],
//    [8],
//    [7]
//  ]

//Examples 3:
//
//Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
//
//       3
//      /\
//     /  \
//     9   8
//    /\  /\
//   /  \/  \
//   4  01   7
//      /\
//     /  \
//     5   2
//
//Output:
//
//  [
//    [4],
//    [9,5],
//    [3,0,1],
//    [8,2],
//    [7]
//  ]




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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, List<Integer>> hashmap = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int min = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> pair = queue.poll();
                TreeNode node = pair.getKey();
                int index = pair.getValue();
                if (!hashmap.containsKey(index)) {
                    hashmap.put(index, new ArrayList<>());
                }
                hashmap.get(index).add(node.val);
                min = Math.min(min, index);
                max = Math.max(max, index);
                
                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, index - 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, index + 1));
                }
            }
        }

        for (int i = min; i <= max; i++) {
            if (hashmap.containsKey(i)) {
                res.add(hashmap.get(i));
            }
        }
        return res;
    }
}



