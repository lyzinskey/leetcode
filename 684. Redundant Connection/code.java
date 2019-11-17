//In this problem, a tree is an undirected graph that is connected and has no cycles.

//The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), 
//with one additional edge added. 
//The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

//The resulting graph is given as a 2D-array of edges. 
//Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

//Return an edge that can be removed so that the resulting graph is a tree of N nodes. 
//If there are multiple answers, return the answer that occurs last in the given 2D-array. 
//The answer edge [u, v] should be in the same format, with u < v.

//Example 1:
//Input: [[1,2], [1,3], [2,3]]
//Output: [2,3]
//Explanation: The given undirected graph will be like this:
//  1
// / \
//2 - 3

//Example 2:
//Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
//Output: [1,4]
//Explanation: The given undirected graph will be like this:
//5 - 1 - 2
//    |   |
//    4 - 3

//Note:
//The size of the input 2D-array will be between 3 and 1000.
//Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

//Update (2017-09-26):
//We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. 
//For the directed graph follow up please see Redundant Connection II). 
//We apologize for any inconvenience caused.



// UnionFind
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[] {};
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        private UnionFind(int size) {
            this.parent = new int[size + 1];
            for (int i = 0; i <= size; i++) {
                parent[i] = i;
            }
            this.rank = new int[size + 1];
        }

        private int find(int node) {
            if (node != parent[node]) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        private boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;

            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }
}



// BFS
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> hashmap = new HashMap<>();
        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            if (!hashmap.containsKey(i)) {
                hashmap.put(i, new HashSet<>());
            }
            if (!hashmap.containsKey(j)) {
                hashmap.put(j, new HashSet<>());
            }
            hashmap.get(i).add(j);
            hashmap.get(j).add(i);       
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int num : hashmap.keySet()) {
            if (hashmap.get(num).size() <= 1) {
                queue.add(num);
            }
        }
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            Set<Integer> hashset = hashmap.get(node);
            for (int num : hashset) {
                hashmap.get(num).remove(node);
                if (hashmap.get(num).size() <= 1) {
                    queue.offer(num);
                }
            }            
        }
        
        for (int i = edges.length - 1; i >= 0; i--) {
            int[] edge = edges[i];
            if (hashmap.get(edge[0]).size() > 1 && hashmap.get(edge[1]).size() > 1) {
                return edge;
            }
        }
        return edges[edges.length - 1];
    }
}



