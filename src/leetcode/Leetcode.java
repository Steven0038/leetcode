package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Leetcode {

	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		Stack<Integer> stack = new Stack<>();
		Queue<Integer> queue = new LinkedList<>();
		while (x != 0) {
			int t = x % 10;
			stack.push(t);
			queue.offer(t);
			x = x / 10;
		}
		
		while (!stack.empty()) {
			int tem = stack.pop();
			if (tem != queue.poll()) {
				return false;
			}
		}
		return true;
	}
}
