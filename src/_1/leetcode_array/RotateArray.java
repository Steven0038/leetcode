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
		int len = nums.length;

		// If arr is rotated n times then you get the same array
		while(k > len) { // [1,2] k = 3 =>[2,1]; [1,2] k = 1 =>[2,1];
			k = k - len;
		}

		// Creating a temporary array of size len
		int[] tmp = new int[len - k];

		// 1. copy first len-k element to tmp array
//		if (len - k >= 0) System.arraycopy(nums, 0, tmp, 0, len - k);
		for(int i = 0; i < len - k; i++) {
			tmp[i] = nums[i];
		}

		// 2. moving the rest element to index zero to k
		for(int i = len - k; i < len; i++) {
			nums[i - len + k] = nums[i]; //  nums[] = [4, 5], 將 len - k 移到 0 index 開始(i - len + k = 0)
		}

		// 3. copying the temp array element to nums
		for(int i = 0; i < tmp.length; i++) {
			nums[i + k] = tmp[i]; // Output nums[] = [4, 5, 1, 2, 3]  NOTE: i + k
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
