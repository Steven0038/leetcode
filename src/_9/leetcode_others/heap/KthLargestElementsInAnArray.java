package _9.leetcode_others.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 * <p>
 * medium, premium
 * <p>
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * You must solve it in O(n) time complexity.
 * <p>
 * Example:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * <p>
 * Output: 4
 */
public class KthLargestElementsInAnArray {
    public static void main(String[] args) {
        KthLargestElementsInAnArray kle = new KthLargestElementsInAnArray();
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(Arrays.toString(nums));
        System.out.printf("the %sth largest element in array is: %s", k, kle.findKthLargest(nums, k));
        System.out.println();
        System.out.printf("the %sth largest element in array is: %s", k, kle.findKthLargest2(nums, k));
        System.out.println();

        // easiest solution
        Arrays.sort(nums); // equal to quicksort, O(n * log n), but wost case is O(n^2)
        int nth = nums[nums.length - k];
        System.out.printf("the %sth largest element in array is: %s", k, nth);
    }

    /**
     * Solution1 30%, 偷吃步
     *
     * @param nums input array
     * @param k    kth largest
     * @return the kth largest element in input array
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 0; i--) {
            k = k - 1;
            if (k == 0) return nums[i];
        }

        return 0;
    }

    /**
     * Solution1 13%, 最大堆 heap 直觀作法
     */
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 降序
        for (int n : nums) {
            maxHeap.add(n);
        }

        int count = k;
        int ret = 0;
        while (count != 0 && !maxHeap.isEmpty()) {
            ret = maxHeap.poll();
            count = count - 1;
        }

        return ret;
    }

    /**
     * 32%
     * - 1. 維護一個遞減的 minHeap(Priority Queue), 讓 heap 中的元素不超過 k 個
     * 在上述代码中，我们使用了Java的PriorityQueue来实现最小堆。我们遍历数组中的每个元素，并将其插入到最小堆中。如果堆的大小超过了 k，则将堆顶元素（最小值）弹出。最终，堆顶元素即为第 k 个最大的元素。
     * <p>
     * 时间复杂度分析：插入和弹出操作的时间复杂度为O(logk)，在最坏情况下，需要处理数组中的所有元素，因此时间复杂度为O(nlogk)，其中 n 是数组的长度。
     * <p>
     * 空间复杂度分析：最小堆中最多包含 k 个元素，因此空间复杂度为O(k)。
     * <p>
     * 通过使用最小堆，我们可以高效地找出数组中第 k 个最大的元素。
     * <p>
     * O(n * log n)
     */
    public int findKthLargest2(int[] nums, int k) {
        if (nums.length == 0) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 預設是最小堆

        for (int num : nums) {
            minHeap.offer(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        if (minHeap.isEmpty()) return 0;

        return minHeap.peek();
    }
}
