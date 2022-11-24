package _6.leetcode_dynamic_programming;

/**
 * 91. Decode Ways, medium, companies
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * <p>
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "226"
 * <p>
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * <p>
 * Example 3:
 * <p>
 * Input: s = "06"
 * <p>
 * Output: 0
 * <p>
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 */
public class DecodeWays {
    public static void main(String[] args) {

        /**                           226
         *                          /     \
         *                    2 26(Z)    22 6(F)
         *                     |        /    \
         *                   2(B)    2 2(B)   22(V)
         *                            |
         *                          2(B)
         */

        /**                             n
         *                          /       \
         *                      n-2         n-1
         *                     /   |       /   \
         *                  N/A   n-3     n-3  n-2
         *
         *           NOTE: n-4 is N/A
         */

        DecodeWays dw = new DecodeWays();
        String s = "226";
        System.out.println(dw.numDecodings(s));
    }

    Integer[] memo;

    /**
     * 給定一個數字構成的字串, 返回該字串可以被 decode 成 A~Z 規則 char 的數目
     *
     * @param s given string
     * @return number of chars could s to be decoding
     */
    private int numDecodings(String s) {
        int n = s.length();
        memo = new Integer[n + 1]; // 0 is empty string base case

        return dfs(s, n);
    }

    /**
     * 1D array DP
     * [n-1][x]  (x < 10)
     * [n-2][xx] (10 =< xx =<26)
     *
     * @param s input string
     * @param n s length
     * @return number of chars could s to be decoding of this sub-question
     */
    private int dfs(String s, int n) {
        if (n == 0) return 1; // empty string

        if (n == 1) return s.charAt(0) == '0' ? 0 : 1; // 0 無法被 decode

        if (memo[n] != null) return memo[n];

        int res = 0;
        char x = s.charAt(n - 1), y = s.charAt(n - 2); // NOTE: n 是長度不是 index, 所以倒數第一個 char 是倒數 n-1 index
        // 一個問題只會向二個子問題要答案, 所以不用 for loop
        if (x != '0') {
            res += dfs(s, n - 1); // x is valid and not 0
        }

        int yx = (y - '0') * 10 + (x - '0'); // convert yx char to int
        if (yx >= 10 && yx <= 26) {
            res += dfs(s, n - 2); // xx is valid (<=26)
        }

        memo[n] = res;

        return res;
    }
}
