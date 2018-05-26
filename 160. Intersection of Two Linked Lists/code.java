//Write a program to find the node at which the intersection of two singly linked lists begins.

//For example, the following two linked lists:
//
//A:      a1 → a2
//               ↘
//                c1 → c2 → c3
//               ↗
//B: b1 → b2 → b3
//
//begin to intersect at node c1.

//Notes:
//
//If the two linked lists have no intersection at all, return null.
//The linked lists must retain their original structure after the function returns.
//You may assume there are no cycles anywhere in the entire linked structure.
//Your code should preferably run in O(n) time and use only O(1) memory.


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
     
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != null || b != null) {
            if (a != null) {
                a = a.next;
            } else {
                headB = headB.next;
            }
            if (b != null) {
                b = b.next;
            } else {
                headA = headA.next;
            }
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }        
        return headA;
    }

