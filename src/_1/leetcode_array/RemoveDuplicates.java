package _1.leetcode_array;

public class RemoveDuplicates {

	/**
	 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
		Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
		Given nums = [0,0,1,1,1,2,2,3,3,4],
		Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
		It doesn't matter what values are set beyond the returned length.

	 	Return k after placing the final result in the first k slots of nums.
	 */
	public static void main(String[] args) {
		RemoveDuplicates s = new RemoveDuplicates();
		int[] nums = {0,0,1,1,1,2,2,3,3,4};
//		int[] nums = {1,1,2};
		int result =  s.removeDuplicates(nums);
		
		for (int i : nums) {
			System.out.print(i);
		}

		System.out.println("	result: " + result);
	}
	
	/**
	 * use slow and fast pointer to remove duplicated elements in-place
	 * @param nums integer array
	 * @return location of final result slot plus one
	 */
	public int removeDuplicates(int[] nums) {

		int fast = 1; // iterator through array, could be 0
		int slow = 0; // current index
		for (; fast < nums.length; fast++) {
			if (nums[fast] != nums[slow]) { // new number
				slow++; // move current index
				nums[slow] = nums[fast]; // fill current index with new number
			}
		}
		return slow + 1;
	}

}
