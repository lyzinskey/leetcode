//There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network 
//where connections[i] = [a, b] represents a connection between servers a and b. 
//Any server can reach any other server directly or indirectly through the network.

//A critical connection is a connection that, if removed, will make some server unable to reach some other server.

//Return all critical connections in the network in any order.

//Example 1:
//https://assets.leetcode.com/uploads/2019/09/03/1537_ex1_2.png
//
//Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
//Output: [[1,3]]
//Explanation: [[3,1]] is also accepted.

//Constraints:
//
//1 <= n <= 10^5
//n-1 <= connections.length <= 10^5
//connections[i][0] != connections[i][1]
//There are no repeated connections.




class Solution {    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> conn : connections) {
            int n1 = conn.get(0);
            int n2 = conn.get(1);
            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        int[] discover = new int[n]; // discovery time of each node
        int[] lowest = new int[n]; // earliest discovered node reachable from this node in DFS
        boolean[] visited = new boolean[n]; // whether this node has been visited in DFS
        List<List<Integer>> result = new ArrayList<>();
        int[] time = new int[] {0};// current time of discovery

        dfs(0, -1, discover, lowest, visited, graph, result, time); // arbitrarily pick a node to start DFS

        return result;
    }

    // root = current node under consideration
    // parent = parent of current node
    private void dfs(int root, int parent, int[] discover, int[] lowest, boolean[] visited, List<Integer>[] graph, List<List<Integer>> result, int[] time) {
        visited[root] = true;
        discover[root] = time[0]++;
        lowest[root] = discover[root]; // we don't have to initialize lowest[] to inf because of this line

        for (Integer neighbor : graph[root]) {
            if (neighbor == parent) {
                continue;
            }

            if (!visited[neighbor]) {
                dfs(neighbor, root, discover, lowest, visited, graph, result, time);
                lowest[root] = Math.min(lowest[root], lowest[neighbor]);
                if (discover[root] < lowest[neighbor]) {
                    result.add(Arrays.asList(root, neighbor));
                }
            } else {
                lowest[root] = Math.min(lowest[root], discover[neighbor]);
            }
        }
    }
    
    
    // TLE, Java的Hashmap，Hashset是用红黑树实现的，创建时有initial capacity, 当load factor达到一定大小时，
    // Java内部会创建一个capacity更大的map/set, 然后把之前的东西全部复制过来，因此当数据规模较大时会拷贝多次而导致超时
//     public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
//         Set<String> edges = new HashSet<>();
//         List<Integer>[] graph = new List[n];
//         for (int i = 0; i < n; i++) {
//             graph[i] = new ArrayList<>();
//         }
//         for (List<Integer> connection : connections) {
//             int from = connection.get(0);
//             int to = connection.get(1);
//             edges.add(Math.min(from, to) + " " + Math.max(from, to));
//             graph[from].add(to);
//             graph[to].add(from);
//         }

//         int[] rank = new int[n];
//         Arrays.fill(rank, -2);
//         dfs(edges, graph, rank, 0, 0);
        
//         List<List<Integer>> result = new ArrayList<>();
//         for (String str : edges) {
//             String[] split = str.split(" ");
//             result.add(Arrays.asList(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
//         }
//         return result;
//     }

//     private int dfs(Set<String> edges, List<Integer>[] graph, int[] rank, int node, int depth) {
//         if (rank[node] >= 0) {
//             return rank[node];
//         }
//         rank[node] = depth;
//         int min_depth = depth;
//         for (int neighbor : graph[node]) {
//             if (rank[neighbor] == depth - 1) {
//                 continue;
//             }
//             int lastDepth = dfs(edges, graph, rank, neighbor, depth + 1);
//             min_depth = Math.min(min_depth, lastDepth);
//             if (lastDepth <= depth) {
//                 edges.remove((Math.min(neighbor, node) + " " + Math.max(neighbor, node)));
//             }
//         }
//         return min_depth;
//     }
}




