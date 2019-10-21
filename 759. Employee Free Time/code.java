//We are given a list schedule of employees, which represents the working time for each employee.

//Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

//Return the list of finite intervals representing common, positive-length free time for all employees, 
//also in sorted order.

//Example 1:
//
//Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
//Output: [[3,4]]
//Explanation:
//There are a total of three employees, and all common
//free time intervals would be [-inf, 1], [3, 4], [10, inf].
//We discard any intervals that contain inf as they aren't finite.

//Example 2:
//
//Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
//Output: [[5,6],[7,9]]

//(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. 
//For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
//Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

//Note:
//
//schedule and schedule[i] are lists with lengths in range [1, 50].
//0 <= schedule[i].start < schedule[i].end <= 10^8.
//NOTE: input types have been changed on June 17, 2019. 
//Please reset to default code definition to get new method signature.




/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start,int _end) {
        start = _start;
        end = _end;
    }
};
*/
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                if (i1.start == i2.start) {
                    return i1.end - i2.end;
                }
                return i1.start - i2.start;
            }
        });
        
        for (List<Interval> list : schedule) {
            for (Interval inter : list) {
                pq.offer(inter);                
            }
        }
        
        int start = 0;
        int end = 0;
        int maxEnd = pq.peek().end;
        
        while(!pq.isEmpty()) {
            Interval inter = pq.poll();
            start = inter.start;
            end = inter.end;
            if (start > maxEnd) {
                result.add(new Interval(maxEnd, start));
            }
            maxEnd = Math.max(maxEnd, end);
        }
        return result;
    }
}



