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
    // 时间复杂度分析：Stack的push和pop时间复杂度均为O(1)，因此getStack需要O(logn)时间；
    //              再加上往values中add总计k次，总时间复杂度为O(k + logn)
    // 空间复杂度分析：题目已经说明输入BST是balanced，
    //              upperStack和lowerStack在树的每一层添加一个点，因此空间复杂度为O(logn)
    
    // 实现如下的子函数：
    // getStack() => 在假装插入 target 的时候，看看一路走过的节点都是哪些，放到 stack 里，用于 iterate
    // moveUpper(stack) => 根据 stack，挪动到 next node
    // moveLower(stack) => 根据 stack, 挪动到 prev node
    // 有了这些函数之后，就可以把整个树当作一个数组一样来处理，
    // 只不过每次 i++ 的时候要用 moveUpper，i--的时候要用 moveLower 
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> values = new ArrayList<>();
        
        if (k == 0 || root == null) {
            return values;
        }
        
        //lowerStack用来记录所有比target小的节点
        Stack<TreeNode> lowerStack = getStack(root, target);
        //upperStack用来记录所有比target大的节点
        Stack<TreeNode> upperStack = new Stack<>();
        
        //addAll按照元素在lowerStack中的顺序完全复制到upperStack中
        //addAll是deep copy
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
    
    //朝着node增大的方向移动1
    public void moveUpper(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.right == null) {
            node = stack.pop();
            //如果一路全是右子树的话，需要移动到第一个朝右边移动的节点
            //这样才能保证只朝增大的方向移动1
            while (!stack.isEmpty() && stack.peek().right == node) {
                node = stack.pop();
            }
            return;
        }
        
        //右子树的所有数字都比当前根节点大
        //因此大于当前根节点的最小值（upperbound）是其右子树最左边的节点
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    //朝着node减小的方向移动1
    public void moveLower(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.left == null) {
            node = stack.pop();
            //如果一路全是左子树的话，需要移动到第一个朝左边移动的节点
            //这样才能保证只朝增大的方向移动1
            while (!stack.isEmpty() && stack.peek().left == node) {
                node = stack.pop();
            }
            return;
        }
        
        //左子树的所有数字都比当前根节点小
        //因此小于当前根节点的最大值（lowerbound）是其左子树最右边的节点
        node = node.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }
    
    
