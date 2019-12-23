package _1.leetcode_array;

public class RemoveDuplicates {

	/**
	 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
		Given nums = [0,0,1,1,1,2,2,3,3,4],
		Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
		It doesn't matter what values are set beyond the returned length.
	 * @param args
	 */
	public static void main(String[] args) {
		RemoveDuplicates s = new RemoveDuplicates();
		int[] nums = {0,0,1,1,1,2,2,3,3,4};
//		int[] nums = {1,1,2};
		s.removeDuplicates(nums);
		
		for (int i : nums) {
			System.out.print(i);
		}
	}
	
	/**
	 * ��z�N�O�@���V�k�䤣�P�� j ���Ʀr, �M���J j ���k��, �D�ت��, �u�n���������פ����ƴN�n, ���κ޶W�X������
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {

		int i = 1; // iterator thru array
		int j = 0; // current index
		for (; i < nums.length; i++) { // i �k��, ������P�ثe j ���P���Ʀr
			if (nums[i] != nums[j]) { // new number
				j++; // move current index, �N���P���Ʀr��J j �k�誺 index
				nums[j] = nums[i]; // fill current index with new number
			}
		}
		return j + 1;
	}

}
