//Design and implement a data structure for Least Recently Used (LRU) cache. 
//It should support the following operations: get and put.

//get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
//put(key, value) - Set or insert the value if the key is not already present. 
//When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

//Follow up:
//Could you do both operations in O(1) time complexity?

//Example:
//
//  LRUCache cache = new LRUCache( 2 /* capacity */ );
//
//  cache.put(1, 1);
//  cache.put(2, 2);
//  cache.get(1);       // returns 1
//  cache.put(3, 3);    // evicts key 2
//  cache.get(2);       // returns -1 (not found)
//  cache.put(4, 4);    // evicts key 1
//  cache.get(1);       // returns -1 (not found)
//  cache.get(3);       // returns 3
//  cache.get(4);       // returns 4




public class LRUCache {
    static class Node {
        Node next;
        Node prev;
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        void update(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int limit;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;    
    
    /*
    * @param capacity: An integer
    */
    public LRUCache(int limit) {
        this.limit = limit;
        this.map = new HashMap<>();
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        remove(node);
        append(node);
        return node.value;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void put(int key, int value) {
        Node node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            remove(node);
        } else if (map.size() < limit) {
            node = new Node(key, value);
        } else {
            node = tail;
            remove(node);
            node.update(key, value);
        }
        append(node);
    }
    
    private Node remove(Node node) {
        map.remove(node.key);
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == head) {
            head = head.next;
        }
        if (node == tail) {
            tail = tail.prev;
        }
        node.next = node.prev = null;
        return node;
    }

    private Node append(Node node) {
        map.put(node.key, node);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        return node;
    }    
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */



