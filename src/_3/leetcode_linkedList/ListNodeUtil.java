package _3.leetcode_linkedList;

import java.util.ArrayList;
import java.util.List;

// TODO: singleton
public class ListNodeUtil {

    public ListNode createNewLinkedList(int[] nums) {
        ListNode prev = new ListNode(0);
        for (int n : nums) {
            prev.next = new ListNode(n);
            prev = prev.next;
        }

        return prev.next;
    }

    public List<ListNode> createIntersecLinkedList(int[] nums1, int[] nums2, int intersect) {
        List<ListNode> rets = new ArrayList<>();
        ListNode ret1 = new ListNode(0);
        ListNode ret2 = new ListNode(0);
        ListNode prev1 = ret1;
        ListNode prev2 = ret2;

        ListNode intersectNode = null;
        for (int n : nums1) {
            if (n == intersect) {
                intersectNode = new ListNode(n);
                prev1.next = intersectNode;
                break;
            }

            prev1.next = new ListNode(n);
            prev1 = prev1.next;
        }

        for (int n : nums2) {
            if (n == intersect) {
                assert prev2 != null;
                prev2.next = intersectNode;
                prev2 = prev2.next;
                continue;
            }
            assert prev2 != null;
            prev2.next = new ListNode(n);
            prev2 = prev2.next;
        }

        rets.add(ret1.next);
        rets.add(ret2.next);

        return rets;
    }

    public void printNodeVal(ListNode node) {
        while (node != null) {
            System.out.printf("[printNodeVal] node value: %s", node.val);
            System.out.println();
            node = node.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

