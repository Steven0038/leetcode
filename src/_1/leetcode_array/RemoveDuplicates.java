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
	 * 原理就是一直向右找不同於 j 的數字, 然後放入 j 的右方, 題目表示, 只要對應的長度不重複就好, 不用管超出的部分
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {

		int i = 1; // iterator thru array
		int j = 0; // current index
		for (; i < nums.length; i++) { // i 右移, 直到找到與目前 j 不同的數字
			if (nums[i] != nums[j]) { // new number
				j++; // move current index, 將不同的數字放入 j 右方的 index
				nums[j] = nums[i]; // fill current index with new number
			}
		}
		return j + 1;
	}

}
