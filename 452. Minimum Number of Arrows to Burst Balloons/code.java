//There are a number of spherical balloons spread in two-dimensional space. 
//For each balloon, provided input is the start and end coordinates of the horizontal diameter. 
//Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. 
//Start is always smaller than end. There will be at most 104 balloons.

//An arrow can be shot up exactly vertically from different points along the x-axis. 
//A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. 
//There is no limit to the number of arrows that can be shot. 
//An arrow once shot keeps travelling up infinitely. 
//The problem is to find the minimum number of arrows that must be shot to burst all balloons.

//Example:
//
//Input:
//[[10,16], [2,8], [1,6], [7,12]]
//
//Output:
//2
//
//Explanation:
//One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) 
//and another arrow at x = 11 (bursting the other two balloons).




class Solution {
    // Time: O(nlogn)
    // Space: O(1)
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        // sort by x_end
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int arrows = 1;
        int end = points[0][1];
        for (int[] p : points) {
            // if the current balloon starts after the end of another one,
            // one needs one more arrow
            if (end < p[0]) {
                arrows++;
                end = p[1];
            }
        }
        return arrows;
    }
}



