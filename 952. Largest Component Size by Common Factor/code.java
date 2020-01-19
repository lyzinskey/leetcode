//Given a non-empty array of unique positive integers A, consider the following graph:

//There are A.length nodes, labelled A[0] to A[A.length - 1];
//There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
//Return the size of the largest connected component in the graph.

//Example 1:
//
//Input: [4,6,15,35]
//Output: 4

//Example 2:
//
//Input: [20,50,9,63]
//Output: 2

//Example 3:
//
//Input: [2,3,6,7,4,12,21,39]
//Output: 8

//Note:
//
//1 <= A.length <= 20000
//1 <= A[i] <= 100000





class Solution {
    // union find
    // Time: O(n * sqrt(Max val of A[i]))
    // Space: O(Max val of A[i])
    public int largestComponentSize(int[] A) {
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            max = Math.max(max, A[i]);
        }
        UnionFind uf = new UnionFind(max);
        for (int num : A) {
            int sqrt = (int) Math.sqrt(num);
            for (int i = 2; i <= sqrt; i++) {
                if (num % i == 0) {
                    uf.union(num, i);
                    uf.union(num, num / i);
                }
            }
        }
        int res = 1;
        Map<Integer, Integer> hashmap = new HashMap<>();
        for (int a : A) {
            int parent = uf.find(a);
            if (!hashmap.containsKey(parent)) {
                hashmap.put(parent, 0);
            }
            int count = hashmap.get(parent) + 1;
            hashmap.put(parent, count);
            res = Math.max(res, count);
        }
        return res;
    }

    class UnionFind {
        int[] parent;

        UnionFind(int n) {
            this.parent = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = parent[find(y)];
        }
    }
}




