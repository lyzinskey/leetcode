//Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

//For example, given a 3-ary tree:
//https://leetcode.com/static/images/problemset/NaryTreeExample.png
 
//We should return its level order traversal:
//
//  [
//     [1],
//     [3,2,4],
//     [5,6]
//  ]




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
    public List<List<Integer>> levelOrder(Node root) {     
        List result = new ArrayList<Node>();
        
        if (root == null){
            return result;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()){
            ArrayList<Integer> child = new ArrayList<>();
            int size = queue.size();
            
            for (int i = 0; i < size; i++){
                Node node = queue.poll();
                child.add(node.val);
                for (Node childNode : node.children){
                    if (childNode != null){
                        queue.offer(childNode);
                    }
                }                
            }
            result.add(child);
        }
        return result;
    }    
}



