//Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

//Calling next() will return the next smallest number in the BST.

//Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.



/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */



//这是一个非常通用的利用 stack 进行 Binary Tree Iterator 的写法。

//stack 中保存一路走到当前节点的所有节点，stack.peek() 一直指向 iterator 指向的当前节点。
//因此判断有没有下一个，只需要判断 stack 是否为空
//获得下一个值，只需要返回 stack.peek() 的值，并将 stack 进行相应的变化，挪到下一个点。

//挪到下一个点的算法如下：
//
//如果当前点存在右子树，那么就是右子树中“一路向西”最左边的那个点
//如果当前点不存在右子树，则是走到当前点的路径中，第一个左拐的点


public class BSTIterator {

    private Stack<TreeNode> stack = new Stack<>();
    private TreeNode curr;
    
    //对stack进行初始化，一路push节点进stack直到整个树的最小节点（即最左侧的节点）
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        curr = stack.peek();
        TreeNode node = stack.peek();
        
        if (node.right == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
        }
        else {
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        
        return curr.val;        
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
 
