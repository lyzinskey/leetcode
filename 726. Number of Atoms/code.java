//Given a chemical formula (given as a string), return the count of each atom.

//An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

//1 or more digits representing the count of that element may follow if the count is greater than 1. 
//If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

//Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

//A formula placed in parentheses, and a count (optionally added) is also a formula. 
//For example, (H2O2) and (H2O2)3 are formulas.

//Given a formula, output the count of all elements as a string in the following form: 
//the first name (in sorted order), followed by its count (if that count is more than 1), 
//followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

//Example 1:
//Input: 
//formula = "H2O"
//Output: "H2O"
//Explanation: 
//The count of elements are {'H': 2, 'O': 1}.

//Example 2:
//Input: 
//formula = "Mg(OH)2"
//Output: "H2MgO2"
//Explanation: 
//The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.

//Example 3:
//Input: 
//formula = "K4(ON(SO3)2)2"
//Output: "K4N2O14S4"
//Explanation: 
//The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.

//Note:
//
//All atom names consist of lowercase letters, except for the first character which is uppercase.
//The length of formula will be in the range [1, 1000].
//formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.





class Solution {
    // dfs
    // Time: O(n)
    // Space: O(n)    
    private int i;
    public String countOfAtoms(String formula) {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> treemap = dfs(formula.toCharArray());
        for (String str : treemap.keySet()) {
            sb.append(str);
            int count = treemap.get(str);
            if (count > 1) {
                sb.append(treemap.get(str));
            }
        }
        return sb.toString();
    }

    private Map<String, Integer> dfs(char[] array) {
        Map<String, Integer> treemap = new TreeMap<>();
        while (i < array.length) {
            if (array[i] == '(') {
                i++;
                Map<String, Integer> temp = dfs(array);
                int count = getCount(array);
                for (String str : temp.keySet()) {
                    treemap.put(str, treemap.getOrDefault(str, 0) + temp.get(str) * count);
                }
            } else if (array[i] == ')') {
                i++;
                return treemap;
            } else {
                String name = getName(array);
                treemap.put(name, treemap.getOrDefault(name, 0) + getCount(array));
            }
        }
        return treemap;
    }

    public String getName(char[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append(array[i++]);
        while (i < array.length && array[i] >= 'a' && array[i] <= 'z') {
            sb.append(array[i++]);
        }
        return sb.toString();
    }

    public int getCount(char[] array) {
        int count = 0;
        while (i < array.length && array[i] >= '0' && array[i] <= '9') {
            count = count * 10 + (array[i] - '0');
            i++;
        }
        return count == 0 ? 1 : count;
    }
}




