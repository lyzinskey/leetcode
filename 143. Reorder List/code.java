//Given a singly linked list L: L0→L1→…→Ln-1→Ln,
//reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

//You may not modify the values in the list's nodes, only nodes itself may be changed.

//Example 1:
//
//Given 1->2->3->4, reorder it to 1->4->2->3.

//Example 2:
//
//Given 1->2->3->4->5, reorder it to 1->5->2->4->3.



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {        
        if (head == null || head.next == null) {
            return;
        }

        ListNode middle = middleNode(head);
        ListNode firstHalf = head;
        ListNode secondHalf = middle.next;
        middle.next = null;

        head = merge(firstHalf, reverse(secondHalf));        
    }

    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }


    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    private ListNode merge(ListNode one, ListNode two) {
        ListNode head = new ListNode(0);
        ListNode dummy = head;

        while (one != null && two != null) {
            dummy.next = one;
            one = one.next;
            dummy = dummy.next;
            dummy.next = two;
            two = two.next;
            dummy = dummy.next;
        }

        if (one != null) {
            dummy.next = one;
        } else {
            dummy.next = two;
        }

        return head.next;
    }    
}


