//Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
//Each person may dislike some other people, and they should not go into the same group. 
//Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
//Return true if and only if it is possible to split everyone into two groups in this way.

//Example 1:
//
//Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
//Output: true
//Explanation: group1 [1,4], group2 [2,3]

//Example 2:
//
//Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
//Output: false

//Example 3:
//
//Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//Output: false
 
//Note:
//
//1 <= N <= 2000
//0 <= dislikes.length <= 10000
//1 <= dislikes[i][j] <= N
//dislikes[i][0] < dislikes[i][1]
//There does not exist i != j for which dislikes[i] == dislikes[j].





class Solution {
    // dfs
    // Time: O(V + E)
    // Space: O(V + E)
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> hashmap = new HashMap<>();
        int[] colors = new int[N + 1];
        for (int[] dislike : dislikes) {
            int from = dislike[0];
            int to = dislike[1];
            if (!hashmap.containsKey(from)) {
                hashmap.put(from, new ArrayList<>());                
            }
            if (!hashmap.containsKey(to)) {
                hashmap.put(to, new ArrayList<>());                
            }
            hashmap.get(from).add(to);
            hashmap.get(to).add(from);
        }
        
        for (int i = 1; i <= N; i++) {
            if (colors[i] == 0 && !dfs(hashmap, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean dfs(Map<Integer, List<Integer>> hashmap, int[] colors, int node, int color) {
        if (!hashmap.containsKey(node)) {
            return true;
        }
        colors[node] = color;
        for (int neighbor : hashmap.get(node)) {
            if (colors[neighbor] == color) {
                return false;
            }
            if (colors[neighbor] == 0 && !dfs(hashmap, colors, neighbor, -color)) {
                return false;
            }
        }
        return true;
    }
}



