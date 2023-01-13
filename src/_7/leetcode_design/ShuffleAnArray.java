package _7.leetcode_design;

import java.util.Arrays;
import java.util.Random;

public class ShuffleAnArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Solution obj = new Solution(nums);
        // shuffle
        int[] param_2 = obj.shuffle();
        System.out.println(Arrays.toString(param_2));

        // reset
        int[] param_1 = obj.reset();
        System.out.println(Arrays.toString(param_1));

        // shuffle
        int[] param_3 = obj.shuffle();
        System.out.println(Arrays.toString(param_3));
    }
}

class Solution {

    int[] oriArr = null;
    int[] shuffleArr = null;
    Random random = null;

    public Solution(int[] nums) {
        oriArr = nums;
        shuffleArr = Arrays.copyOf(nums, nums.length);
        random = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        shuffleArr = Arrays.copyOf(oriArr, oriArr.length);
        return shuffleArr;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = 0; i < shuffleArr.length; i++) { // n = 10
            // int x = random.nextInt(shuffleArr.length - i);
            // int randIndex = x + i;
//            int randIndex = random.nextInt(oriArr.length); // 隨機產生一個 random index
            int randIndex = random.nextInt(oriArr.length); // 隨機產生一個 random index

            // switch
            int tmp = shuffleArr[randIndex];
            shuffleArr[randIndex] = shuffleArr[i];
            shuffleArr[i] = tmp; // 將隨機產生的 random index 的元素與 i 的元素交換
        }

        return shuffleArr;
    }
}
