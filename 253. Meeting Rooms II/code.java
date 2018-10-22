//Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
//find the minimum number of conference rooms required.

//Example 1:
//
//Input: [[0, 30],[5, 10],[15, 20]]
//Output: 2

//Example 2:
//
//Input: [[7,10],[2,4]]
//Output: 1




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
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        minHeap.offer(intervals[0].end);

        for (int i = 1; i < intervals.length; i++) {
            if (minHeap.peek() <= intervals[i].start) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i].end);
        }
        return minHeap.size();
    }
}



