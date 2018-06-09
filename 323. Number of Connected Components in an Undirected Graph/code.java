//Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
//write a function to find the number of connected components in an undirected graph.

//Example 1:
//
//Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
//
//     0          3
//     |          |
//     1 --- 2    4 
//
//Output: 2

//Example 2:
//
//Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
//
//     0           4
//     |           |
//     1 --- 2 --- 3
//
//Output:  1

//Note:
//You can assume that no duplicate edges will appear in edges. 
//Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


    public int countComponents(int n, int[][] edges) {
        if (n < 2){
            return n;
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        int connected = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                connected++;
                visited[i] = true;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                
                while (!queue.isEmpty()){
                    int node = queue.poll();
                    for (int neighbor : map.get(node)){
                        if (!visited[neighbor]){
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }
        
        return connected;
    }
    
    
