package _2.leetcode_string;

/*
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"dog", "racecar", "car"};
        Prefix px = new Prefix();
        px.longestCommonPrefix(strs);

    }

}

/*
 * Longest Common Prefix (not fixed!!!) Topic: 給定一個 string array, 找出共同最大子字串
 * Solution: 1.暴力解: 雙重迴圈從個別第一個 char 檢查到最短的字串長度為止
 * Hard point:
 */
class Prefix {

    public String longestCommonPrefix(String[] strs) {

        // find the shortest length of string
        int shortestLength = 0;
        for (String s : strs) {
            if (s.length() < shortestLength) {
                shortestLength = s.length();
            }
        }

        // brute force find each one
        int pointer = 0;
        char pointChar = strs[0].charAt(pointer);
        char[] commonPrefixChars = new char[shortestLength];
        // int commmonPrefixIndex = 0;

        for (int i = 0; i < shortestLength - 1; i++) { // iterate between charAt
            // char pointChar = strs[0].charAt(i);
            for (int j = 0; j < strs.length - j; i++) { // iterate between Strings
                if (strs[j].charAt(i) != strs[0].charAt(i)) { // always use the first str as checker
                    return String.valueOf(commonPrefixChars);
                } else {
                    commonPrefixChars[i] = strs[j].charAt(i);
                }
            }
        }

        return "";
        // charArr.forEach(s-> System.out.println(s));

    }

}
