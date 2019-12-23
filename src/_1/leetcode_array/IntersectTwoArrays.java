package _1.leetcode_array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2] Output: [2,2] 
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4] Output: [4,9] 
 * 
 * Note:
 * Each element in the result should appear as many times as it shows in both
 * arrays. The result can be in any order. 
 * 
 * Follow up:
 * What if the given array is already sorted? How would you optimize your
 * algorithm? What if nums1's size is small compared to nums2's size? Which
 * algorithm is better? What if elements of nums2 are stored on disk, and the
 * memory is limited such that you cannot load all elements into the memory at
 * once?
 * 
 * @author steven
 *
 */
public class IntersectTwoArrays {

	public static void main(String[] args) {
		IntersectTwoArrays it = new IntersectTwoArrays();
		int[] nums1 = { 1, 2, 2, 1 };
		int[] nums2 = { 2, 2 };
		int[] intersects = it.intersect(nums1, nums2);

		for (int i : intersects) {
			System.out.print(i);
			System.out.print(" ");
		}
	}

	// Input: nums1 = [1,2,2,1], nums2 = [2,2] Output: [2,2]
    public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		// add nums1's element to Map's key and count the times in value
		for (int num1 : nums1) {
			if (numMap.containsKey(num1)) {
				numMap.put(num1, numMap.get(num1) + 1);
			} else {
				numMap.put(num1, 1);
			}
		}

		// if nums2 contains the same element, add to the result
		for (int i = 0; i < nums2.length; i++) {
			if (numMap.containsKey(nums2[i]) && numMap.get(nums2[i]) > 0) {
				result.add(nums2[i]);
				numMap.put(nums2[i], numMap.get(nums2[i]) - 1);// reduce the intersect count
			}
		}

		// copy the result to arr
		int[] retArr = new int[result.size()];

		for (int i = 0; i < retArr.length; i++) {
			retArr[i] = result.get(i);
		}

		return retArr;
	}

}
