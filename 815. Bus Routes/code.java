//We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. 
//For example if routes[0] = [1, 5, 7], 
//this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

//We start at bus stop S (initially not on a bus), and we want to go to bus stop T. 
//Travelling by buses only, what is the least number of buses we must take to reach our destination? 
//Return -1 if it is not possible.

//Example:
//Input: 
//routes = [[1, 2, 7], [3, 6, 7]]
//S = 1
//T = 6
//Output: 2
//Explanation: 
//The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

//Note:
//
//1 <= routes.length <= 500.
//1 <= routes[i].length <= 500.
//0 <= routes[i][j] < 10 ^ 6.




class Solution {
    // bfs
    // Time: O(mn)
    // Space: O(mn)
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        
        Map<Integer, List<Integer>> hashmap = new HashMap<>();
        boolean[] visited = new boolean[routes.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(S);
        int res = 0;

        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!hashmap.containsKey(routes[i][j])) {
                    hashmap.put(routes[i][j], new ArrayList<>());
                }
                hashmap.get(routes[i][j]).add(i);
            }
        }

        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int stop = queue.poll();
                List<Integer> buses = hashmap.get(stop);
                for (int bus : buses) {
                    if (visited[bus]) {
                        continue;
                    }
                    visited[bus] = true;
                    for (int next : routes[bus]) {
                        if (next == T) {
                            return res;
                        }
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }
}



