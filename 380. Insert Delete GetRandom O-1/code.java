//Design a data structure that supports all following operations in average O(1) time.

//insert(val): Inserts an item val to the set if not already present.
//remove(val): Removes an item val from the set if present.
//getRandom: Returns a random element from current set of elements. 
//Each element must have the same probability of being returned.

//Example:
//
//  // Init an empty set.
//RandomizedSet randomSet = new RandomizedSet();
//
//  // Inserts 1 to the set. Returns true as 1 was inserted successfully.
//randomSet.insert(1);
//
//  // Returns false as 2 does not exist in the set.
//randomSet.remove(2);
//
//  // Inserts 2 to the set, returns true. Set now contains [1,2].
//randomSet.insert(2);
//
//  // getRandom should return either 1 or 2 randomly.
//randomSet.getRandom();
//
//  // Removes 1 from the set, returns true. Set now contains [2].
//randomSet.remove(1);
//
//  // 2 was already in the set, so return false.
//randomSet.insert(2);
//
//  // Since 2 is the only number in the set, getRandom always return 2.
//randomSet.getRandom();



class RandomizedSet {
        
    private Map<Integer, Integer> hashmap;
    private List<Integer> list;
    private Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        hashmap = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (hashmap.containsKey(val)) {
            return false;
        }
        
        hashmap.put(val, list.size());
        list.add(val);
        return true;        
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!hashmap.containsKey(val)) {
            return false;
        }
        
        int index = hashmap.get(val);
        if (index < list.size() - 1) {
            int lastVal = list.get(list.size() - 1);
            list.set(index, lastVal);
            hashmap.put(lastVal, index);            
        }
        
        //The second point is that the complexity of ArrayList.remove(index) 
        //is sensitive to the value of index as well as the list length.
        //The "advertised" complexity of O(N) for the average and worst cases.
        //In the best case, the complexity is actually O(1).
        //This happens when you remove the last element of the list; i.e. index == list.size() - 1.          
        list.remove(list.size() - 1);
        hashmap.remove(val);
        return true;        
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
 
 
 
