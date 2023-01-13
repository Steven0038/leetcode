package _9.leetcode_others;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses va = new ValidParentheses();
        System.out.println(va.isValid("{{}[][[[]]]}"));
        System.out.println(va.isValid("{{}}"));
    }

    private boolean isValid(String s) {
        Map<Character, Character> lMap = new HashMap<>();
        lMap.put('(', ')');
        lMap.put('{', '}');
        lMap.put('[', ']');

        Map<Character, Character> rMap = new HashMap<>();
        rMap.put(')', '(');
        rMap.put('}', '{');
        rMap.put(']', '[');

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            Character curr = s.charAt(i);

            // 左括號就入棧
            if(lMap.containsKey(curr)) {
                stack.add(curr);
            } else {
                if (stack.isEmpty())  return false;
                // 右括號就 pop stack 檢查
                if(!rMap.containsKey(curr) || curr != lMap.get(stack.peek())){
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();

//         Stack<Character> stack = new Stack<>();
//         for (int i = 0; i < s.length(); i++) {
//             char ch = s.charAt(i);
//             // 如果是左括號，則入棧
//             if (ch == '(' || ch == '[' || ch == '{') {
//                 stack.push(ch);
//             } else { // 如果是右括號，則比較其與棧頂元素是否配對
//                 if (stack.isEmpty())  return false;

//                 if (ch == ')' && stack.peek() != '(') return false;

//                 if (ch == ']' && stack.peek() != '[') return false;

//                 if (ch == '}' && stack.peek() != '{') return false;

//                 stack.pop();
//             }
//         }
//         // 最後棧為空則表示完全匹配完畢
//         return stack.isEmpty();
    }
}
