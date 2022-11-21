package _5.leetcode_sort_and_search;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets, medium, companies
 * <p>
 * Given an integer array nums of unique elements, return all possible
 * subsets
 * (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * <p>
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class Subsets {
    public static void main(String[] args) {
        Subsets s = new Subsets();
        int[] nums = new int[]{1, 2, 3};
        System.out.println(s.subsets(nums));

        /**
         *                                   [1,3,4]
         *                                     []
         *                            /                   \
         *  1                       []                   [1]
         *                         /  \              /         \
         *  3                    []   [3]         [1]          [1,3]
         *                      / \   /  \       /   \         /   \
         *  4                 [] [4] [3] [3,4] [1] [1,4]  [1,3]  [1,3,4]
         */
    }

    /**
     * 獲取題目提供的 array 所有元素的子集, Time O(2^n)
     * <p>
     * 可以當作一個 b tree, 每個 arr 元素在樹的每一層左右節點建立都有二種選擇, 要他或不要他去建立子集,
     * 最後做 top down DFS 就可以獲得所有元素的子集
     *
     * @param nums input array
     * @return all subsets of input array
     */
    private List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, nums, new ArrayList<>(), 0);

        return res;
    }

    /**
     * dfs recursive, 可以當作一個 DP, 用子問題紀錄解決母問題
     * @param res 題目要求的 subsets
     * @param nums input elements array
     * @param cur 當前 top down dfs 的 list
     * @param index 當前 dfs 層數 所需判斷是否要加入的 nums array 元素 index
     */
    private void dfs(List<List<Integer>> res, int[] nums, List<Integer> cur, int index) {
        if (index >= nums.length) { // base case check
            res.add(new ArrayList<>(cur)); // 最底層要做一個 copy, 讓 reference 不一樣
            return;
        }
        // solve sub-problem
        cur.add(nums[index]); // 要該元素
        dfs(res, nums, cur, index + 1);

        cur.remove(cur.size() - 1); // 不要該元素(上面添加過了 nums[index], 要刪掉)
        dfs(res, nums, cur, index + 1);
    }
}
