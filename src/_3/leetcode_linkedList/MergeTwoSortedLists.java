package _3.leetcode_linkedList;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * 
 * Input: 1->2->4, 1->3->4 Output: 1->1->2->3->4->4
 * 
 * @author steven
 *
 */
public class MergeTwoSortedLists {

	public static void main(String[] args) {
		LinkedList1 l1 = new LinkedList1();
		LinkedList1 l2 = new LinkedList1();
		
		/*
		 * Use push() to construct below list 1->12->1->4->1
		 */
		l1.push(4);
		l1.push(2);
		l1.push(1);

		l2.push(4);
		l2.push(3);
		l2.push(1);
		
		System.out.println("Before merge");
		l1.printList();
		l2.printList();
		
		/*
		 * I m deleting the head itself. You can check for more cases
		 */
		l1.mergeTwoLists(l1.head, l2.head);

		System.out.println("\nAfter Deleting");
		l1.printList();
	}

}

class LinkedList1 {
	ListNode head; // head of the list

	class ListNode {
		int val;
		ListNode next;

		ListNode(int d) {
			val = d;
			next = null;
		}
	}

	/*
	 * Given a reference to the head of a list and an int, inserts a new Node on the
	 * front of the list.
	 */
	public void push(int new_data) {
		/* 1. alloc the Node and put the data */
		ListNode new_Node = new ListNode(new_data);

		/* 2. Make next of new Node as head */
		new_Node.next = head;

		/* 3. Move the head to point to new Node */
		head = new_Node;
	}

	/*
	 * This function prints contents of linked list starting from the given Node
	 */
	public void printList() {
		ListNode tNode = head;
		while (tNode != null) {
			System.out.print(tNode.val + " ");
			tNode = tNode.next;
		}
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}

		ListNode result = new ListNode(0);
		ListNode prev = result; // prev 引子會變動
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next; // 新接上的 Node 為 prev
		}
		if (l1 != null) {
			prev.next = l1;
		}
		if (l2 != null) {
			prev.next = l2;
		}
		return result.next;
	}

}