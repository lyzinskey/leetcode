//Equations are given in the format A / B = k, where A and B are variables represented as strings, 
//and k is a real number (floating point number). Given some queries, return the answers. 
//If the answer does not exist, return -1.0.

//Example:
//Given a / b = 2.0, b / c = 3.0.
//queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
//return [6.0, 0.5, -1.0, 1.0, -1.0 ].

//The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , 
//where equations.size() == values.size(), and the values are positive. 
//This represents the equations. Return vector<double>.

//According to the example above:
//
//equations = [ ["a", "b"], ["b", "c"] ],
//values = [2.0, 3.0],
//queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 
//The input is always valid. 
//You may assume that evaluating the queries will result in no division by zero and there is no contradiction.




class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> hashmap = new HashMap<>();
        buildMap(equations, values, queries, hashmap);
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String first = queries.get(i).get(0);
            String second = queries.get(i).get(1);
            
            if (!hashmap.containsKey(first) || !hashmap.containsKey(second)) {
                result[i] = -1.0;
            } else {
                result[i] = dfs(hashmap, first, second, 1.0, new HashSet<String>());
            }
        }
        return result;
    }
    
    private void buildMap(List<List<String>> equations, double[] values, List<List<String>> queries, Map<String, Map<String, Double>> hashmap) {        
        for (int i = 0; i < equations.size(); i++) {
            String first = equations.get(i).get(0);
            String second = equations.get(i).get(1);
            if (!hashmap.containsKey(first)) {
                hashmap.put(first, new HashMap<>());
            }
            if (!hashmap.containsKey(second)) {
                hashmap.put(second, new HashMap<>());
            }
            hashmap.get(first).put(second, values[i]);
            hashmap.get(second).put(first, 1 / values[i]);
        }        
    }
    
    private double dfs(Map<String, Map<String, Double>> hashmap, String first, String second, double num, Set<String> hashset) {
        if (hashset.contains(first)) {
            return -1.0;
        }
        if (first.equals(second)) {
            return 1.0;
        }
        
        Map<String, Double> next = hashmap.get(first);
        hashset.add(first);
        for (String str : next.keySet()) {
            double result = dfs(hashmap, str, second, num, hashset);
            if (result > 0) {
                return result * next.get(str);
            }
        }
        return -1.0;
    }
}



