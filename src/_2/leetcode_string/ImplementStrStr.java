package _2.leetcode_string;

/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "sadbutsad", needle = "sad"
 * <p>
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 */
public class ImplementStrStr {
    public static void main(String[] args) {
        ImplementStrStr iss = new ImplementStrStr();
//        System.out.println(iss.strstr("hello", "ll")); // 2
        System.out.println(iss.strstr("a", "a")); // 0
    }

    private int strstr(String haystack, String needle) {
        /**Solution1: substring **/
        int hayL = haystack.length();
        int ndlL = needle.length();
        if(hayL < ndlL) {
            return -1;
        } else if (ndlL == 0) {
            return 0;
        }

        for(int i = 0; i <= hayL - ndlL; i++) { // NOTE: <=, test case: "a", "a"
            // haystack = "hello", needle = "ll"
            //               2 4
            if(haystack.substring(i, i + ndlL).equals(needle)) return i;
        }

        return -1;
    }
}
