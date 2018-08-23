//Given a n-ary tree, find its maximum depth.

//The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

//For example, given a 3-ary tree:
//                  1
//              /   |   \
//             3    2    4
//            / \
//           5   6 
//We should return its max depth, which is 3.

//Note:
//
//The depth of the tree is at most 1000.
//The total number of nodes is at most 5000.




/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {    
    private int max = 0;
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;        
        }
        getDepth(root, 1);
        return max;
    }
    
    private void getDepth(Node root, int depth) {    
        if (root == null) {
            return;
        }
        max = Math.max(max, depth);
        for (Node child : root.children) {            
            getDepth(child, depth + 1);            
        }        
    }
    
}



