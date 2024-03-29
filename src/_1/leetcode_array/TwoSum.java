package _1.leetcode_array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 *
 * @author steven
 */
public class TwoSum {

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        int[] nums = {2, 7, 11, 5};
        int target = 9;
        int[] targets = ts.twoSum(nums, target);

        for (int i : targets) {
            System.out.print(i + " ");
        }
    }

    public int[] twoSum(int[] nums, int target) {
        //  num       index
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        // traverse nums and check if target number in map or not
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {// <num, index>
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                return result;
            } else {
                map.put(nums[i], i);
            }
        }

        return result;
    }
	
	/*
	private int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			map.put(i, nums[i]);
		}

		for (int i = 0; i < map.size(); i++) {
			for (int key : map.keySet()) {
				if (map.get(i) + map.get(key) == target && i != key) { // NOTE: 一定要檢查 i != key, 不然會重複
					return new int[] { i, key };
				}
			}
		}
		return new int[0];
	}
	*/

}
