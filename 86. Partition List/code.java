//Given a linked list and a value x, 
//partition it such that all nodes less than x come before nodes greater than or equal to x.

//You should preserve the original relative order of the nodes in each of the two partitions.

//Example:
//
//Input: head = 1->4->3->2->5->2, x = 3
/Output: 1->2->2->4->3->5



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int target) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode dummySmall = new ListNode(0);
        ListNode small = dummySmall;

        ListNode dummyLarge = new ListNode(0);
        ListNode large = dummyLarge;

        while (curr != null) {
            if (curr.val < target) {
                small.next = new ListNode(curr.val);
                small = small.next;
            }
            else {
                large.next = new ListNode(curr.val);
                large = large.next;
            }
            curr = curr.next;
        }

        small.next = dummyLarge.next;
        large.next = null;

        return dummySmall.next;
    }
}


