package _6.leetcode_dynamic_programming;

/**
 * 5. Longest Palindromic Substring, medium
 * <p>
 * Given a string s, return the longest
 * palindromic substring in s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring ls = new LongestPalindromicSubstring();
        String s = "babad";
        System.out.println(ls.longestPalindrome(s));
    }

    Boolean[][] memo;
    String lps = "";

    private String longestPalindrome(String s) {
        int n = s.length();
        memo = new Boolean[n][n];
        dfs(s, 0, n - 1);
        return lps;
    }

    // 2D DP dfs
    private boolean dfs(String s, int i, int j) {
        if (i >= j) {
            if (lps.length() < j - i + 1) {
                lps = s.substring(i, j + 1);
            }
            return true;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean res = false;
        if (s.charAt(i) == s.charAt(j) && dfs(s, i + 1, j - 1)) {
            res = true;
            if (lps.length() < j - i + 1) {
                lps = s.substring(i, j + 1);
            }
        } else {
            dfs(s, i + 1, j);
            dfs(s, i, j - 1);
        }

        return memo[i][j] = res;
    }
}
