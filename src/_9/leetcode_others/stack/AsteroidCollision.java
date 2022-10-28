package _9.leetcode_others.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 735. Asteroid Collision, premium, medium
 * <p>
 * O(N) time complexity
 * <p>
 * We are given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents its direction
 * (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * <p>
 * Example 1:
 * <p>
 * Input: asteroids = [10,2,-5]
 * <p>
 * Output: [10]
 * <p>
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 */
public class AsteroidCollision {

    public static void main(String[] args) {
        AsteroidCollision ac = new AsteroidCollision();
//        int[] asteroids = {5, 10, -5};
//        int[] asteroids = {10, 2, -5};
        int[] asteroids = {11, 8, 2, -5, -8, 3}; // -> [11,3]
        System.out.println(Arrays.toString(ac.asteroidCollision(asteroids)));
    }

    private int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int ast : asteroids) {
            // positive meaning right, negative meaning left
            if (ast > 0) {
                stack.push(ast);
            } else {
                // 反向比大小相撞
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -ast) { // NOTE: 同一個元素只會出入棧一次,所以一樣是 O(N)
                    stack.pop();
                }

                if (!stack.isEmpty() && stack.peek() == -ast) { // 反向一樣大,互相毀滅 (不 Push 新元素到 stack 裡)
                    stack.pop();
                } else if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(ast); // 活到最後加入 stack
                }
            }
        }

        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }

        return res;
    }
}
