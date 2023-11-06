package _5.leetcode_sort_and_search;

import java.util.*;

/**
 * 34. Find First and Last Position of Element in Sorted Array, medium
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 */
public class SearchForRange {
    public static void main(String[] args) {
        SearchForRange sfr = new SearchForRange();
//        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int[] nums = new int[]{1, 2, 3, 3, 3, 3, 4, 5, 9};
//        int target = 8;
        int target = 3;
        int[] ret = sfr.searchRange(nums, target);
        Arrays.stream(ret).forEach(System.out::println);
    }

    public int[] searchRange(int[] nums, int target) {
        // use binary range search
        int left = 0;
        int right = nums.length - 1;
        int[] ret = new int[]{-1, -1};
        binarySearch(nums, target, left, right, ret);

        return ret;
    }

    private void binarySearch(int[] nums, int target, int left, int right, int[] ret) {
        if (left > right) {
            return;
        }

        int mid = (left + right) / 2;
        int midVal = nums[mid];

        if (target > midVal) {
            binarySearch(nums, target, mid + 1, right, ret);
        } else if (target < midVal) {
            binarySearch(nums, target, left, mid - 1, ret);
        } else if (target == midVal) {
            List<Integer> idxs = new ArrayList<>();
            // iterate left side
            for (int i = mid - 1; i >= 0; i--) {
                if (nums[i] == target) {
                    idxs.add(i);
                }
            }

            // add midval idx
            idxs.add(mid);

            // iterate right side
            for (int i = mid + 1; i < nums.length; i++) {
                if (nums[i] == target) {
                    idxs.add(i);
                }
            }
            Collections.sort(idxs); // NOTE
            ret[0] = idxs.get(0);
            ret[1] = idxs.get(idxs.size() - 1);
        }
    }
}
