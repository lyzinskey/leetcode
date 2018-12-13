//Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

//  Example 1:
//
//  Input: [[1,1],[2,2],[3,3]]
//  Output: 3
//  Explanation:
//  ^
//  |
//  |        o
//  |     o
//  |  o  
//  +------------->
//  0  1  2  3  4

//  Example 2:
//
//  Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//  Output: 4
//  Explanation:
//  ^
//  |
//  |  o
//  |     o        o
//  |        o
//  |  o        o
//  +------------------->
//  0  1  2  3  4  5  6




/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

import java.math.BigDecimal;
import java.math.MathContext;

class Solution {
    public int maxPoints(Point[] points) {
        int result = 0;

        for (int i = 0; i < points.length; i++) {
            Point seed = points[i];
            int same = 1;
            int sameX = 0;
            int most = 0;
            HashMap<BigDecimal, Integer> cnt = new HashMap<>();

            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                Point tmp = points[j];
                if (tmp.x == seed.x && tmp.y == seed.y) {
                    same++;
                } else if (tmp.x == seed.x) {
                    sameX++;
                } else {
                    BigDecimal slope = getSlope(tmp, seed);
                    if (!cnt.containsKey(slope)) {
                        cnt.put(slope, 1);
                    } else {
                        cnt.put(slope, cnt.get(slope) + 1);
                    }
                    most = Math.max(most, cnt.get(slope));
                }
            }
            most = Math.max(most, sameX) + same;
            result = Math.max(result, most);
        }
        return result;
    }

    private static BigDecimal getSlope(Point a, Point b){
        if(b.x == a.x){
            return BigDecimal.valueOf(Integer.MAX_VALUE);
        }
        return BigDecimal.valueOf(b.y -a.y).divide(BigDecimal.valueOf(b.x - a.x), new MathContext(20));
    }
}



