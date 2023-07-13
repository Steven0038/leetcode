package _2.leetcode_string;

/**
 * 1616. Split Two Strings to Make Palindrome, medium
 *
 * You are given two strings a and b of the same length. Choose an index and split both strings at the same index, splitting a into two strings: aprefix and asuffix where a = aprefix + asuffix, and splitting b into two strings: bprefix and bsuffix where b = bprefix + bsuffix. Check if aprefix + bsuffix or bprefix + asuffix forms a palindrome.
 * <p>
 * When you split a string s into sprefix and ssuffix, either ssuffix or sprefix is allowed to be empty. For example, if s = "abc", then "" + "abc", "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.
 * <p>
 * Return true if it is possible to form a palindrome string, otherwise return false.
 * <p>
 * Notice that x + y denotes the concatenation of strings x and y.
 * <p>
 * Example 3:
 * <p>
 * Input: a = "ulacfd", b = "jizalu"
 * Output: true
 * Explaination: Split them at index 3:
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * Then, aprefix + bsuffix = "ula" + "alu" = "ulaalu", which is a palindrome.
 */
public class SplitTwoStringsToMakePalindrome {
    public static void main(String[] args) {
//        String aStr = "abcdef"; // ab xx ba
        String aStr = "zwxxba"; // ab xx ba
//        String bStr = "xxxcba";
//        String bStr = "zwxxba";
        String bStr = "abcdef";

        boolean ret1 = checkPalindromeFormation(aStr, bStr); // ret index should be 3
        boolean ret2 = checkPalindromeFormation(bStr, aStr); // ret index should be 3
        System.out.println(ret1 || ret2);
    }

    /**
     * two str input, have the same length, try to find out the position,
     * let a concat b could be palindrome
     * input a: "abc de", => "abc"   index point = 2
     * input b: "xxc ba"  => "ba"
     * =>        "abcba"  => return true
     */
    private static boolean checkPalindromeFormation(String aStr, String bStr) {
        // 1. find out the first not palindrome index point,
        // 2. checkout the substring between aStr(start, end + 1) || bStr(start, end + 1) is also palindrom
        int start = 0;
        int end = bStr.length() - 1;
        while (start < end) {
            if (aStr.charAt(start) != bStr.charAt(end)) {
                return isPalindrome(aStr.substring(start, end + 1))
                        || isPalindrome(bStr.substring(start, end + 1));
            }
            start++;
            end--;
        }

        return true;
//        // iterate aStr/bStr and concat both of them to tmpStr
//        for (int i = 0; i < aStr.length(); i++) {
//            String tmp = "";
//            tmp = tmp + aStr.substring(0, i);
//            tmp = tmp + bStr.substring(i, aStr.length());
//            // judge tmpStr is palindrom or not
//            if (isPalindrom(tmp))
//                return true;
//        }
//
//        return false;
    }

    private static boolean isPalindrome(String tmpString) {
        int start = 0;
        int end = tmpString.length() - 1;
        while (start < end) {
            if (tmpString.charAt(start) != tmpString.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
