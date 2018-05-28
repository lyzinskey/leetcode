//Design and implement a TwoSum class. It should support the following operations: add and find.

//add - Add the number to an internal data structure.
//find - Find if there exists any pair of numbers which sum is equal to the value.

//Example 1:
//
//add(1); add(3); add(5);
//find(4) -> true
//find(7) -> false

//Example 2:
//
//add(3); add(1); add(2);
//find(3) -> true
//find(6) -> false


    Map<Integer,Integer> hm;
    
    TwoSum(){
        hm = new HashMap<Integer,Integer>();
    }
    // Add the number to an internal data structure.
	public void add(int number) {
	    if(hm.containsKey(number)){
	        hm.put(number,2);
	    }else{
	        hm.put(number,1);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    Iterator<Integer> iter = hm.keySet().iterator();
	    while(iter.hasNext()){
	        int num1 = iter.next();
	        int num2 = value - num1;
	        if(hm.containsKey(num2)){
	            if(num1 != num2 || hm.get(num2) == 2){
	                return true;
	            }
	        }
	    }
	    return false;
	}
  
  
