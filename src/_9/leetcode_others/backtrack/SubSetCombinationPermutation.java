package _9.leetcode_others.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * general backtrack template of subsets/permutations/combinations
 * <p>
 * refer from
 * 1. http://pglost.github.io/blog-theme/leetcode/2015/08/10/leetcode-subset.html
 * 2. https://segmentfault.com/a/1190000040142137
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

        System.out.println(scp.combine(4, 2));

        /*
         - 全排列 (Permutation)
                - N 個元素取 N 個元素 (所有元素) 的所有排列組合情況
                - 無重複序列排列
                - 有重複序列排列
         - 組合  (Combinations)
                - n 個元素取 m 個元素的所有組合情況 (非排列)
                - 組合只看元素不看順序
         - 子集  (Subsets)
                - 組合的擴展, 不考慮排列順序先後
                - n 個元素的所有子集 (所有可能的組合情況)
                - 重複子集
                - 不重複子集
         */
    }


    /**
     * 1-1. 子集: 給定一個不重複的整數數組, 返回其所有的子集
     * <p>
     * Input: nums = [1,2,3]
     * Output: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
     *
     * @see Subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums); // 題目中要求子集中的元素為非降序排列
        List<List<Integer>> subs = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();

        subsetsHelper(nums, 0, sub, subs);

        return subs;
    }

    // 從 nums 的第 i 個元素開始, 計算包含 sub 的所有子集, 並保存到 subs 中
    private void subsetsHelper(int[] nums, int posIdx, List<Integer> sub, List<List<Integer>> subs) {
        subs.add(new ArrayList<>(sub));
        // 實際上是解決子問題, 從 i 開始的每一個元素保存到 sub 中
        for (int i = posIdx; i < nums.length; i++) { // 組合/子集不看順序, 為了避免重複, 使用下標 posIdx 保存當前位置, 下次只能遍歷後面的數字
            sub.add(nums[i]);
            subsetsHelper(nums, i + 1, sub, subs); // NOTE: postIdx + 1
            sub.remove(sub.size() - 1);
        }
    }

    /**
     * 1-2. 子集: 給定一個可能有重複數字的數組, 返回其所有的子集
     * <p>
     * Input: nums = [1, 2, 2]
     * Output: [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
     *
     * @see SubSetII
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subs = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();

        subsetsWithDupHelper(nums, 0, sub, subs);

        return subs;
    }

    private void subsetsWithDupHelper(int[] nums, int posIdx, List<Integer> sub, List<List<Integer>> subs) {
        subs.add(new ArrayList<>(sub)); // base case

        for (int i = posIdx; i < nums.length; i++) { // iterate
            // continue to manipulate duplicate case
            if (i != posIdx && nums[i] == nums[i - 1]) { // NOTE: i = posIdx 即 case [1, 2, 2]
                continue;
            }

            sub.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1, sub, subs);
            sub.remove(sub.size() - 1);
        }
    }

    /**
     * 2-1 排列: 全排列 , 給定一個數組, 返回其所有可能的排列
     * <p>
     * Input: nums = [1, 2, 3]
     * Output: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
     *
     * @see Permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        Arrays.sort(nums);

        permuteHelper(nums, list, result, visited);

        return result;
    }

    private void permuteHelper(int[] nums, List<Integer> list, List<List<Integer>> result, boolean[] visited) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            list.add(nums[i]);
            visited[i] = true;
            permuteHelper(nums, list, result, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * 2-2 排列: 帶重複元素的排列 , 給定一個具有重複數字的數組, 返回其所有可能的排列
     * <p>
     * Input: nums = [1, 2, 2]
     * Output: [[1, 2, 2], [2, 1, 2], [2, 2, 1]]
     *
     * @see PermutationsII
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
            if (visited[i] || (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]))
                continue;

            visited[i] = true;
            list.add(nums[i]);
            permuteUniqueHelper(nums, list, result, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }


    /**
     * 3-1 組合: 給定二個整數 n 和 k, 返回 1..n 中所有可能的 k 個數的組合
     * <p>
     * Input: n = 4, k = 2
     * Output: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
     */
    public List<List<Integer>> combine(int n, int k) { // Cn 取 k
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[n]; // 用於判斷是否使用
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) { // init nums 1..n
            nums[i] = i + 1;
        }

        dfs(nums, -1, k, results, visited, n);
        return results;
    }

    private void dfs(int[] nums, int idx, int count, List<List<Integer>> results, boolean[] visited, int n) {
        if (count == 0) { // k 個元素滿
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
//                    list.add(i + 1);
                    list.add(nums[i]);
                }
            }
            results.add(list);

            return;
        }

        for (int i = idx + 1; i < n; i++) { // 只能在 index 後遍歷,回朔向下
            visited[i] = true;
            dfs(nums, i, count - 1, results, visited, n);
            visited[i] = false; // 還原 backtrack
        }
    }


}
