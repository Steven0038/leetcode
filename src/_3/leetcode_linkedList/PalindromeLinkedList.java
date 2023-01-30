package _3.leetcode_linkedList;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {

	public static void main(String[] args) {
		PalindromeLinkedList pll = new PalindromeLinkedList();
		OldListNode head = new OldListNode(0);
		pll.isPalindrome(head);
	}

	private boolean isPalindrome(OldListNode head) {
		List<Integer> list = new ArrayList<Integer>();
		// add each node's value to list until find the last node
		OldListNode curr = head;
		while (curr.next != null) {
			list.add(curr.val);
			curr = head.next;
		}
		
		// iterate from both head and last to middle to check palindrome
		int start = 0;
		int end = list.size() - 1;
		
		while (start <= end) {
			if (list.get(start) != list.get(end)) {
				return false;
			}
			start++;
			end--;
		}
		
		
		return true;
	}

}

/**
 * new a ListNode obj.
 * @deprecated
 * This class is no longer used.
 * <p> Use {@link ListNodeUtil} instead.
 */
class OldListNode {
     int val;
     OldListNode next;
     OldListNode(int x) { val = x; }
 }