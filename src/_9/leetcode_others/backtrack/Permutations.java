package _9.leetcode_others.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 46. Permutations, medium, companies
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Constraints:
 * All the integers of nums are unique.
 */
public class Permutations {
    public static void main(String[] args) {
        Permutations pm = new Permutations();
        int[] nums = new int[]{1, 2, 3};
        System.out.println(pm.permute(nums));
        /**
         *                         [1,2,3]
         *                  /         |            \
         *          [1,2,3]        [2,1,3]          [3,2,1]   L1: index 0, n   possibilities
         *                          /    \                    L2: index 1, n-1 possibilities
         *                     [2,1,3]  [2,3,1]               L3: index 2, n-2 possibilities
         *                        |        |
         *                     [2,1,3]  [2,3,1]
         */
    }

    /**
     * 返回所有給定的 array 元素的, 所有可能排序組合的 list
     * @param nums input array
     * @return List of Permutations
     */
    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, nums, 0);

        return res;
    }

    // top-down DFS
    private void dfs(List<List<Integer>> res, int[] nums, int index) {
        if (index > nums.length - 1) {
            // make a copy
//            List<Integer> ans = new ArrayList<>(); for (int i = 0; i < nums.length; i++) ans.add(nums[i]);
            List<Integer> ans = Arrays.stream(nums).boxed().collect(Collectors.toList());
            res.add(ans);
            return;
        }
        // back-tack dfs
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i); // 1. backtrack 交換 index,
            dfs(res, nums, index + 1); // 2. 進行搜索
            swap(nums, index, i); // 換回來,因為須回朔不讓其他分支的狀態干擾之後的搜索分支
        }
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
