package _6.leetcode_dynamic_programming;

/**
 * 1143. Longest Common Subsequence, medium, companies
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * <p>
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * <p>
 * Example 1:
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * <p>
 * Output: 3
 * <p>
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        LongestCommonSubsequence lc = new LongestCommonSubsequence();
        String text1 = "abcde", text2 = "ace";
        System.out.println(lc.longestCommonSubsequence(text1, text2));

        /**
         * "子問題分割形式為: 二個字串的最後一個 char 是否相等"
         *
         * 假設長度 4 跟 長度 3 的字串做比較
         *                               (4, 3)
         *                              /      \
         * 最後一個字串                  =       !=
         *                           /       /   \
         * 各自刪去最後一個char的情況 #(3,2)  (4,2) (3,3)
         *                                  |
         *                                 !=
         *                                / \
         *                         #(3,2)   (4,1)
         * # 重複子問題
         *
         * "base case 為: 比較到最後一個 char 是空字串"
         * "子母狀態變化二個: 2D DP, i j 為二個比較字串的各自最後一個 char index"
         * "遞推關係為: 最後一個 char 相等(i-1 AND j-1, count++ 收縮子問題), 或不相等(i-1 OR j-1 收縮子問題)"
         */
    }

    Integer[][] memo;

    /**
     * 比較給定的二個字串的最大公共子序列長度 (可以跳 char, 但一定要順序)
     * @param text1 given string1
     * @param text2 given string2
     * @return the length of the longest common subsequence
     */
    private int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        memo = new Integer[m][n];

        return dfs(text1, text2, m - 1, n - 1); // transfer to index
    }

    private int dfs(String t1, String t2, int i, int j) {
        if (i < 0 || j < 0) // base case: out of bound -> 空字串
            return 0;

        if (memo[i][j] != null) return memo[i][j];

        int res = 0;
        if (t1.charAt(i) == t2.charAt(j)) {
            res = dfs(t1, t2, i - 1, j - 1) + 1; // 最後一個 char 一樣, 代表至少有一個最長公共子序列
        } else {
            res = Math.max(dfs(t1, t2, i - 1, j), dfs(t1, t2, i, j - 1));
        }

        return memo[i][j] = res;
    }
}
