package _3.leetcode_linkedList;

/**
 * 328. Odd Even Linked List, medium
 * <p>
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 * <p>
 * The first node is considered odd, and the second node is even, and so on.
 * <p>
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * <p>
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        OddEvenLinkedList oel = new OddEvenLinkedList();
        OldListNode head = new OldListNode(1);
        head.next = new OldListNode(2);
        head.next.next = new OldListNode(3);
        head.next.next.next = new OldListNode(4);
        head.next.next.next.next = new OldListNode(5);

        oel.oddEvenList(head);
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public OldListNode oddEvenList(OldListNode head) {
        if (head==null || head.next==null) return head;
        OldListNode evenHead = head.next;
        OldListNode evenNode = evenHead; // ref 到同一記憶體位置, 但不是共用的參數
        OldListNode oddNode = head;
        // 簡單來說, 就是把奇數節點跟偶數節點分別串在一起, 最後再將奇數節點的末尾指向偶數節點的頭
        while (oddNode.next!=null && evenNode.next!=null) {
            oddNode.next = oddNode.next.next;
            oddNode = oddNode.next;
            evenNode.next = evenNode.next.next;
            evenNode = evenNode.next;
        }

        oddNode.next = evenHead;

        return head;
    }
}
