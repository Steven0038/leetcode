package _6.leetcode_dynamic_programming;

/**
 * 70. Climbing Stairs, easy
 * <p>
 * you are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Example:
 * <p>
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 45
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        int n = 3;
        System.out.println(cs.climbStairs(n));
    }

    Integer[] memo;

    public int climbStairs(int n) {
        /* Solution2: DP dfs
            https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
        */
        memo = new Integer[n + 1];

        dfs(n);

        return memo[n];
    }

    private int dfs(int n) {
        if (n <= 1) {
            memo[n] = 1;
            return 1;
        }

        if (memo[n] != null) return memo[n];

        int ret = dfs(n - 1) + dfs(n - 2);

        memo[n] = ret;

        return ret;
    }
}
