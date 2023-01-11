package _6.leetcode_dynamic_programming;

/**
 * 198. House Robber, medium
 * <p>
 * You are a professional robber planning to rob houses along a street.
 * <p>
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobber {
    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(hr.rob(nums));
        System.out.println(hr.rob2(nums));
    }

    /**
     * Solution1: DP dfs + memorization
     */
    Integer[] memo;

    public int rob(int[] nums) {
        // base case: 偷到最後一間
        // sub-problem division: 偷 n - 1 或不偷 n - 1 的 max

        int n = nums.length;
        memo = new Integer[n + 1];

        return dfs(nums, 0);
    }

    private int dfs(int[] nums, int n) {
        // base case: 偷到最後一間
        if (n > nums.length - 1) {
            return 0;
        }

        if (memo[n] != null) return memo[n];

        // 偷 n 或不偷 n
        int res = Math.max(nums[n] + dfs(nums, n + 2), dfs(nums, n + 1));
        memo[n] = res;

        return res;
    }

    /**
     * Solution2: DP
     */
    public int rob2(int[] nums) {
        /**
         選擇要偷第i間的話，意味著我們前面不能偷第i-1間。
         這種狀況下，我們可以將rob[i]的值拆開來算：

         rob[i] = rob[i-2] + num[i] //偷i
         rob[i] = rob[i-1]          //不偷i
         rob[i] = max(rob[i-1], rob[i-2] + nums[i]) //歸納

         rob[0] = nums[0] (第一間不用考慮前面的影響)
         rob[1] = max(nums[0], nums[1]) (兩間選一間偷)
         **/

        int n = nums.length;

        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] rob = new int[n];
        rob[0] = nums[0];
        rob[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            rob[i] = Math.max(rob[i - 1], rob[i - 2] + nums[i]);
        }

        return rob[n - 1];
    }
}
