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
	
	public int[] plusOne(int[] digits) {
		
		// add from the array tail, if tail is 9, add and plus one�Bmove pointer until not 9
		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] < 9) { // pointer not 9
				digits[i]++;
				return digits;
			}
			
			digits[i] = 0; // pointer is 9
		}
		
		// if first digit is 9
		int[] newDigits = new int[digits.length + 1];// �p�G�i��A�åB�̫e���]�O9, �N��ݭn�b�̫e���[ 1 
		newDigits[0] = 1;
		
		return newDigits;
	}

}
