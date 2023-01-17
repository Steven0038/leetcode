package _3.leetcode_linkedList;

/**
 * 2. Add Two Numbers, medium
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers atn = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        atn.addTwoNumbers(l1, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0); //NOTE:0
        ListNode result = temp;

        int currNum = 0; // 當前累加值
        int decimal = 0; // 進位符, 只會是 0 or 1

        while (l1 != null || l2 != null || decimal > 0) { // NOTE: 需要考慮 decimal 最後進位為1
            if (l1 != null) {
                currNum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                currNum += l2.val;
                l2 = l2.next;
            }

            currNum += decimal;
            decimal = currNum > 9 ? 1 : 0;
            currNum %= 10;

            // if (currNum > 9) {
            //     currNum -= 10; // ex: 17, 則 assign 給 tmp Node 17 - 10 = 7,
            //     decimal = 1;   //         並 assign 進位符 1 給下個 node 使用
            // } else {
            //     decimal = 0;
            // }

            temp.next = new ListNode(currNum);
            temp = temp.next;

            currNum = 0; //NOTE
        }

        return result.next;
    }
}


