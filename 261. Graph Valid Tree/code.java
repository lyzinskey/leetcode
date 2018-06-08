//Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), 
//write a function to check whether these edges make up a valid tree.

//Example 1:
//
//Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
//Output: true

//Example 2:
//
//Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
//Output: false

//Note: you can assume that no duplicate edges will appear in edges. 
//Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.


    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1){
            return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hashSet = new HashSet<>();
        Map<Integer, ArrayList<Integer>> map = mapInitial(n, edges);
        int nodeCounter = 1;
        
        queue.offer(0);
        hashSet.add(0);
        
        while(!queue.isEmpty()){
            for (int i = 0; i < queue.size(); i++) {
                int node = queue.poll();
                for (int neighbor : map.get(node)){
                    if (!hashSet.contains(neighbor)){
                        queue.offer(neighbor);
                        hashSet.add(neighbor);
                        nodeCounter++;
                    }
                }
            }
        }
        return nodeCounter == n;
    }
    
    public Map<Integer, ArrayList<Integer>> mapInitial(int n, int[][] edges){
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        
        return map;
    }
    
    
