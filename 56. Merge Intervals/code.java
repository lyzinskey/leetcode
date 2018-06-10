//Given a collection of intervals, merge all overlapping intervals.

//Example 1:
//
//Input: [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

//Example 2:
//
//Input: [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considerred overlapping.


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0){
            return intervals;
        }
        
        intervals.sort(new myComparator());

        int lastStart = intervals.get(0).start;
        int lastEnd = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= lastEnd){
                lastEnd = Math.max(intervals.get(i).end, lastEnd);
                intervals.remove(i);
                intervals.get(i - 1).end = lastEnd;
                i--;
            }
            else {
                lastStart = intervals.get(i).start;
                lastEnd = intervals.get(i).end;
            }
        }
        
        return intervals;
    }
}

class myComparator implements Comparator<Interval>{
    @Override
    public int compare(Interval o1, Interval o2) {
        return o1.start - o2.start;
    }
}

