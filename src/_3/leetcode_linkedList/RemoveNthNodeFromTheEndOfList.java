package _3.leetcode_linkedList;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head. 
 * 
 * Example:
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5. 
 * 
 * Note:
 * Given n will always be valid.
 * 
 * Follow up: 
 * Could you do this in one pass?
 * 
 * @author steven
 *
 */
public class RemoveNthNodeFromTheEndOfList {

	public static void main(String[] args) {
		Solution s = new Solution();
		s.push(5);//[1,2,3,4,5] head is 1
		s.push(4);
		s.push(3);
		s.push(2);
		s.push(1);
		
		s.printList();
		
		s.removeNthFromEnd(s.head, 2);
		System.out.println();
		s.printList();
	}

}

class Solution {
	ListNode head;
	
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	public void push(int newData) {
		ListNode newNode = new ListNode(newData);
		newNode.next = head;  //newNode - head 
		head = newNode; // - newHead - oldHead,  變成說, 最晚 Push 進來的當 head, 反著長
	}
	
	public void printList() {
		ListNode tNode = head;
		while(tNode != null) {
			System.out.print(tNode.val + " ");
			tNode = tNode.next;
		}
	}

	
	public ListNode removeNthFromEnd(ListNode head, int n) {
		// Given linked list: 1->2->3->4->5, and n = 2.
		// After removing the second node from the end, 1->2->3->5.
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode fast = dummy;
		ListNode slow = dummy;
		int temp = n;//需要刪除倒數第 n 個
		// 先讓 fast 走 n 步, 之後快慢一起走,
		for (; fast.next != null; temp--) {// 直到 fast.next 為 null 時, slow 會在要刪除的位置上前一個 Node
			if (temp <= 0) { // control, temp 剛好為 0 的時候, slow 就放到 head, 與 fast 相距 n, 之後跟著 fast 一起前進
				slow = slow.next;
			}
			fast = fast.next;
		}
		slow.next = slow.next.next;// delete Nth
		return dummy.next;
	}
}
