//Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], 
//reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. 
//Thus, the itinerary must begin with JFK.

//Note:
//
//If there are multiple valid itineraries, 
//you should return the itinerary that has the smallest lexical order when read as a single string. 
//For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//All airports are represented by three capital letters (IATA code).
//You may assume all tickets form at least one valid itinerary.

//Example 1:
//
//Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

//Example 2:
//
//Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
//             But it is larger in lexical order.




class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> route = new LinkedList<>();
        Map<String, PriorityQueue<String>> hashmap = new HashMap<>();
        
        for (List<String> ticket : tickets) {
            String start = ticket.get(0);
            if (!hashmap.containsKey(start)) {
                hashmap.put(start, new PriorityQueue<String>());
            }
            hashmap.get(start).offer(ticket.get(1));
        }
        buildPath("JFK", route, hashmap);
        return route;
    }
    
    private void buildPath(String airport, List<String> route, Map<String, PriorityQueue<String>> hashmap) {
        while (hashmap.containsKey(airport) && !hashmap.get(airport).isEmpty()) {
            buildPath(hashmap.get(airport).poll(), route, hashmap);
        }
        route.add(0, airport);
    }
}



