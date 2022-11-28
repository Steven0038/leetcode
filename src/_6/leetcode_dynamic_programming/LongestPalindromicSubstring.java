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
        String s1 = "babad";
        String s2 = "aba";
        String s3 = "abba";
        System.out.println(ls.longestPalindrome(s1));
        System.out.println(ls.longestPalindrome(s2));
        System.out.println(ls.longestPalindrome(s3));
        System.out.println(ls.longestPalindrome2(s3));
    }

    Boolean[][] memo;
    String lps = "";

    // dp
    private String longestPalindrome(String s) {
        int n = s.length();
        memo = new Boolean[n][n];
        dfs(s, 0, n - 1);
        return lps;
    }

    // 2D DP dfs
    private boolean dfs(String s, int i, int j) {
        if (i >= j) {
            if (lps.length() < j - i + 1) { // 當前的子問題長度 > lps
                lps = s.substring(i, j + 1); // 更新 lps
            }
            return true;
        }

        if (memo[i][j] != null) return memo[i][j];

        boolean res = false;
        // 每個長 n 的 string 都有二個 n-1 的 substring
        if (s.charAt(i) == s.charAt(j) && dfs(s, i + 1, j - 1)) { // 較大的字串範圍子問題,包含較小(就不用算else)
            res = true;
            if (lps.length() < j - i + 1) { // 這邊的長度 + 1 是 index -> length 轉換
                lps = s.substring(i, j + 1);
            }
        } else { // 較小範圍的子問題(上面 res = false)
            dfs(s, i + 1, j);
            dfs(s, i, j - 1);
        }

        return memo[i][j] = res;
    }

    String res = "";

    /**
     * 中心擴散法
     * 假設每個 char 都是回文的中心, 然後向外擴增, 找出最長的回文
     *
     * @param s input string
     * @return longest palindrome string in s
     */
    private String longestPalindrome2(String s) {
        // 注意数据范围，这里不需要判断字符串为空的corner case了
        for (int i = 0; i < s.length(); i++) { // iterate each char in s, as a palindrome center
            helper(s, i, i); // 奇數個 char
            helper(s, i, i + 1); // 偶數個 char
        }
        return res;
    }

    private void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > res.length()) {
            res = cur;
        }
    }
}
