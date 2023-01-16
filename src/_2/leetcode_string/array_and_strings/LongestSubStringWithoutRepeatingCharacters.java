package _2.leetcode_string.array_and_strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. Longest Substring Without Repeating Characters
 * <p>
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 */
public class LongestSubStringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubStringWithoutRepeatingCharacters ls = new LongestSubStringWithoutRepeatingCharacters();
//        String s = "abcabcbb";
        String s = "abba";
        System.out.println(ls.lengthOfLongestSubstring(s));
        System.out.println(ls.lengthOfLongestSubstring2(s));
    }

    public int lengthOfLongestSubstring(String s) {
        /*Solution1: iterate each char*/
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        char[] chars = s.toCharArray();
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            Set<Character> charSet = new HashSet<>();
            int j = i;
            for (; j < s.length(); j++) {
                if (charSet.contains(chars[j])) break;
                charSet.add(chars[j]);
            }

            max = Math.max(max, j - i); // NOTE: 需要放在外面
        }

        return max;
    }

    /**
     * Solution2: sliding window
     * 窗口代表的就是無重複子字符串，左右邊界分別代表子字符串的兩端；
     * 對於滑動時機，在本題中，我們限定右邊界的滑動爲主滑動，也就是說，右邊界的滑動爲從字符串最左端至最右端；
     * 而對於左邊界，其滑動跟隨右邊界的滑動，每當右邊界發生滑動，那麼判斷新納入字符是已存在當前窗口中，
     * 如不存在，左邊界不動，如存在，則將左邊界滑動至窗口中此已存在字符之後，可看出此操作保證了窗口中一定不存在重複字符；
     * <p>
     * 每當窗口發生變化，更新最大窗口長度，當右邊界滑動至字符串最右端時結束並返回最大長度；
     * <p>
     * 隨着右邊界的滑動，我們使用 map 記錄每個字符最後出現的位置，以便於左邊界的滑動更新
     **/
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        //             left
        Map<Character, Integer> map = new HashMap<>(); // 記錄每個字符最後出現的位置，以便於左邊界的滑動更新
        int max = 0;
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            Character curr = s.charAt(right);
            if (map.containsKey(curr)) { // 字符已在窗口中存在
                left = Math.max(left, map.get(curr) + 1); // NOTE: 需跟 left 取 max, 不然會往左往回走　ex: "abba"
            }

            map.put(curr, right);

            max = Math.max(max, right - left + 1);

            right++;
        }

        return max;
    }
}
