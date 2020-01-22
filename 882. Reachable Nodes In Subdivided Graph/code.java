//Starting with an undirected graph (the "original graph") with nodes from 0 to N-1, 
//subdivisions are made to some of the edges.

//The graph is given as follows: 
//edges[k] is a list of integer pairs (i, j, n) such that (i, j) is an edge of the original graph,
//and n is the total number of new nodes on that edge. 

//Then, the edge (i, j) is deleted from the original graph, 
//n new nodes (x_1, x_2, ..., x_n) are added to the original graph,
//and n+1 new edges (i, x_1), (x_1, x_2), (x_2, x_3), ..., (x_{n-1}, x_n), (x_n, j) are added to the original graph.

//Now, you start at node 0 from the original graph, and in each move, you travel along one edge. 

//Return how many nodes you can reach in at most M moves.

//Example 1:
//
//Input: edges = [[0,1,10],[0,2,1],[1,2,2]], M = 6, N = 3
//Output: 13
//Explanation: 
//The nodes that are reachable in the final graph after M = 6 moves are indicated below.

//Example 2:
//
//Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], M = 10, N = 4
//Output: 23

//Note:
//
//0 <= edges.length <= 10000
//0 <= edges[i][0] < edges[i][1] < N
//There does not exist any i != j for which edges[i][0] == edges[j][0] and edges[i][1] == edges[j][1].
//The original graph has no parallel edges.
//0 <= edges[i][2] <= 10000
//0 <= M <= 10^9
//1 <= N <= 3000
//A reachable node is a node that can be travelled to using at most M moves starting from node 0.





class Solution {
    // Dijkstra
    // Time: O(ElogE), which is O(N^2logN^2) in the worst case
    // Space: O(N^2)
    public int reachableNodes(int[][] edges, int M, int N) {
        //build adjacent matrix
        int[][] adjM = new int[N][N];
        for (int i = 0; i < N; i++){
            Arrays.fill(adjM[i], -1); 
            //seperate the conditions: edge with 0 cost and not connected
        }
        for (int[] edge: edges){
            adjM[edge[0]][edge[1]] = edge[2];   //adjM is the number of new nodes between two old nodes
            adjM[edge[1]][edge[0]] = edge[2];
        }
        int result = 0;
        boolean[] visited = new boolean[N];
        //max heap for remain moves
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });  
        pq.offer(new int[]{0, M});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int curNode = cur[0];
            int moves = cur[1];
            //check old nodes
            if (visited[curNode]) continue;
            visited[curNode] = true;
            result++;
            //check new nodes
            for (int i = 0; i < N; i++){    //i is the next node
                int cost = adjM[curNode][i] + 1;   //cost = new nodes + 1
                //cost = 0 means node and i doesn't connected, the valid cost is at least 1
                if (cost > 0){    
                    if (moves >= cost && !visited[i]){
                        pq.offer(new int[]{i, moves - cost});
                    }
                    //reach only record the new nodes we travelled
                    int reach = Math.min(moves, cost - 1);   //cost will only reach cost - 1 new nodes
                    //old node will be counted at the start of new round;
                    result += reach;
                    adjM[i][curNode] -= reach;     
                    //update the remain new nodes from i to curNode
                    //this is important since the new nodes could be visted from both side
                }
            }
        }        
        return result;        
    }    
}





