package _6.leetcode_dynamic_programming;

/**
 * 96. Unique Binary Search Trees, medium, companies
 *
 * Given an integer n, return the number of structurally unique BST's (binary search trees)
 * which has exactly n nodes of unique values from 1 to n.
 *
 * Example 1:
 *  1       1              2              3        3
 *   \       \            / \            /        /
 *    3       2          1   3          2        1
 *   /        \                        /         \
 *  2          3                      1           2
 *
 * Input: n = 3
 * Output: 5
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        UniqueBinarySearchTrees ub = new UniqueBinarySearchTrees();
        int n = 3;
        System.out.println(ub.numTrees(n));
        System.out.println(ub.numTrees2(n));
    }

    Integer[] memo;

    /**
     * top-down DP
     *
     * 給定 1~n 的順序數字, 回傳這些數字可以構成的 unique BST 數量
     *
     * @param n given number of nodes
     * @return number of unique BST
     */
    private int numTrees(int n) {
        memo = new Integer[n + 1];
        return dfs(n);
    }

    private int dfs(int n) {
        if (n <= 1) return 1; // base case, 1 的話, 代表只有一個數字只能構成一棵樹

        if (memo[n] != null) { // 計算過就不用再計算
            return memo[n];
        }

        int res = 0;
        for (int i = 1; i <= n; i++) { // 以 i 為 root, 向ˊ左右二邊子樹要答案
            int left = dfs(i - 1);
            int right = dfs(n - i);
            res += left * right; // 左右 unique 子樹的數量相乘(兩兩組合), 就會是 i 當前樹的數量
        }

        memo[n] = res;

        return res;
    }

    // 正向填表: dfs 的 flow 反過來, 從底層子問題開始往大計算, 最終計算到頂層問題
    private int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1; // 先填好 base case
        for (int i = 2; i <= n; i++) { // 1 跟 0 是 base case, 所以從 2 開始
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

}
