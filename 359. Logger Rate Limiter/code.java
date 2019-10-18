//Design a logger system that receive stream of messages along with its timestamps, 
//each message should be printed if and only if it is not printed in the last 10 seconds.

//Given a message and a timestamp (in seconds granularity), 
//return true if the message should be printed in the given timestamp, otherwise returns false.

//It is possible that several messages arrive roughly at the same time.

//Example:
//
//Logger logger = new Logger();
//
//  // logging string "foo" at timestamp 1
//logger.shouldPrintMessage(1, "foo"); returns true; 
//
//  // logging string "bar" at timestamp 2
//logger.shouldPrintMessage(2,"bar"); returns true;
//
//  // logging string "foo" at timestamp 3
//logger.shouldPrintMessage(3,"foo"); returns false;
//
//  // logging string "bar" at timestamp 8
//logger.shouldPrintMessage(8,"bar"); returns false;
//
//  // logging string "foo" at timestamp 10
//logger.shouldPrintMessage(10,"foo"); returns false;
//
//  // logging string "foo" at timestamp 11
//logger.shouldPrintMessage(11,"foo"); returns true;




class Logger {
    Map<String, Integer> hashmap;
    
    /** Initialize your data structure here. */
    public Logger() {
        hashmap = new HashMap<>();        
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (hashmap.containsKey(message) && hashmap.get(message) > timestamp) {
            return false;
        }
        hashmap.put(message, timestamp + 10);
        return true;
    }
}


// class Logger {
//     Queue<Log> queue;
//     Set<String> hashset;
    
//     /** Initialize your data structure here. */
//     public Logger() {
//         this.queue = new LinkedList<>();
//         this.hashset = new HashSet<>();        
//     }
    
//     /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
//         If this method returns false, the message will not be printed.
//         The timestamp is in seconds granularity. */
//     public boolean shouldPrintMessage(int timestamp, String message) {
//         while (!queue.isEmpty() && timestamp - queue.peek().timestamp >= 10) {
//             hashset.remove(queue.peek().message);
//             queue.poll();            
//         }
//         if (hashset.contains(message)) {
//             return false;
//         }
//         hashset.add(message);
//         queue.offer(new Log(timestamp, message));
//         return true;
//     }
// }

// class Log {
//     int timestamp;
//     String message;
    
//     public Log (int timestamp, String message) {
//         this.timestamp = timestamp;
//         this.message = message;
//     }
// }

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
 
 
 
 
