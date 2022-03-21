package _1.leetcode_array;

import java.util.*;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2] Output: [2,2]
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4] Output: [4,9]
 * <p>
 * Note:
 * Each element in the result should appear as many times as it shows in both
 * arrays. The result can be in any order.
 * <p>
 * Follow up:
 * What if the given array is already sorted? How would you optimize your
 * algorithm? What if nums1's size is small compared to nums2's size? Which
 * algorithm is better? What if elements of nums2 are stored on disk, and the
 * memory is limited such that you cannot load all elements into the memory at
 * once?
 *
 * @author steven
 */
public class IntersectTwoArrays {

    public static void main(String[] args) {
        IntersectTwoArrays it = new IntersectTwoArrays();
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] intersects = it.intersect(nums1, nums2);

        for (int i : intersects) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    // Input: nums1 = [1,2,2,1], nums2 = [2,2] Output: [2,2]
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> numMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        // add nums1's element to Map's key and count the times in value
//		for (int num1 : nums1) {
//			if (numMap.containsKey(num1)) {
//				numMap.put(num1, numMap.get(num1) + 1);
//			} else {
//				numMap.put(num1, 1);
//			}
//		}

        for (int num : nums1) {
            numMap.put(num, numMap.getOrDefault(num, 0) + 1);
        }

        // if nums2 contains the same element, add to the result
        for (int j : nums2) {
            if (numMap.containsKey(j) && numMap.get(j) > 0) {
                result.add(j);
                numMap.put(j, numMap.get(j) - 1); // reduce intersect count
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
