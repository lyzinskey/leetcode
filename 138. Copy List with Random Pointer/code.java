//A linked list is given such that each node contains an additional random pointer 
//which could point to any node in the list or null.

//Return a deep copy of the list.




/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        Map<RandomListNode, RandomListNode> hashmap = new HashMap<>();
        RandomListNode newHead = new RandomListNode(head.label);
        RandomListNode curr = newHead;
        hashmap.put(head, newHead);
        while (head != null) {
            if (head.next != null) {
                if (!hashmap.containsKey(head.next)) {
                    hashmap.put(head.next, new RandomListNode(head.next.label));
                }
                curr.next = hashmap.get(head.next);
            }

            if (head.random != null) {
                if (!hashmap.containsKey(head.random)) {
                    hashmap.put(head.random, new RandomListNode(head.random.label));
                }
                curr.random = hashmap.get(head.random);
            }
            head = head.next;
            curr = curr.next;
        }
        return newHead;
    }    
}



