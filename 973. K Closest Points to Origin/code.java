//We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
//(Here, the distance between two points on a plane is the Euclidean distance.)
//You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

//Example 1:
//
//Input: points = [[1,3],[-2,2]], K = 1
//Output: [[-2,2]]
//Explanation: 
//The distance between (1, 3) and the origin is sqrt(10).
//The distance between (-2, 2) and the origin is sqrt(8).
//Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
//We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

//Example 2:
//
//Input: points = [[3,3],[5,-1],[-2,4]], K = 2
//Output: [[3,3],[-2,4]]
//(The answer [[-2,4],[3,3]] would also be accepted.) 

//Note:
//
//1 <= K <= points.length <= 10000
//-10000 < points[i][0] < 10000
//-10000 < points[i][1] < 10000




class Solution {
    
    // Priority Queue (MaxHeap)
    // Time: O(NlogK)
    // Space: O(K)
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new myComparator());
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[K][2];
        for (int i = K - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }
        return res;
    }
    
    class myComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a1, int[] a2) {
            return (a2[0] * a2[0] + a2[1] * a2[1]) - (a1[0] * a1[0] + a1[1] * a1[1]);
        }
    }
    
    
    /*
    In the quick sort, we will always choose a pivot to compare with other elements. After one iteration, we will get an array that all elements smaller than the pivot are on the left side of the pivot and all elements greater than the pivot are on the right side of the pviot (assuming we sort the array in ascending order). 
    So, inspired from this, each iteration, we choose a pivot and then find the position p the pivot should be. Then we compare p with the K, if the p is smaller than the K, meaning the all element on the left of the pivot are all proper candidates but it is not adequate, we have to do the same thing on right side, and vice versa. If the p is exactly equal to the K, meaning that we've found the K-th position. Therefore, we just return the first K elements, since they are not greater than the pivot.
    */
    
    //quick select
    // Time: n + n/2 + n/4 ... + 1 = 2n - 1 = O(N)
    // Space: O(logn)
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][];
        if (points == null || points.length == 0 || K >= points.length) {
            return res;
        }
        sort(points, K, 0, points.length - 1);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void sort(int[][] points, int K, int start, int end) {
        if (start >= end) return;
        
        /* Fancy way to pick pivot
        int k = ThreadLocalRandom.current().nextInt(start, end);
        // swap with the end
        swap(points, k, end);
        */

        // here, we just pick the last as pivot

        int mid = partition(points, start, end);
        // int leftLength = mid - start + 1;

        /* Method 1 to switch*/
        if (K < mid) {
            sort(points, K, start, mid - 1);
        } else if (K > mid) {
            sort(points, K, mid + 1, end);
        }

        /* Method 2 to switch*/
        // if (K < leftLength)
        //     sort(points, K, start, mid - 1);
        // else if (K > leftLength)
        //     sort(points, K, mid + 1, end);

        // else we find right position K == mid
    }

    private int partition(int[][] points, int l, int r) {
        int tmp = r;
        int pivotDist = getDist(points[r]);
        r -= 1;

        while (l <= r) {
            while (l <= r && getDist(points[l]) < pivotDist) {
                l++;
            }
            while (l <= r && getDist(points[r]) >= pivotDist) {
                r--;
            }
            if (l <= r) {
                swap(points, l, r);
                l++;
                r--;
            }
        }
        swap(points, tmp, l);
        return l;
    }

    private void swap(int[][] points, int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }

    private int getDist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }    
}



