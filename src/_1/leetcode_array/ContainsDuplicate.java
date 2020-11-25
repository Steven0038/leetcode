package _1.leetcode_array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates.
 *
 * Your function should return true if any value appears at least twice in the
 * array, and it should return false if every element is distinct.
 *
 * Example 1:
 *
 * Input: [1,2,3,1] Output: true
 *
 * @author steven
 *
 */
public class ContainsDuplicate {

	public static void main(String[] args) {
		int[] nums = {1,2,3,1};

		ContainsDuplicate c = new ContainsDuplicate();
		System.out.print(c.containsDuplicate(nums));
	}

	public boolean containsDuplicate(int[] nums) {

		Set<Integer> s = new HashSet<Integer>();

		for (int num : nums) {
			if (!s.add(num)) {
				return true;
			}
		}

		return false;

//    	Map<Integer, Integer> m = new HashMap<Integer, Integer>();
//
//    	for (int i : nums) {
//    		if (m.containsKey(i)) {
//    			int value = (int) m.get(i);
//    			value++;
//    			m.put(i, value);
//    		} else {
//    			m.put(i, 1);
//    		}
//    	}
//
//    	for (int k : m.keySet()) {
//    		if (m.get(k) != null && m.get(k) > 1) {
//    			return true;
//    		}
//    	}
//
//    	return false;
	}

}
