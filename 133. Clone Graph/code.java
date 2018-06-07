//Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

//OJ's undirected graph serialization:
//Nodes are labeled uniquely.
//
//We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
//As an example, consider the serialized graph {0,1,2#1,2#2,2}.
//
//The graph has a total of three nodes, and therefore contains three parts as separated by #.
//
//First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
//Second node is labeled as 1. Connect node 1 to node 2.
//Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
//Visually, the graph looks like the following:
//
//       1
//      / \
//     /   \
//    0 --- 2
//         / \
//         \_/



/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    //    算法描述
    //    用3个步骤：
    //    从1个点找到所有点
    //    复制所有的点
    //    复制所有的边
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null){
            return null;
        }

        //用BFS找到图中的所有点，注意这里仅仅只是找到了所有点而已，并没有进行任何处理
        ArrayList<UndirectedGraphNode> allNodes = getNodes(node);

        //用map存储所有点，key是老图中的点，value是新点
        Map<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
        for (UndirectedGraphNode eachNode : allNodes){
            mapping.put(eachNode, new UndirectedGraphNode(eachNode.label));
        }

        //复制所有的边
        for (UndirectedGraphNode eachNode : allNodes){
            //对mapping使用get得到的是所有复制出来的新点
            UndirectedGraphNode newNode = mapping.get(eachNode);
            //遍历所有老点的neighbor
            for (UndirectedGraphNode eachNeighbor : eachNode.neighbors){
                //注意这里不能直接add(eachNeighbor)
                //因为eachNeighbor对应的是老点，要取出eachNeighbor在mapping中对应的新点
                newNode.neighbors.add(mapping.get(eachNeighbor));
            }
        }

        return mapping.get(node);
    }

    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node){
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> hashset = new HashSet<>();

        queue.offer(node);
        hashset.add(node);

        while (!queue.isEmpty()){
            UndirectedGraphNode head = queue.poll();
            for (UndirectedGraphNode neighbor : head.neighbors){
                if (!hashset.contains(neighbor)){
                    queue.offer(neighbor);
                    hashset.add(neighbor);
                }
            }
        }

        return new ArrayList<>(hashset);
    }
    
}

