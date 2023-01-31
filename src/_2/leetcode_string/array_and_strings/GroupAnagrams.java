package _2.leetcode_string.array_and_strings;

import java.util.*;

/**
 * 49. Group Anagrams, medium
 * <p>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groupedAnagrams = ga.groupAnagrams(strs);
        System.out.println(groupedAnagrams);
    }

    /**
     * 給定一個陣列含不同變位詞字串，將其中相同的變位詞分組並回傳,
     * 首先把變位詞的字元排序，這樣同一組變位詞都可以統一到同一個形式。這就可以作為Hash-map的key，
     * 然後，我們value用一個list來儲存不同的變位詞形式。這樣一邊迴圈做統計，然後把Hash-map的內容輸出即可
     *
     * @param strs given string array
     * @return grouped anagrams
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        // step1: to char array and sort
        for (String s : strs) {
            String sorted = sortStr(s);
            // step2: make a map<sorted str, oristr> and put
            List<String> ls = map.getOrDefault(sorted, new ArrayList<>());
            ls.add(s);
            map.put(sorted, ls);
        }

        // step3: return all map's value by given pattern
        for (String k : map.keySet()) {
            List<String> group = map.get(k);
            ret.add(group);
        }

        return ret;
    }

    private String sortStr(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        return new String(chars);
    }
}
