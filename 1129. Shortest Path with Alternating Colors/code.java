//Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  
//In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.

//Each [i, j] in red_edges denotes a red directed edge from node i to node j.  
//Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.

//Return an array answer of length n, 
//where each answer[X] is the length of the shortest path from node 0 to node X 
//such that the edge colors alternate along the path (or -1 if such a path doesn't exist).

//Example 1:
//
//Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
//Output: [0,1,-1]

//Example 2:
//
//Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
//Output: [0,1,-1]

//Example 3:
//
//Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
//Output: [0,-1,-1]

//Example 4:
//
//Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
//Output: [0,1,2]

//Example 5:
//
//Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
//Output: [0,1,1]

//Constraints:
//
//1 <= n <= 100
//red_edges.length <= 400
//blue_edges.length <= 400
//red_edges[i].length == blue_edges[i].length == 2
//0 <= red_edges[i][j], blue_edges[i][j] < n




class Solution {
    // bfs
    // state: {node, color}
    // swap color for each step
    // Time: O(V + E)
    // Space: O(V + E)
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, Set<Integer>>[] hashmap = new HashMap[2];
        hashmap[0] = new HashMap<>();
        hashmap[1] = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hashmap[0].put(i, new HashSet<>());
            hashmap[1].put(i, new HashSet<>());
        }
        for (int[] edge : red_edges) {            
            hashmap[0].get(edge[0]).add(edge[1]);
        }
        for (int[] edge : blue_edges) {            
            hashmap[1].get(edge[0]).add(edge[1]);
        }
        
        int[][] steps = new int[2][n]; 
        for (int i = 1; i < n; i++) {
            steps[0][i] = n * 2;
            steps[1][i] = n * 2;
        }        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        queue.offer(new int[] {0, 1});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0];
            int color = cur[1];
            int nextColor = 1 - color;            
            for (int next : hashmap[nextColor].get(node)) {
                if (steps[nextColor][next] == n * 2) {
                    steps[nextColor][next] = steps[color][node] + 1;
                    queue.offer(new int[] {next, nextColor});
                }
            }            
        }
        
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int step = Math.min(steps[0][i], steps[1][i]);
            res[i] = step == n * 2 ? -1 : step;
        }
        return res;
    }
}




