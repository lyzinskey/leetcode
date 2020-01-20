//An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
//graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
//Return the length of the shortest path that visits every node. 
//You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

//Example 1:
//
//Input: [[1,2,3],[0],[0],[0]]
//Output: 4
//Explanation: One possible path is [1,0,2,0,3]

//Example 2:
//
//Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
//Output: 4
//Explanation: One possible path is [0,1,4,2,3]

//Note:
//
//1 <= graph.length <= 12
//0 <= graph[i].length < graph.length






class Solution {
    // Time: O(n * 2^n)
    // Space: O(n * 2^n)
    public int shortestPathLength(int[][] graph) {
        int len = graph.length;
        int finalState = (1 << len) - 1;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[len][finalState + 1];
        
        for (int i = 0; i < len; i++) {
            queue.offer(new int[] {i, 1 << i});
        }
        
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pair = queue.poll();
                int node = pair[0];
                int state = pair[1];
                if (state == finalState) {
                    return step;
                }
                if (visited[node][state]) {
                    continue;
                }
                visited[node][state] = true;
                for (int next : graph[node]) {
                    queue.offer(new int[] {next, state | (1 << next)});
                }
            }
            step++;
        }
        return -1;
    }
}




