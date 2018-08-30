//Given a sorted linked list, delete all nodes that have duplicate numbers, 
//leaving only distinct numbers from the original list.

//Example 1:
//
//Input: 1->2->3->3->4->4->5
//Output: 1->2->5

//Example 2:
//
//Input: 1->1->1->2->3
//Output: 2->3



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;

        ListNode node = dummyhead;

        while (node.next != null && node.next.next != null) {
            if (node.next.val == node.next.next.val) {
                int val = node.next.val;
                while (node.next != null && node.next.val == val) {
                    node.next = node.next.next;
                }
            }
            else {
                node = node.next;
            }
        }
        return dummyhead.next;
    }    
}



