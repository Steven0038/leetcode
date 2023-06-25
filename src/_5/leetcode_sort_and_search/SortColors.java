package _5.leetcode_sort_and_search;

import java.util.Arrays;

/**
 * 75. Sort Colors, medium
 * Topic: 給定一個包含0,1,2的陣列,將其作 in-place 小至大排序
 * Solution1: Counting sort O(n + k)/O(k) 因為本題元素只有0,1,2 所以適合用 counting sort
 * Solution2: Quick sort O(nlogn)/O(logn)
 * hard point: Quick sort 的原理及邊界條件很容易忘記
 * Note:
 * Input:  0,1,2的陣列
 * Output: 已排序 0,1,2的陣列
 */
public class SortColors {
    public static void main(String[] args) {
        SortColors sc = new SortColors();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sc.sortColors(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    /**
     * Solution1 counting sort
     */
    public void sortColors(int[] nums) {
        // count the digits in nums and replace
        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;

        for (int n : nums) {
            if (n == 0) zeroCount++;
            if (n == 1) oneCount++;
            if (n == 2) twoCount++;
        }

        // replace nums with counts, NOTE: 可以用雙指針只遍歷一次,遇到0換到前面,遇到2換到後面
        int pointer = 0;
        while (zeroCount > 0) {
            nums[pointer] = 0;
            pointer++;
            zeroCount--;
        }
        while (oneCount > 0) {
            nums[pointer] = 1;
            pointer++;
            oneCount--;
        }
        while (twoCount > 0) {
            nums[pointer] = 2;
            pointer++;
            twoCount--;
        }
    }


    /**
     * Solution2 quick sort
     */
    public void sortColors0(int[] nums) {
        // quick sort
        int leftEdge = 0;
        int rightEdge = nums.length - 1;

        quickSort(nums, leftEdge, rightEdge); // NOTE:
    }

    private void quickSort(int[] nums, int leftEdge, int rightEdge) {
        if (leftEdge >= rightEdge) return; // NOTE:

        int pivot = partition(nums, leftEdge, rightEdge);
        quickSort(nums, leftEdge, pivot - 1);
        quickSort(nums, pivot + 1, rightEdge);
    }

    // the purpose is keep left elements less than pivot and right larger than pivot
    private int partition(int[] nums, int leftEdge, int rightEdge) {
        int pivot = nums[rightEdge];
        int left = leftEdge;
        int right = rightEdge - 1;

        while (true) {
            // select left number larger than pivot
            while (left < rightEdge && nums[left] <= pivot) { // NOTE:
                left++; // skip those no need to move
            }

            // select right number less than pivot
            while (right >= leftEdge && nums[right] > pivot) { // NOTE:
                right--;
            }

            // beak condition
            if (left > right)
                break;

            // swap
            swap(nums, left, right);
        }

        // from break condition and swap the last larger num and pivot
        swap(nums, left, rightEdge);

        // return pivot and the nums should all left elements less than pivot
        // and all right elements larger than pivot
        return left; // NOTE:
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
