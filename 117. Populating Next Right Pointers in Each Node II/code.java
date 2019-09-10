//Given a binary tree

//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}
//Populate each next pointer to point to its next right node. 
//If there is no next right node, the next pointer should be set to NULL.

//Initially, all next pointers are set to NULL.

//Example:
//https://assets.leetcode.com/uploads/2019/02/15/117_sample.png
//
//Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
//
//Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}
//
//Explanation: Given the above binary tree (Figure A), 
//your function should populate each next pointer to point to its next right node, just like in Figure B.

//Note:
//
//You may only use constant extra space.
//Recursive approach is fine, implicit stack space does not count as extra space for this problem.




/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node head) {
        if (head == null) {
            return head;
        }
        Node root = head;
        while (root != null) {
            Node dummyNode = new Node(0);
            Node curr = dummyNode;
            while (root != null) {
                if (root.left != null) {
                    curr.next = root.left;
                    curr = curr.next;
                }
                if (root.right != null) {
                    curr.next = root.right;
                    curr = curr.next;
                }
                root = root.next;
            }    
            root = dummyNode.next;
            dummyNode.next = null;
        }
        return head;
    }
}



