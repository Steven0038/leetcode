package _3.leetcode_linkedList;

/**
 * Reverse a singly linked list.
 * 
 * Example:
 * 
 * Input: 1->2->3->4->5->NULL Output: 5->4->3->2->1->NULL
 * 
 * Follow up: A linked list can be reversed either iteratively or recursively.
 * Could you implement both?
 * 
 * @author steven
 *
 */
public class ReverseLinkedList {
	  
    static Node head; 
  
    static class Node { 
  
        int data; 
        Node next; 
  
        Node(int d) { 
            data = d; 
            next = null; 
        } 
    } 
  
    /* Function to reverse the linked list */
    Node reverse(Node node) { 
        Node prev = null; 
        Node current = node; 
        Node next = null; 
        while (current != null) { 
            next = current.next; 
            current.next = prev; 
            prev = current; 
            current = next; 
        } 
        node = prev; 
        return node; 
    }

    private Node reverseRecursive(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node reversedHead = reverseRecursive(node.next);
        node.next.next = node;
        node.next = null;
        return reversedHead;
    }
  
    // prints content of double linked list 
    void printList(Node node) { 
        while (node != null) { 
            System.out.print(node.data + " "); 
            node = node.next; 
        } 
    } 
  
    public static void main(String[] args) { 
    	ReverseLinkedList list = new ReverseLinkedList(); 
        list.head = new Node(85);
        list.head.next = new Node(15); 
        list.head.next.next = new Node(4); 
        list.head.next.next.next = new Node(20); 
          
        System.out.println("Given Linked list"); 
        list.printList(head); 
        head = list.reverse(head); 
        System.out.println();
        System.out.println("Reversed linked list "); 
        list.printList(head); 
    } 
} 
