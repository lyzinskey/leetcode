//Serialization is the process of converting a data structure or object into a sequence of bits 
//so that it can be stored in a file or memory buffer, 
//or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

//Design an algorithm to serialize and deserialize a binary tree. 
//There is no restriction on how your serialization/deserialization algorithm should work. 
//You just need to ensure that a binary tree can be serialized to a string 
//and this string can be deserialized to the original tree structure.

//Example: 
//
//You may serialize the following tree:
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//as "[1,2,3,null,null,4,5]"
//Clarification: The above format is the same as how LeetCode serializes a binary tree. 
//You do not necessarily need to follow this format, 
//so please be creative and come up with different approaches yourself.

//Note: Do not use class member/global/static variables to store states. 
//Your serialize and deserialize algorithms should be stateless.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "{}";
        }

        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(root);

        //注意这里queue的size在每次循环之后都会变化
        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (node == null) {
                continue;
            }
            queue.add(node.left);
            queue.add(node.right);
        }

        //删除最后一层的所有为null的叶子节点
        while (queue.get(queue.size() - 1) == null) {
            queue.remove(queue.size() - 1);
        }

        //String每次修改都会新建一个String
        //StringBuilder的好处在于可以对单一对象进行修改而不是每次都新建对象
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(queue.get(0).val);
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i) == null) {
                sb.append(",#");
            } else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("{}")) {
            return null;
        }
        
        //去掉一头一尾的“{”，“}”, 同时按照“，”将String进行split
        String[] vals = data.substring(1, data.length() - 1).split(",");
        
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.add(root);
        
        int index = 0;
        boolean isLeftChild = true;
        
        //根据isLeftChild判断是左子树还是右子树
        //每次循环结束时isLeftChild取反
        for (int i = 1; i < vals.length; i++) {
            if (!vals[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                if (isLeftChild) {
                    queue.get(index).left = node;
                } else {
                    queue.get(index).right = node;
                }
                queue.add(node);
            }
            
            //当前节点的两个子树都已处理完毕，移动到下一节点
            if (!isLeftChild) {
                index++;
            }
            
            isLeftChild = !isLeftChild;
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));



