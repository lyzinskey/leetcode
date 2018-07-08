//Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

//push(x) -- Push element x onto stack.
//pop() -- Removes the element on top of the stack.
//top() -- Get the top element.
//getMin() -- Retrieve the minimum element in the stack.

//Example:
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> Returns -3.
//minStack.pop();
//minStack.top();      --> Returns 0.
//minStack.getMin();   --> Returns -2.



class MinStack {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;    
    /** initialize your data structure here. */
    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();         
    }
    
    public void push(int element) {
        if (stack2.empty() || element <= stack2.peek()) {
        stack2.push(element);
        }
        stack1.push(element);        
    }
    
    public void pop() {
        if (!stack1.empty()) {           
           int result = stack1.pop();
           if (result == stack2.peek()) {
               stack2.pop();
           }           
        }        
    }
    
    public int top() {
        return stack1.empty() ? -1 : stack1.peek();        
    }
    
    public int getMin() {
        return stack2.empty() ? -1 : stack2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
 
 
 
