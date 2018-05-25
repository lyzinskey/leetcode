//Given a linked list, determine if it has a cycle in it.

//Follow up:
//Can you solve it without using extra space?


    public boolean hasCycle(ListNode head) {
        if (head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            if (fast == slow){
                return true;
            }
            slow = slow.next;
        }
        return false;
    }
    
    
