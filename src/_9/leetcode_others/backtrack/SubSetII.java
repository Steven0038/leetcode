package _9.leetcode_others.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II, medium
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 */
public class SubSetII {
    public static void main(String[] args) {
        SubSetII sb = new SubSetII();
        int[] nums = new int[]{1, 2, 2};

        sb.subsetsWithDup(nums);
    }

    /**
     * 帶重複元素的子集
     *
     * @see Subsets
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subs = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();

        subsetsWithDupHelper(nums, 0, sub, subs);

        return subs;
    }

    private void subsetsWithDupHelper(int[] nums, int pos, List<Integer> sub, List<List<Integer>> subs) {
        subs.add(new ArrayList<>(sub));

        for (int i  = pos; i < nums.length; i++) {
            if (i != pos && nums[i] == nums[i - 1]) {
                continue;
            }

            sub.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1, sub, subs);
            sub.remove(sub.size() - 1);
        }
    }
}
