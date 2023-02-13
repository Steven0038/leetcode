package _9.leetcode_others.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * general backtrack template of subsets/permutations/combinations
 * <p>
 * refer from http://pglost.github.io/blog-theme/leetcode/2015/08/10/leetcode-subset.html
 */
public class SubSetCombinationPermutation {
    public static void main(String[] args) {
        SubSetCombinationPermutation scp = new SubSetCombinationPermutation();
        int[] nums = new int[]{1, 2, 3};
        int[] nums2 = new int[]{1, 2, 2};

        System.out.println(scp.subsets(nums));
        System.out.println(scp.subsetsWithDup(nums2));
        System.out.println(scp.permute(nums));
        System.out.println(scp.permuteUnique(nums2));
    }


    /**
     * 1-1. 子集: 給定一個不重複的整數數組, 返回其所有的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums); // 題目中要求子集中的元素為非降序排列
        List<List<Integer>> subs = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();

        subsetsHelper(nums, 0, sub, subs);

        return subs;
    }

    // 從 nums 的第 i 個元素開始, 計算包含 sub 的所有子集, 並保存到 subs 中
    private void subsetsHelper(int[] nums, int pos, List<Integer> sub, List<List<Integer>> subs) {
        subs.add(new ArrayList<>(sub));
        for (int i = pos; i < nums.length; i++) { // 實際上是解決子問題, 從 i 開始的每一個元素保存到 sub 中
            sub.add(nums[i]);
            subsetsHelper(nums, i + 1, sub, subs);
            sub.remove(sub.size() - 1);
        }
    }

    /**
     * 1-2. 子集: 給定一個可能有重複數字的數組, 返回其所有的子集
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

        for (int i = pos; i < nums.length; i++) {
            if (i != pos && nums[i] == nums[i - 1]) {
                continue;
            }

            sub.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1, sub, subs);
            sub.remove(sub.size() - 1);
        }
    }

    /**
     * 2-1 排列: 全排列 , 給定一個數組, 返回其所有可能的排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        permuteHelper(nums, list, result);

        return result;
    }

    private void permuteHelper(int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int num : nums) {
            if (list.contains(num)) continue;

            list.add(num);
            permuteHelper(nums, list, result);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 2-2 排列: 帶重複元素的排列 , 給定一個具有重複數字的數組, 返回其所有可能的排列
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);

        permuteUniqueHelper(nums, list, result, visited);

        return result;
    }

    private void permuteUniqueHelper(int[] nums, List<Integer> list, List<List<Integer>> result, boolean[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            list.add(nums[i]);
            permuteUniqueHelper(nums, list, result, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }


    /**
     * 3-1 組合:
     */
    public List<List<Integer>> combine(int n, int k) { // Cn 取 k
        List<List<Integer>> results = new ArrayList<>(); // 结果
        int nums[] = new int[n]; // 数组存储 1-n
        boolean visited[] = new boolean[n]; // 用于判断是否使用
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        dfs(-1, k, results, visited, n);
        return results;
    }

    private void dfs(int index, int count, List<List<Integer>> results, boolean visited[], int n) {
        if (count == 0) { // k个元素满
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    list.add(i + 1);
                }
            }
            results.add(list);

            return;
        }

        for (int i = index + 1; i < n; i++) { // 只能在index后遍历 回溯向下
            visited[i] = true;
            dfs(i, count - 1, results, visited, n);
            visited[i] = false; //还原
        }
    }


}
