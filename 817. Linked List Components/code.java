//We are given head, the head node of a linked list containing unique integer values.

//We are also given the list G, a subset of the values in the linked list.

//Return the number of connected components in G, 
//where two values are connected if they appear consecutively in the linked list.

//Example 1:
//
//Input: 
//head: 0->1->2->3
//G = [0, 1, 3]
//Output: 2
//Explanation: 
//0 and 1 are connected, so [0, 1] and [3] are the two connected components.

//Example 2:
//
//Input: 
//head: 0->1->2->3->4
//G = [0, 3, 1, 4]
//Output: 2
//Explanation: 
//0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.

//Note:
//
//If N is the length of the linked list given by head, 1 <= N <= 10000.
//The value of each node in the linked list will be in the range [0, N - 1].
//1 <= G.length <= 10000.
//G is a subset of all values in the linked list.





class Solution {
    // build graph + dfs
    // Time: O(n)
    // Space: O(n)
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> hashset = new HashSet<>();
        for (int node : G) {
            hashset.add(node);
        }
        Map<Integer, List<Integer>> hashmap = new HashMap<>();
        ListNode prev = head;
        ListNode next = prev.next;
        while (next != null) {
            if (!hashmap.containsKey(prev.val)) {
                hashmap.put(prev.val, new ArrayList<>());
            }
            if (!hashmap.containsKey(next.val)) {
                hashmap.put(next.val, new ArrayList<>());
            }
            if (hashset.contains(prev.val) && hashset.contains(next.val)) {
                hashmap.get(prev.val).add(next.val);
                hashmap.get(next.val).add(prev.val);
            }
            prev = next;
            next = next.next;
        }
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        for (int node : G) {
            if (visited.contains(node)) {
                continue;
            }
            res++;
            dfs(hashmap, visited, node);
        }
        return res;
    }
    
    private void dfs(Map<Integer, List<Integer>> hashmap, Set<Integer> visited, int node) {
        if (!hashmap.containsKey(node) || visited.contains(node)) {
            return;
        }
        visited.add(node);
        for (int next : hashmap.get(node)) {
            dfs(hashmap, visited, next);
        }        
    }
}




class Solution {
    // property of list
    // every element in a connected component has at most 2 edges
    // change node val to 1 if in G, else change node val to 0
    // only need to count how many connected 1s
    // 
    // Time: O(n)
    // Space: O(n)
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> hashset = new HashSet<>();
        for (int node : G) {
            hashset.add(node);
        }
        int res = 0;
        while (head != null) {
            if (hashset.contains(head.val)) {
                res++;
                while (head != null && hashset.contains(head.val)) {
                    head.val = 1;
                    head = head.next;
                }                
            } else {
                head.val = 0;
            }         
            if (head != null) {
                head = head.next;
            }            
        }
        return res;
    }
}





