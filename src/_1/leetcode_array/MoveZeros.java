package _1.leetcode_array;

/**
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 *
 * Example:
 * Input: [0,1,0,3,12] Output: [1,3,12,0,0] Note:
 *
 * You must do this in-place without making a copy of the array. Minimize the
 * total number of operations.
 *
 * @author steven
 *
 */
public class MoveZeros {

	public static void main(String[] args) {
		int[] nums = {0,1,0,3,12};
		MoveZeros mz = new MoveZeros();
		mz.moveZeros(nums);

		for (int i  : nums) {
			System.out.print(i);
			System.out.print(" ");
		}
	}

	public void moveZeros(int[] nums) {// Input: [0,1,0,3,12] Output: [1,3,12,0,0]
		// find not zero and insert to the zero position
		int count = 0; // Count of non-zero elements

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[count] = nums[i]; // here count is incremented
				count++;// 這個位置必定是已經移到前面的非零數或者是零
			}

		}

		// Make all elements 0 from count to end.
		while (count < nums.length) {
			nums[count] = 0;
			count++;
		}

	}

}
