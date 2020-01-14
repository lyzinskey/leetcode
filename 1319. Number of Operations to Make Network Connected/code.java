//There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network 
//where connections[i] = [a, b] represents a connection between computers a and b. 
//Any computer can reach any other computer directly or indirectly through the network.

//Given an initial computer network connections. 
//You can extract certain cables between two directly connected computers, 
//and place them between any pair of disconnected computers to make them directly connected. 
//Return the minimum number of times you need to do this in order to make all the computers connected. 
//If it's not possible, return -1. 

//Example 1:
//
//Input: n = 4, connections = [[0,1],[0,2],[1,2]]
//Output: 1
//Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.

//Example 2:
//
//Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
//Output: 2

//Example 3:
//
//Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
//Output: -1
//Explanation: There are not enough cables.

//Example 4:
//
//Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
//Output: 0

//Constraints:
//
//1 <= n <= 10^5
//1 <= connections.length <= min(n*(n-1)/2, 10^5)
//connections[i].length == 2
//0 <= connections[i][0], connections[i][1] < n
//connections[i][0] != connections[i][1]
//There are no repeated connections.
//No two computers are connected by more than one cable.





class Solution {
    // there must be a solution as long as there're no less than n-1 connections
    // this problem can be simplified to finding the number of connected components
    
    // dfs
    // Time: O(V + E)
    // Space: O(V + E)
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        
        boolean[] visited = new boolean[n];
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] connection : connections) {
            edges[connection[0]].add(connection[1]);
            edges[connection[1]].add(connection[0]);
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {                
                count++;
                dfs(visited, edges, i);                
            }
        }
        return count - 1;
    }
    
    private void dfs(boolean[] visited, List<Integer>[] edges, int cur) {
        if (visited[cur]) {
            return;
        }        
        List<Integer> edge = edges[cur];
        visited[cur] = true;
        for (int node : edge) {                   
            dfs(visited, edges, node);            
        }
    }
}
    
    

class Solution {    
    // union find
    // Time: O(V + E)
    // Space: O(V)
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] connection : connections) {
            int p0 = findParent(parent, connection[0]);
            int p1 = findParent(parent, connection[1]);
            if (p0 != p1) {
                parent[p0] = p1;
            }
        }

        int components = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                components++;
            }
        }
        return components - 1;
    }

    private int findParent(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = findParent(parent, parent[i]);
        }
        return parent[i];
    }
}



