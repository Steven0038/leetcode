package _3.leetcode_linkedList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 160. Intersection of Two Linked Lists, easy
 * <p>
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 * <p>
 * For example, the following two linked lists begin to intersect at node c1:
 * <p>
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * <p>
 * Note that the linked lists must retain their original structure after the function returns.
 * <p>
 * Example 1:
 * <p>
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 * <p>
 * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 * - Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2n
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists it = new IntersectionOfTwoLinkedLists();
        int[] nums1 = new int[]{4, 1, 8, 4, 5};
        int[] nums2 = new int[]{5, 6, 1, 8, 4, 5};
        int intersect = 8;
        ListNodeUtil util = new ListNodeUtil();
        List<ListNode> heads = util.createIntersecLinkedList(nums1, nums2, intersect);
        System.out.println("--- node 1 ---");
        util.printNodeVal(heads.get(0));
        System.out.println("--- node 2 ---");
        util.printNodeVal(heads.get(1));
        System.out.println("--- Intersection node value ---");
        System.out.println(it.getIntersectionNode(heads.get(0), heads.get(1)).val);
    }

    /**
     * Solution1, 使用距交點的距離, O(m + n) time and use only O(1) memory
     * 觀察：第一個列表的節點總數+第二個列表的頭部到交點的距離=第二個列表的節點總數+第一個列表的頭部到交點的距離。
     * 這個想法是採取兩個指針， x 和 y，最初指向第一個和第二個列表的頭部。然後以相同的速度推進兩個指針，直到它們在一個公共節點處相遇。
     * 什麼時候 x 到達其末尾，將其重定向到第二個列表的頭部。什麼時候 y 到達其末尾，將其轉到第一個列表的頭部。所在的節點 x 滿足 y 是交點節點。
     * @param headA head of list A
     * @param headB head of list B
     * @return ListNode intersection node of A and B
     * @See https://www.techiedelight.com/zh-tw/find-intersection-point-of-two-linked-lists/
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 取兩個指向各自列表頭部的指針
        ListNode x = headA, y = headB;

        // 推進兩個指針，直到它們在一個公共節點處相遇
        while (x != y) {
            // 當第一個列表到達末尾時，將其重定向到
            // 第二個列表的頭部
            if (x == null) {
                x = headB;
            } else {
                x = x.next;
            }

            // 當第二個列表到達末尾時，將其重定向到
            // 第一個列表的頭部
            if (y == null) {
                y = headA;
            } else {
                y = y.next;
            }
        }

        // 返回公共節點
        return x;
    }

    /**
     * Solution2, use set
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode tmpA = headA;
        ListNode tmpB = headB;
        while (tmpA.next != null) {
            set.add(tmpA);
            tmpA = tmpA.next;
        }

        while (tmpB.next != null) {
            if (!set.add(tmpB)) {
                return tmpB;
            } else {
                tmpB = tmpB.next;
            }
        }

        return null;
    }

}
