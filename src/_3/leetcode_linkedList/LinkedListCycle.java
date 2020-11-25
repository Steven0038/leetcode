package _3.leetcode_linkedList;

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * To represent a cycle in the given linked list, we use an integer pos which
 * represents the position (0-indexed) in the linked list where tail connects
 * to. If pos is -1, then there is no cycle in the linked list.
 * 
 * Example 1:
 * 
 * Input: head = [3,2,0,-4], pos = 1 Output: true Explanation: There is a cycle
 * in the linked list, where tail connects to the second node.
 * 
 * Example 2:
 * 
 * Input: head = [1,2], pos = 0 Output: true Explanation: There is a cycle in
 * the linked list, where tail connects to the first node.
 * 
 * Example 3:
 * 
 * Input: head = [1], pos = -1 Output: false Explanation: There is no cycle in
 * the linked list.
 * 
 * Follow up:
 * 
 * Can you solve it using O(1) (i.e. constant) memory?
 * 
 * @author steven
 *
 */
//Java program to detect loop in a linked list 
class LinkedListCycle {
	Node head; // head of list

	/* Linked list Node */
	class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	/* Inserts a new Node at front of the list. */
	public void push(int new_data) {
		/*
		 * 1 & 2: Allocate the Node & Put in the data
		 */
		Node new_node = new Node(new_data);

		/* 3. Make next of new Node as head */
		new_node.next = head;

		/* 4. Move the head to point to new Node */
		head = new_node;
	}

	/**
	 *  find  out if a LinkedList have a loop
	 *  快慢指針, 快指針一次走二步, 慢指針走一步, 如果快指針追到慢指針, 代表這個 LinkedList 有環
	 * @return
	 */
	int detectLoop() {
		Node slow_p = head, fast_p = head;
		while (slow_p != null && fast_p != null && fast_p.next != null) {
			slow_p = slow_p.next;
			fast_p = fast_p.next.next;
			if (slow_p == fast_p) {
				System.out.println("Found loop");
				return 1;
			}
		}
		return 0;
		/*Hash method
        HashSet<ListNode> set = new HashSet<ListNode>();
        while(head != null){
            if (set.contains(head)){
                return true;
            }
            set.add(head);
            head = head.next;
        }
        
        return false;*/
	}

	/* Drier program to test above functions */
	public static void main(String args[]) {
		LinkedListCycle lList = new LinkedListCycle();

		lList.push(20);
		lList.push(4);
		lList.push(15);
		lList.push(10);

		/* Create loop for testing */
		lList.head.next.next.next.next = lList.head;

		lList.detectLoop();
	}
}
