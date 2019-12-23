package _1.leetcode_array;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * 
 * Example 1:
 * 
 * Input: [1,2,3,4,5,6,7] and k = 3 Output: [5,6,7,1,2,3,4] 
 * Explanation: 
 * rotate 1 steps to the right: [7,1,2,3,4,5,6] 
 * rotate 2 steps to the right: [6,7,1,2,3,4,5] 
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * 
 * @author steven
 *
 */
public class RotateArray {

	public static void main(String[] args) {
		RotateArray s = new RotateArray();
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		s.rotate(nums, 3);
		s.printArray(nums);
	}

	// insert solution
	public void rotate(int[] nums, int k) {
		
		for (int j = 0; j < k; j++) {
			int key = nums[nums.length - 1];// 要翻轉的對象
			int i = nums.length - 1;
			while(i > 0) {// 除了要翻轉的對象, 每個都向後退一格
				nums[i] = nums[i - 1];
				i--;
			}
			nums[0] = key;//把要翻轉的對象丟到最前面
		}
	}
	
	
	
	
	
//	public void rotate(int[] nums, int k) {
//		int[] tmp = new int[nums.length];
//		int i = 0;
//		
//		//add the rotate elements
//		while (i < k) {
//			tmp[i] = nums[nums.length - 1 - i];
//			i++;
//		}
//		
//		int n = i;
//		//add the remaining elements
//		for (int j = 0; j < nums.length - i; j++) {
//			tmp[n] = nums[j];
//			n++;
//		}
//		
//		
//		//copy tmp to nums
//		for(int j = 0; j < tmp.length; j++) {
//			nums[j] = tmp[j];
//		}
//	}

	public void printArray(int[] nums) {
		for (int i : nums) {
			System.out.print(String.valueOf(i).concat(" "));
		}
	}

}
