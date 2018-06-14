//Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
//Return the root node reference (possibly updated) of the BST.

//Basically, the deletion can be divided into two stages:
//
//Search for a node to remove.
//If the node is found, delete the node.
//Note: Time complexity should be O(height of tree).

//Example:
//
//root = [5,3,6,2,4,null,7]
//key = 3
//
//      5
//     / \
//    3   6
//   / \   \
//  2   4   7
//
//Given key to delete is 3. So we find the node with value 3 and delete it.
//
//One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
//
//      5
//     / \
//    4   6
//   /     \
//  2       7
//
//Another valid answer is [5,2,6,null,4,null,7].
//
//      5
//     / \
//    2   6
//     \   \
//      4   7



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

    //考虑待删除的节点为叶子节点，可以直接删除并修改父亲节点(Parent Node)的指针，需要区分待删节点是否为根节点
    //考虑待删除的节点为单支节点(只有一棵子树——左子树 or 右子树)，与删除链表节点操作类似，同样的需要区分待删节点是否为根节点
    //考虑待删节点有两棵子树，可以将待删节点与左子树中的最大节点进行交换，由于左子树中的最大节点一定为叶子节点，所以这时再删除待删的节点可以参考第一条
    //详细的解释可以看 http://www.algolist.net/Data_structures/Binary_search_tree/Removal
    
    public TreeNode deleteNode(TreeNode root, int value) {
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode parent = findNode(dummy, root, value);
        TreeNode node;
        
        //判断parent的左右儿子是否是待删除的节点
        //因为findNode函数不能保证返回正确节点，有可能待删除节点根本不存在        
        if (parent.left != null && parent.left.val == value) {
            node = parent.left;
        } else if (parent.right != null && parent.right.val == value) {
            node = parent.right;
        } 
        //待删除节点不存在时直接返回原BST        
        else {
            return dummy.left;
        }
        
        deleteNode(parent, node);
        return dummy.left;
    }
    
    //找到值与value相同对节点node并返回其父节点parent
    private TreeNode findNode(TreeNode parent, TreeNode node, int value) {
        if (node == null) {
            return parent;
        }
        if (node.val == value) {
            return parent;
        }
        if (value < node.val) {
            return findNode(node, node.left, value);
        } else {
            return findNode(node, node.right, value);
        }
    }

    private void deleteNode(TreeNode parent, TreeNode node) {
        //若待删除节点为叶节点，直接删除该点，无需其他任何操作
        if (node.left == null && node.right == null){
            if (parent.left == node){
                parent.left = null;
            }
            else {
                parent.right = null;
            }
        }

        //若待删除节点只有一个子树，父节点直接跳过删除节点连接其子节点
        if (node.left == null && node.right != null){
            if (parent.left == node){
                parent.left = node.right;
            }
            else {
                parent.right = node.right;
            }
        }

        if (node.left != null && node.right == null) {
            if (parent.left == node){
                parent.left = node.left;
            }
            else {
                parent.right = node.left;
            }
        }

        //若待删除节点左右子树均不为空，那么找到右子树的最小值，
        //将待删除节点的值赋为该最小值，同时删除最小值对应的节点
        if (node.left != null && node.right != null) {
            TreeNode minNode = node.right;
            TreeNode minNodeParent = node;
            while (minNode.left != null) {
                minNodeParent = minNode;
                minNode = minNode.left;
            }
            node.val = minNode.val;
            deleteNode(minNodeParent, minNode);            
        }
    }    
}

