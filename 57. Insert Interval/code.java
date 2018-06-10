//Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

//You may assume that the intervals were initially sorted according to their start times.

//Example 1:
//
//Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
//Output: [[1,5],[6,9]]

//Example 2:
//
//Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//Output: [[1,2],[3,10],[12,16]]
//Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].



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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null){
            return intervals;
        }

        List<Interval> mergedList = new ArrayList<>();

        if (intervals == null || intervals.size() == 0){
            mergedList.add(newInterval);
            return mergedList;
        }

        intervals.add(newInterval);
        intervals.sort(new myComparator());

        int lastStart = intervals.get(0).start;
        int lastEnd = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= lastEnd){
                lastEnd = Math.max(intervals.get(i).end, lastEnd);
            }
            else {
                mergedList.add(new Interval(lastStart, lastEnd));
                lastStart = intervals.get(i).start;
                lastEnd = intervals.get(i).end;
            }
        }
        
        mergedList.add(new Interval(lastStart, lastEnd));
        
        return mergedList;
    }
}

class myComparator implements Comparator<Interval>{
    @Override
    public int compare(Interval o1, Interval o2) {
        return o1.start - o2.start;
    }
}

