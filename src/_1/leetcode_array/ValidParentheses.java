package _1.leetcode_array;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {

    public static void main(String arg[]) {

        String s = "{[]}";
        ValidParentheses vp = new ValidParentheses();
        System.out.print(vp.isValid(s));
    }

    /*
     * Topic:
     * 給定一個帶括號的字串，判斷是否形狀左右對稱，不同形狀的括號可以內含再一起，但一定要對稱
     * Solution:
     * 建立一個括號對稱的 Map 以及 stack, 字串轉 charArray 後，iterate 每個 char,
     * 如果是右括號就放入 stack, 如果是左括號就 pop stack 確認是否為對應的右括號(並且 stack 不為空)，
     * 這樣一來如果最後 stack 是空的，代表給定的字串中的括號左右對稱。
     *
     */
    private boolean isValid(String s) {
        Map<Character, Character> braceMap = new HashMap<>();
        braceMap.put('}', '{');
        braceMap.put(')', '(');
        braceMap.put(']', '[');
        Stack<Character> charStack = new Stack<>();

        for (Character c : s.toCharArray()) {
            if (braceMap.containsValue(c)) {
                charStack.push(c);
            } else if (braceMap.containsKey(c)){
                if (charStack.isEmpty() || charStack.pop() != braceMap.get(c)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return charStack.isEmpty();
    }


}
