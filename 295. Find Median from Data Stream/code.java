//Median is the middle value in an ordered integer list. 
//If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

//For example,
//[2,3,4], the median is 3
//[2,3], the median is (2 + 3) / 2 = 2.5

//Design a data structure that supports the following two operations:
//
//void addNum(int num) - Add a integer number from the data stream to the data structure.
//double findMedian() - Return the median of all elements so far.

//Example:
//
//addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2




class MedianFinder {
    private PriorityQueue<Integer> smallHalf;
    private PriorityQueue<Integer> largeHalf;
    private int size;
    
    public MedianFinder() {
        this.smallHalf = new PriorityQueue<>(Collections.reverseOrder());
        this.largeHalf = new PriorityQueue<>();
        this.size = 0;        
    }
    
    public void addNum(int value) {
        size++;
        if (smallHalf.isEmpty() || value <= smallHalf.peek()) {
            smallHalf.offer(value);
            if (smallHalf.size() > largeHalf.size() + 1) {
                largeHalf.offer(smallHalf.poll());
            }
        }
        else {
            largeHalf.offer(value);
            if (smallHalf.size() < largeHalf.size()) {
                smallHalf.offer(largeHalf.poll());
            }
        }            
    }
    
    public double findMedian() {
        if (size % 2 == 0) {
            return (smallHalf.peek() + largeHalf.peek()) / 2.0;
        }
        else {
            return (double) smallHalf.peek();
        }        
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
 
 
 
 
