package _1.leetcode_array;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus
 * one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of
 * the list, and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the
 * number 0 itself.
 *
 * Example 1:
 * Input: [1,2,3] Output: [1,2,4] Explanation: The array represents the integer 123. 
 * Example 2:
 * Input: [4,3,2,1] Output: [4,3,2,2] Explanation: The array represents the
 * integer 4321.
 *
 * @author steven
 *
 */
public class PlusOne {

	public static void main(String[] args) {
		PlusOne po = new PlusOne();
		int[] digits = {1,2,9};
		po.plusOne(digits);

		for (int i : digits) {
			System.out.print(i);
			System.out.print(" ");
		}

	}

	/* Plus One
	 * Topic: 給定一個不為空的數字 array 作為一個數字的代表, 對其加1, 成為新的數字,開頭不為零
	 * Solution: 從尾部 iterate, 遇到非9就 +1 並 return, 遇到 9 就 assign 0 並繼續 iterate
	 *           但特例是, 如果第一個數也是9, 就要進位 + 1
	 */
	public int[] plusOne(int[] digits) {
		// add from the array tail, if tail is 9, add and plus one, move pointer until not 9
		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] < 9) { // pointer not 9
				digits[i]++;
				return digits;
			}

			digits[i] = 0; // pointer is 9
		}

		// if first digit is 9
		int[] newDigits = new int[digits.length + 1];// 如果進位，並且最前面也是9, 代表需要在最前面加 1
		newDigits[0] = 1;

		return newDigits;
	}

}
