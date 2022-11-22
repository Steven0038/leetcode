package _6.leetcode_dynamic_programming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. Word Break, medium companies
 * <p>
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * <p>
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * <p>
 * Constraints:
 * <p>
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class WordBreak {
    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        String s = "applepenapple";
        List<String> wordDict = List.of("apple", "pen");

        System.out.println(wb.wordBreak(s, wordDict));
    }

    Boolean[] memo;

    /**
     * Time: O(n^2)
     * @param s given input string to check
     * @param wordDict list of substring
     * @return if s contains all the substring in wordDict
     */
    private boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        memo = new Boolean[n + 1]; // n + 1 個子問題 (0 ~ n)

        return dfs(s, n, new HashSet<>(wordDict));
    }

    private boolean dfs(String s, int sLen, Set<String> dict) {
        if (sLen == 0) return true; // base case
        if (memo[sLen] != null) { // 已經被計算過,就不重複計算
            return memo[sLen];
        }

        // 當前狀態 [sub-problem j |    x ] (0, i)
        //         子問題(0, j)     (j, n)
        // 如果 x 是 dict 中的一個單詞, 整個問題 [0, i) 就會是 true
        memo[sLen] = false;
        for (int j = 0; j < sLen; j++) {
            boolean right = dict.contains(s.substring(j, sLen)); // 右邊 (j~len-1)
            if (!right) continue;

            boolean left = dfs(s, j, dict); // 左邊子問題 (0~j)
            if (left) { // 只要子問題符合
                memo[sLen] = true; // 整塊當前區域就符合
                break;
            }
        }

        return memo[sLen];
    }
}
