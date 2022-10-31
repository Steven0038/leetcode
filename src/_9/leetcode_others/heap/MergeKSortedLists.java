package _9.leetcode_others.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists, hard, premium, O(nlogK), n = 所有 Node 總數
 * <p>
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * <p>
 * Output: [1,1,2,3,4,4,5,6]
 * <p>
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 */
class MergeKSortedLists {
    // Function to merge k sorted linked lists
    static ListNode mergeKList(ListNode[] arr) {
        // Priority_queue 'heap' implemented as min heap with the help of 'compare' function
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        ListNode head = new ListNode(0);
        ListNode tmpCurr = head;
        // Push the head nodes of all the k lists in 'heap'
        for (ListNode listNode : arr) {
            if (listNode != null) {
                heap.add(listNode);
            }
        }
        // Handles the case when k = 0 or lists have no elements in them
        if (heap.isEmpty()) return null;

        // Loop till 'heap' is not empty
        while (!heap.isEmpty()) {
            ListNode top = heap.poll(); // Get the top element of 'heap'
            tmpCurr.next = top; // Add the top element of 'heap' to the resultant merged list
            tmpCurr = tmpCurr.next;
            // Check if there is a node next to the 'top' node in the list of which 'top' node is a member
            if (top.next != null) {
                heap.add(top.next); // Push the next node of top node in 'heap'
            }
        }
        // Address of head node of the required merged list
        return head.next;
    }

    // Print linked list
    public static void printList(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    public static void main(String[] args) {
        int N = 3;

        // array to store head of linked list
        ListNode[] arr = new ListNode[N];

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        /* [ 1->4->5,
             1->3->4,
             2->6 ] */
        arr[0] = head1;
        arr[1] = head2;
        arr[2] = head3;

        ListNode res = mergeKList(arr);

        if (res != null)
            printList(res);
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int key) {
        val = key;
        next = null;
    }
}
