//Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

//Note:
//
//Given target value is a floating point.
//You may assume k is always valid, that is: k ≤ total nodes.
//You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

//Example:
//
//Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
//
//      4
//     / \
//    2   5
//   / \
//  1   3
//
//Output: [4,3]

//Follow up:
//Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?




/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

class Solution {
    //暴力方法。时间复杂度 O(n)，空间复杂度也是 O(n)
    //先用 inorder traversal 求出中序遍历
    //找到第一个 >= target 的位置 index
    //从 index-1 和 index 出发，设置两根指针一左一右，获得最近的 k 个整数 
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> inorder = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        if (root == null || k <= 0){
            return inorder;
        }

        inorderTraversal(root, inorder);

        int pointer = 0;
        int size = inorder.size();
        while (pointer < size){
            if (inorder.get(pointer) > target){
                break;
            }
            else {
                pointer++;
            }
        }

        if (pointer >= size){
            return inorder.subList(size - k, size);
        }

        int left = pointer - 1;
        int right = pointer;

        for (int i = 0; i < k; i++) {
            if (right >= size){
                result.add(inorder.get(left));
                left--;
            }
            if (left < 0){
                result.add(inorder.get(right));
                right++;
            }
            if (left >= 0 && right < size){
                if (target - inorder.get(left) < inorder.get(right) - target){
                    result.add(inorder.get(left));
                    left--;
                }
                else {
                    result.add(inorder.get(right));
                    right++;
                }
            }
        }

        return result;
    }

    private void inorderTraversal(TreeNode root, List<Integer> inorder){
        if (root == null){
            return;
        }
        
        inorderTraversal(root.left, inorder);
        inorder.add(root.val);
        inorderTraversal(root.right, inorder);
    }
    
    
    //暴力方法2：中序遍历。时间复杂度 O(n)，空间复杂度也是 O(n)
    //队没满遇到一个node就塞进去；满了就把距离远的删了，距离近的塞进去。
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ret = new LinkedList<Integer>();
        
        dfs(root, target, k, ret);
        return ret;
    }
    
    private void dfs(TreeNode root, double target, int k, List<Integer> ret) {
        if (root == null) {
            return;
        }
        
        dfs(root.left, target, k, ret);
        
        if (ret.size() < k) {
            ret.add(root.val);
        } else if (Math.abs(root.val - target) < Math.abs(ret.get(0) - target)) {
            ret.remove(0);
            ret.add(root.val);
        }
        
        dfs(root.right, target, k, ret);
    }
    
}



    // 最优算法，时间复杂度 O(k + logn)，空间复杂度 O(logn)
    // 实现如下的子函数：
    // getStack() => 在假装插入 target 的时候，看看一路走过的节点都是哪些，放到 stack 里，用于 iterate
    // moveUpper(stack) => 根据 stack，挪动到 next node
    // moveLower(stack) => 根据 stack, 挪动到 prev node
    //有了这些函数之后，就可以把整个树当作一个数组一样来处理，
    //只不过每次 i++ 的时候要用 moveUpper，i--的时候要用 moveLower 
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> values = new ArrayList<>();
        
        if (k == 0 || root == null) {
            return values;
        }
        
        Stack<TreeNode> lowerStack = getStack(root, target);
        Stack<TreeNode> upperStack = new Stack<>();
        upperStack.addAll(lowerStack);
        if (target < lowerStack.peek().val) {
            moveLower(lowerStack);
        } else {
            moveUpper(upperStack);
        }
        
        for (int i = 0; i < k; i++) {
            if (lowerStack.isEmpty() ||
                   !upperStack.isEmpty() && target - lowerStack.peek().val > upperStack.peek().val - target) {
                values.add(upperStack.peek().val);
                moveUpper(upperStack);
            } else {
                values.add(lowerStack.peek().val);
                moveLower(lowerStack);
            }
        }

        return values;
    }
    
    private Stack<TreeNode> getStack(TreeNode root, double target) {
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            
            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        return stack;
    }
    
    public void moveUpper(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.right == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
            return;
        }
        
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    public void moveLower(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.left == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().left == node) {
                node = stack.pop();
            }
            return;
        }
        
        node = node.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }
    
    
