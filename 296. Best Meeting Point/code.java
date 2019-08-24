//A group of two or more people wants to meet and minimize the total travel distance. 
//You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
//The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

//Example:
//
//Input: 
//
//  1 - 0 - 0 - 0 - 1
//  |   |   |   |   |
//  0 - 0 - 0 - 0 - 0
//  |   |   |   |   |
//  0 - 0 - 1 - 0 - 0

//Output: 6 
//
//Explanation: Given three people living at (0,0), (0,4), and (2,2):
//             The point (0,2) is an ideal meeting point, as the total travel distance 
//             of 2+2+2=6 is minimal. So return 6.




class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        return get_distance(row) + get_distance(col);
    }

    private static int get_distance(List<Integer> list) {
        int result = 0;

        Collections.sort(list);

        int i = 0;
        int j = list.size() - 1;
        while(i < j){
            result += list.get(j--) - list.get(i++);
        }

        return result;
    }
}



