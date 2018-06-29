//Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

//Example:
//
//Input:
//  [
//    1->4->5,
//    1->3->4,
//    2->6
//  ]
//Output: 1->1->2->3->4->4->5->6



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    //using heap
    //Time complexity: O(nlogk)
    //
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, new ListNodeComparator());

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!heap.isEmpty()) {
            ListNode head = heap.poll();
            tail.next = head;
            tail = tail.next;
            if (head.next != null) {
                heap.add(head.next);
            }
        }

        return dummy.next;
    }
    
    private class ListNodeComparator implements Comparator<ListNode> {
        public int compare(ListNode A, ListNode B) {
            return A.val - B.val;
        }
    }    
}




    //merge list two by two
    //Time Complexity: O(nlogk)
    //
    public ListNode mergeKLists(ListNode[] lists) {           
        if (lists == null || lists.length == 0) {
            return null;
        }
                
        List<ListNode> mergedList = new ArrayList<>();
        
        for (ListNode node : lists) {
            mergedList.add(node);
        }
        
        while (mergedList.size() > 1) {
            List<ListNode> nodeList = new ArrayList<>();
            
            for (int i = 0; i < mergedList.size() - 1; i+=2) {
                ListNode merged = mergeTwoList(mergedList.get(i), mergedList.get(i + 1));
                nodeList.add(merged);
            }
            
            if (mergedList.size() % 2 == 1) {
                nodeList.add(mergedList.get(mergedList.size() - 1));
            }
            
            mergedList = nodeList;
        }
        
        return mergedList.get(0);
    }
    
    private ListNode mergeTwoList(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                a = a.next;
            }
            else {
                tail.next = b;
                b = b.next;
            }
            
            tail = tail.next;
        }
        
        tail.next = (a != null) ? a : b;
        
        return dummy.next;
    } 
    
    
    
    

    //Divide & Conquer
    //Time Complexity: O(nlogk)
    //
    public ListNode mergeKLists(ListNode[] lists) {           
        if (lists == null || lists.length == 0) {
            return null;
        }
                
        List<ListNode> mergedList = new ArrayList<>();
        
        for (ListNode node : lists) {
            mergedList.add(node);
        }
                
        return mergeHelper(mergedList, 0, mergedList.size() - 1);
    }
    
    private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }
        
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return mergeTwo(left, right);
    }    
    
    private ListNode mergeTwo(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                a = a.next;
            }
            else {
                tail.next = b;
                b = b.next;
            }
            
            tail = tail.next;
        }
        
        tail.next = (a != null) ? a : b;
        
        return dummy.next;
    }   
    
    
    
