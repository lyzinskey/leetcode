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




import java.math.BigDecimal;
import java.math.MathContext;

class Solution {
    public int maxPoints(int[][] points) {    
        int result = 0;
        HashMap<BigDecimal, Integer> cnt = new HashMap<>();
        
        for (int i = 0; i < points.length; i++) {
            int[] seed = points[i];
            int same = 1;
            int sameX = 0;
            int most = 0;            
            cnt.clear();
            for (int j = i + 1; j < points.length; j++) {
                int[] tmp = points[j];
                if (tmp[0] == seed[0] && tmp[1] == seed[1]) {
                    same++;
                } else if (tmp[0] == seed[0]) {
                    sameX++;
                } else {
                    BigDecimal slope = getSlope(tmp, seed);
                    if (!cnt.containsKey(slope)) {
                        cnt.put(slope, 0);
                    }
                    cnt.put(slope, cnt.get(slope) + 1);                    
                    most = Math.max(most, cnt.get(slope));
                }
            }
            most = Math.max(most, sameX) + same;
            result = Math.max(result, most);
        }
        return result;
    }

    private static BigDecimal getSlope(int[] a, int[] b){
        if(b[0] == a[0]){
            return BigDecimal.valueOf(Integer.MAX_VALUE);
        }
        return BigDecimal.valueOf(b[1] -a[1]).divide(BigDecimal.valueOf(b[0] - a[0]), new MathContext(20));
    }
}




