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
 *
 * @see Permutations
 */
public class PermutationsII {
    public static void main(String[] args) {
        PermutationsII pm = new PermutationsII();
        int[] nums = new int[]{1, 1, 2};
        System.out.println(pm.permuteUnique(nums));
        System.out.println(pm.permuteUnique2(nums));
    }

    /**
     * 1. 交換法
     * 有重複序列的全排列, 46.34%
     * 返回所有給定的 array 元素的, 所有可能排序組合的 list (元素可以重複)
     * 使用 {@link #swap(int[], int, int)} 交換元素排序達成 backtrack 枚舉
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

    List<List<Integer>> res;
    boolean[] used;

    /**
     * 2. 回朔法 78.3%
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        res = new LinkedList<>();
        used = new boolean[nums.length];
        Arrays.sort(nums);
        List<Integer> tmp = new LinkedList<>();
        helper(nums, tmp);
        return res;
    }

    // bottom up backtrack dfs
    private void helper(int[] nums, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            List<Integer> list = new LinkedList<>(tmp);
            res.add(list);
            return;
        }
//        else {
            for (int idx = 0; idx < nums.length; idx++) {
                // 遇到已经加过的元素就跳过
                if (used[idx]) {
                    continue;
                }

                // 加入该元素后继续搜索
                used[idx] = true;
                tmp.add(nums[idx]);
                helper(nums, tmp);
                tmp.remove(tmp.size() - 1);
                used[idx] = false;
                // 跳过本轮的重复元素，确保每一轮只会加unique的数字
                while (idx < nums.length - 1 && nums[idx] == nums[idx + 1]) {
                    idx++;
                }
            }
//        }
    }
}
