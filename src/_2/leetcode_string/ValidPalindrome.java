package _2.leetcode_string;

/**
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 * 
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * 
 * Example 1:
 * Input: "A man, a plan, a canal: Panama" Output: true Example 2:
 * Input: "race a car" Output: false
 * 
 * @author steven
 *
 */
public class ValidPalindrome {

	public static void main(String[] args) {
		ValidPalindrome vp = new ValidPalindrome();
		String s = "A man, a plan, a canal: Panama";
		vp.isPalindrome(s);
	}

	private boolean isPalindrome(String s) {
		// iterate from head and tail, head++ tail--, until head >= tail
		if (s.isEmpty()) {
			return true;
		}
		
		int head = 0, tail = s.length() - 1;
		char cHead, cTail;
		// ignore non-alphanumeric characters and cases
		// return false if head not match tail
		while (head <= tail) {
			cHead = s.charAt(head);
			cTail = s.charAt(tail);
			
			if (!Character.isLetterOrDigit(cHead)) {
				head++;
			} else if (!Character.isLetterOrDigit(cTail)) {
				tail--;
			} else {
				if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
					return false;
				}
				head++;
				tail--;
			}
		}
		return true;
		
		
	}

}
