package _1.leetcode_array;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * <p>
 * medium, companies
 * <p>
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * <p>
 * A subarray is a ""contiguous non-empty"" sequence of elements within an array.
 *
 * <p>
 * Example :
 * <p>
 * Input: nums = [1,2,3], k = 3
 * <p>
 * Output: 2
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        SubarraySumEqualsK sse = new SubarraySumEqualsK();
        int[] nums = {1, 2, 3}; // [1,2] [3], output count is 2
        int k = 3;
        System.out.printf("total number of subarray: %s", sse.subarraySum(nums, k));
        System.out.println();
        nums = new int[]{1, 2, 3, 4, 5}; // [2,3,4], [4,5], output count is 2
        k = 9;
        System.out.printf("total number of subarray: %s", sse.subarraySum(nums, k));
    }

    /**
     * 如果 sum[i] 表示 加到第 ith 的和,
     * sum[i] - sum[j] = k, 則代表 index i 到 j 間的元素和為 k
     * <p>
     * 例子: nums = [1, 2, 3, 4, 5], k = 9
     * 那 hashtable 裡面會存累加的值的數目，
     * 1: 1
     * 3: 1
     * 6: 1
     * 10: 1
     * 15: 1
     * 這個陣列和是 15。
     * 這裡可以很快看出來有個 continuous subarray 符合。
     * 方法是 15–9 = 6，6 出現一次，因此有個 continuous subarray 符合。
     * 因為 6 是 [1, 2, 3, 4, 5] 中的 [1, 2, 3]，而 15 是 [1, 2, 3, 4, 5]
     * 15–9 = 6 的意義其實是兩個 array 的差，也就是 [4, 5]，也是一個 continuous subarray 並且是我們要的 sum。
     *
     * @param nums input array
     * @param k    target summary number of subarray of array nums
     * @return count of valid subarray
     */
    private int subarraySum(int[] nums, int k) {
        int res = 0;
        int sum = 0;
        //<unique的前綴和, 該前綴和出現了幾次>
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // NOTE: 需要初始化,因為可能有負數的存在,使得和為0

        // x, x, x, x, x, x, x, x   : sum
        // ----------------------
        // x, x, x, x, x, x, x, k   : sum - k
        // -------------------
        for (int num : nums) {
            sum += num; // 當前的前綴和
            if (map.containsKey(sum - k)) { // 核心算法: 如果(當前加總 sum - 目標和 k)的值, 存在於元素前綴和 map.key,
                                           // 代表當前加總至少有一個 subarray 的和為 k
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }
}
