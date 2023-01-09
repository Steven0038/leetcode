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
        cs.climbStairs(n);
    }

    private int climbStairs(int n) {
        /* Solution2: DP dfs
            https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
        */
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = -1;
        }
        fib(n, dp);
        return dp[n];
    }

    private int fib(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;

        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = fib(n - 1, dp) + fib(n - 2, dp);
        return dp[n];
    }
}
