package _5.leetcode_sort_and_search;

import java.util.*;

/**
 * 347. Top K Frequent Elements, medium
 * <p>
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 */
public class TopK_FrequentElements {
    public static void main(String[] args) {
        TopK_FrequentElements tke = new TopK_FrequentElements();
        int[] nums = new int[] {1,1,1,2,2,3};
        int k = 2;
        int[] ret = tke.topKFrequent(nums, k);
        for(int i : ret) {
            System.out.println(i);
        }
    }

    /**
     * get the most K frequent count elements in input array
     * @param nums input array
     * @param k number of element to look up
     * @return int array
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> ckMap = new HashMap<>();
        for(int n : nums) {
            ckMap.put(n, ckMap.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()); //
//        for(Map.Entry<Integer, Integer> entry : ckMap.entrySet()) {
//            maxHeap.add(entry);
//        }
        maxHeap.addAll(ckMap.entrySet());

        List<Integer> res = new ArrayList<>();
        while(res.size() < k) {
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            assert entry != null;
            res.add(entry.getKey());
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
