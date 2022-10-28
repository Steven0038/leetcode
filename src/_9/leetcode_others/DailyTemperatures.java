package _9.leetcode_others;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days
 * you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * Example:
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * <p>
 * Leetcode Premium, medium
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        int[] tmpArr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dt.dailyTemperatures(tmpArr)));
    }

    /**
     * 找出傳入的溫度數列中, 每個元素與數列中下一個比較高的溫度元素的距離
     *
     * @param tmpArr 傳入的溫度數列
     * @return array 對應每個溫度數列中元素, 與下一個比較大的元素的距離
     */
    private int[] dailyTemperatures(int[] tmpArr) {
        if (tmpArr == null || tmpArr.length == 0) {
            return tmpArr;
        }

        // 維護一個單調遞減棧(Monotonic decreasing stack)<array index>, O(n), 每個元素都只會被 push 再被 pop 一次
        // 如果出現比棧頂元素大的新元素, 就迴圈把比新元素小的元素位置都pop出來, 當前棧中元素即為比新元素更大的下一個元素位置
        int len = tmpArr.length;
        int[] res = new int[len];
        // <Index of tmp array>
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = len - 1; i >= 0; i--) { // 從後開始遍歷數列
            while (!stack.isEmpty() && tmpArr[i] >= tmpArr[stack.peek()]) {
                stack.pop(); // pop 掉比當前 T[i] 小的 元素 index
            }
//            res[i] = stk.isEmpty() ? 0 : stk.peek() - i;
            if (stack.isEmpty()) {
                res[i] = 0;
            } else {
                res[i] = stack.peek() - i; // 計算棧頂比當前元素大的下一個元素之間的距離,並存入返回值
            }

            stack.push(i); // 放入當前元素 index
        }

        return res;
    }
}
