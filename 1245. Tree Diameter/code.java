//Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

//The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  
//Each node has labels in the set {0, 1, ..., edges.length}.

//Example 1:
//
//Input: edges = [[0,1],[0,2]]
//Output: 2
//Explanation: 
//A longest path of the tree is the path 1 - 0 - 2.

//Example 2:
//
//Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
//Output: 4
//Explanation: 
//A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.

//Constraints:
//
//0 <= edges.length < 10^4
//edges[i][0] != edges[i][1]
//0 <= edges[i][j] <= edges.length
//The given edges form an undirected tree.




class Solution {
    // two bfs
    // start BFS from any node and find a node with the longest distance from the start node, 
    // it must be an end point of the longest path.
    //
    // Time: O(V+E)
    // Space: O(V+E)
    public int treeDiameter(int[][] edges) {
        int size = edges.length + 1;

        LinkedList<Integer>[] adjacencyList = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

        for (int[] edge : edges) {
            adjacencyList[edge[0]].add(edge[1]);
            adjacencyList[edge[1]].add(edge[0]);
        }

        Pair<Integer, Integer> start = bfs(0, size, adjacencyList);
        return (int) bfs(start.getKey(), size, adjacencyList).getValue();
    }

    private Pair bfs(int start, int size, LinkedList<Integer>[] adjacencyList) {
        int[] distance = new int[size];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        distance[start] = 0;
        int maxDistance = 0;
        int val = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < adjacencyList[node].size(); i++) {
                int neighbor = adjacencyList[node].get(i);
                
                if (distance[neighbor] == 0 && neighbor != start) {
                    queue.add(neighbor);
                    distance[neighbor] = distance[node] + 1;
                    
                    if (distance[neighbor] > maxDistance) {
                        maxDistance = distance[neighbor];
                        val = neighbor;
                    }
                }
            }
        }
        return new Pair<>(val, maxDistance);
    }
}



