package _1.leetcode_array;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();

        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(ts.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 用於去重
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            // 轉化成Two Sum 問題
            int target = -nums[i]; // 3sum 必須為0
            int left = i + 1; // index
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> triplets = Arrays.asList(nums[i], nums[left], nums[right]);
                    result.add(triplets);
                    left++;
                    right--;

                    // 用於去重
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;

                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}
