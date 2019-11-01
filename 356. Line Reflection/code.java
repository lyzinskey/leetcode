//Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

//Example 1:
//
//Input: [[1,1],[-1,1]]
//Output: true

//Example 2:
//
//Input: [[1,1],[-1,-1]]
//Output: false

//Follow up:
//Could you do better than O(n2) ?




class Solution {
    // Find the smallest and largest x-value for all points.
    // If there is a line then it should be at y = (minX + maxX) / 2.
    // For each point, make sure that it has a reflected point in the opposite side.
    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) {
            return true;
        }
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<String> hashset = new HashSet<>();
        
        for (int[] point : points) {
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
            hashset.add(point[0] + " " + point[1]);
        }
                        
        for (int[] point : points) {            
            int reflect = min + max - point[0];
            if (!hashset.contains(reflect + " " + point[1])) {
                return false;
            }
        }
        return true;
    }
}



