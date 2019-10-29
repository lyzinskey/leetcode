//In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

//When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  
//If there are multiple such seats, they sit in the seat with the lowest number.  
//(Also, if no one is in the room, then the student sits at seat number 0.)

//Return a class ExamRoom(int N) that exposes two functions: 
//ExamRoom.seat() returning an int representing what seat the student sat in, 
//and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  
//It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

//Example 1:
//
//Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
//Output: [null,0,9,4,2,null,5]
//Explanation:
//ExamRoom(10) -> null
//seat() -> 0, no one is in the room, then the student sits at seat number 0.
//seat() -> 9, the student sits at the last seat number 9.
//seat() -> 4, the student sits at the last seat number 4.
//seat() -> 2, the student sits at the last seat number 2.
//leave(4) -> null
//seat() -> 5, the student sits at the last seat number 5.

//Note:
//
//1 <= N <= 10^9
//ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
//Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.





class ExamRoom {
    private PriorityQueue<Interval> pq;
    private int N;

    public ExamRoom(int N) {
        this.pq = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.distance == o2.distance) {
                    return o1.start - o2.start;
                }
                return o2.distance - o1.distance;
            }
        });
        this.pq.offer(new Interval(-1, N));

        this.N = N;
    }

    public int seat() {
        Interval inter = pq.poll();
        int seat;
        if (inter.start == -1) {
            seat = 0;
        } else if (inter.end == N) {
            seat = N - 1;
        } else {
            seat = (inter.start + inter.end) / 2;
        }
        pq.offer(new Interval(inter.start, seat));
        pq.offer(new Interval(seat, inter.end));
        return seat;
    }

    public void leave(int p) {
        Interval start = null;
        Interval end = null;
        
        for (Interval inter : pq) {
            if (inter.start == p) {
                end = inter;
            }
            if (inter.end == p) {
                start = inter;
            }
            if (start != null && end != null) {
                break;
            }
        }
        pq.remove(start);
        pq.remove(end);
        pq.offer(new Interval(start.start, end.end));
    }

    private class Interval {
        int start;
        int end;
        int distance;

        private Interval(int start, int end) {
            this.start = start;
            this.end = end;
            if (start == -1) {
                this.distance = end;
            } else if (end == N) {
                this.distance = N - 1 - start;
            } else {
                this.distance = (end - start) / 2;
            }
        }
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
 
 
 
 
