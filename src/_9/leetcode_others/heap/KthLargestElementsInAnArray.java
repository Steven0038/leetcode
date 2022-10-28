package _9.leetcode_others.heap;

import java.util.Arrays;
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
        Arrays.sort(nums);
        int nth = nums[nums.length - k];
        System.out.printf("the %sth largest element in array is: %s", k, nth);
    }

    /**
     * - 1. 維護一個遞減的 Heap(Priority Queue), 讓 heap 中的元素不超過 k 個
     * - 2. 只加進比 heap 的 root 大 的元素
     * - 3. 同時滿足 1&2 二點, heap 的 root 永遠是當前所有 heap 元素中第 k 大的元素(而且是動態的)
     * - 所有 array 都做完的話, heap 的 root 就是 array 中第 K 大的元素
     *
     * @param nums input array
     * @param k    kth largest
     * @return the kth largest element in input array
     */
    private Integer findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            if (heap.isEmpty()) heap.offer(num); // TODO

            if (!heap.isEmpty() && (heap.size() < k || num >= heap.peek())) {
                heap.offer(num);
            }

            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.peek();
    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int n : nums) {
            q.offer(n);
            if (q.size() > k) q.poll();
        }
        return q.poll();
    }
}
