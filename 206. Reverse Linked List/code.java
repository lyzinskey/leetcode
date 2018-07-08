//Reverse a singly linked list.

//Example:

//Input: 1->2->3->4->5->NULL
//Output: 5->4->3->2->1->NULL

//Follow up:
//
//A linked list can be reversed either iteratively or recursively. Could you implement both?



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 
 
// Iterative solution
//
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
    
        while (head != null) {
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
        }
  
        return prev;         
    }
}



// Recursive solution
//
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode newHead = reverseList(head.next);
        
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    
    
