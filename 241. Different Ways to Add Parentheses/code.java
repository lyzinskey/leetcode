//Given a string of numbers and operators, 
//return all possible results from computing all the different possible ways to group numbers and operators. 
//The valid operators are +, - and *.

//Example 1:
//
//Input: "2-1-1"
//Output: [0, 2]
//Explanation: 
//((2-1)-1) = 0 
//(2-(1-1)) = 2

//Example 2:
//
//Input: "2*3-4*5"
//Output: [-34, -14, -10, -10, 10]
//Explanation: 
//(2*(3-(4*5))) = -34 
//((2*3)-(4*5)) = -14 
//((2*(3-4))*5) = -10 
//(2*((3-4)*5)) = -10 
//(((2*3)-4)*5) = 10





class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> memo = new HashMap<>();
        return ways(input, memo);
    }
    
    private List<Integer> ways(String input, Map<String, List<Integer>> memo) {
        if (memo.containsKey(input)) {
            return memo.get(input);
        }
        
        List<Integer> res = new ArrayList<>();        
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                String first = input.substring(0, i);
                String second = input.substring(i + 1);
                List<Integer> list1 = ways(first, memo);
                List<Integer> list2 = ways(second, memo);                
                
                for (int n1 : list1) {
                    for (int n2 : list2) {
                        int result = 0;
                        if (ch == '+') {
                            result += n1 + n2;
                        } else if (ch == '-') {
                            result += n1 - n2;
                        } else {
                            result += n1 * n2;
                        }          
                        res.add(result);                        
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        memo.put(input, res);
        return res;
    }
}




