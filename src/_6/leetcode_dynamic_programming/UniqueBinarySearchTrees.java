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

        /**
         *                                [1,2,3]
         *              /                    |                \
         *  root1     [][2,3]       root2 [1][3]       root3 [1,2][]
         *             / \                 / \               / \
         *         [3]    [2]            [1]  [3]          [2] [1]
         *
         * 可以發現中間那棵樹, ex:2-1-3, 跟 右邊子樹 3-1-2 是重複的,
         * 所以此題就是求 n = node 大小的, 左右子樹數量 (中間重複可以忽略), 而中間子樹為 unique 的左右子樹所組成
         */

        /**
         * 用區間大小看子問題
         *                                  [3]
         *              /                    |                \
         *  root1     [0,2]         root2 [1][1]       root3 [2,0]
         *             / \                                 / \
         *         [1]    [1]                            [1] [1]
         */

        /**
         * 用區間大小看子問題
         *                                  [n]
         *              /                    |                 \
         *            [0,n-1]         [n/2-1][n/2]            [n-1,0]
         *             / \                  /\               /        \
         *     [0,n-2]    [n-2,0]    [0,n/2-1] [n/2-1,0]   [0,n-2] [n-2,0]
         */

        // [i - 1][i][n - i] 用區間大小看子問題
        int res = 0;
        for (int i = 1; i <= n; i++) { // 以 i 為 root, 向ˊ左右二邊子樹要答案
            int left = dfs(i - 1);
            int right = dfs(n - i);
            res += left * right; // 左右 unique 子樹的數量"相乘"(兩兩組合), 就會是 i 當前樹的數量
        }

        memo[n] = res;

        return res;
    }

    // 正向填表: dfs 的 flow 反過來, 從底層子問題開始往大計算, 最終計算到頂層問題
    private int numTrees2(int n) {
        /**
         * 首先是1為根節點的狀況：
         * 左邊沒有比其小的節點，故只有一種可能；
         * 右邊有2跟3的組合，相當於n=2的時候的狀況(2種)。
         * (2跟3等價於1跟2兩個節點會有的組合)
         *
         * 再來是2為根節點的狀況：
         * 左邊只有1種可能(1這個節點)；
         * 右邊也只有1種可能(3這個節點)。
         *
         * 最後是3為根節點的狀況：
         * 左邊是1跟2的組合(2種)，
         * 右邊只有1種可能。
         *
         * 如果我們將n的組合命名為函式f(n)，
         * 那麼顯然f(0)=1(只有一種可能)，f(1)=1。
         *
         * 計算n=3的組合算法為：
         * f(0) * f(2) + f(1) * f(1) + f(2) * f(0)
         * f(0)=1
         * f(1)=1
         * f(3)= f(0) * f(2) + f(1) * f(1) + f(2) * f(0)
         * f(n) = f(0) * f(n-1) + f(1) * f(n-2) + … + f(n-1) * f(0)
         */
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
