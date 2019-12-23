package _1.leetcode_array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of integers, every element appears twice except for
 * one. Find that single one.
 * 
 * Note: Your algorithm should have a linear runtime complexity. Could you
 * implement it without using extra memory?
 * 
 * Example 1: Input: [2,2,1] Output: 1
 * 
 * Example 2: Input: [4,1,2,1,2] Output: 4
 * 
 * @author steven
 *
 */
public class SingleNumber {

	public static void main(String[] args) {
		SingleNumber s = new SingleNumber();
		int[] nums = { 4, 1, 2, 1, 2 };
		System.out.print(s.singleNumber(nums));
	}

	/**
	 * we use bitwise XOR to solve this problem :
	 * 
	 * first , we have to know the bitwise XOR in java
	 * 
	 * 0 ^ N = N N ^ N = 0 So..... if N is the single number
	 * 
	 * N1 ^ N1 ^ N2 ^ N2 ^..............^ Nx ^ Nx ^ N
	 * 
	 * = (N1^N1) ^ (N2^N2) ^..............^ (Nx^Nx) ^ N
	 * 
	 * = 0 ^ 0 ^ ..........^ 0 ^ N
	 * 
	 * = N
	 * 
	 * @param nums
	 * @return
	 */
	public int singleNumber(int[] nums) {
		int ans = 0;

		int len = nums.length;
		for (int i = 0; i != len; i++)
			ans ^= nums[i];

		return ans;

	}

//	public int singleNumber(int[] nums) {
//		// number , counts
//		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//
//		for (int num : nums) {
//			if (map.containsKey(num)) {
//				map.put(num, map.get(num) + 1);
//			} else {
//				map.put(num, 1);
//			}
//		}
//
//		for (int k : map.keySet()) {
//			if (map.get(k) == 1) {
//				return k;
//			}
//		}
//
//		return 0;
//	}

}
