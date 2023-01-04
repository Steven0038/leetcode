package _9.leetcode_others.backtrack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 47. Permutations II, medium, companies
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 */
public class PermutationsII {
    public static void main(String[] args) {
        PermutationsII pm = new PermutationsII();
        int[] nums = new int[]{1, 1, 2};
        System.out.println(pm.permuteUnique(nums));
    }

    /**
     * 有重複序列的全排列
     * 返回所有給定的 array 元素的, 所有可能排序組合的 list (元素可以重複)
     *
     * @param nums input array
     * @return List of Permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
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
        Set<Integer> visited = new HashSet<>();
        // back-tack dfs
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i); // 1. backtrack 交換 index,
            if (visited.add(nums[index])) {
                dfs(res, nums, index + 1); // 2. 進行搜索
            }
            swap(nums, index, i); // 換回來,因為須回朔不讓其他分支的狀態干擾之後的搜索分支
        }
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
