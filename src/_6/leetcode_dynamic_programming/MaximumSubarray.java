package _6.leetcode_dynamic_programming;

/**
 * 53. Maximum Subarray, medium
 * <p>
 * Given an integer array nums, find the
 * subarray
 * with the largest sum, and return its sum.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        MaximumSubarray ms = new MaximumSubarray();
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = new int[]{1};
        System.out.println(ms.maxSubArray(nums));
        System.out.println(ms.maxSubArray2(nums));
    }

    /**Solution1, DP */
    public int maxSubArray(int[] nums) {

        // int result = nums[0]; // 到目前為止的最大子陣列和 (1~n-1)
        // int curr = nums[0]; // 到現在為止"包含當前這個元素"的最大子陣列和 (1~n)

        // for(int i = 1; i < nums.length; i++) { //NOTE: 動態規劃題目, 迴圈從舉例i完後開始
        //     curr += nums[i];
        //     // if (curr < 0 || nums[i] > curr)
        //     if (nums[i] > curr)
        //         curr = nums[i];
        //     if (result < curr)
        //         result = curr;
        // }

        // return result;

        /**
         - array has n elements
         - max_so_far is the maximum sum of a subarray that ends at index n-2

         maximumSubArraySum = max_so_far + arr[n-1]
         maximumSubArraySum[n-2] = max_so_far[n-3] + arr[n-2]
         maximumSubArraySum[i] = maximumSubArraySum[i-1] + arr[i]
         maximumSubArraySum[i] = Max(arr[i], maximumSubArraySum[i-1] + arr[i])
         **/
        int size = nums.length;
        // int start = 0;
        // int end = 0;

        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < size; i++) { //NOTE: 動態規劃題目, 迴圈從舉例i完後開始
            if (nums[i] > maxEndingHere + nums[i]) { // 如果是負數還不如不加
                // start = i;
                maxEndingHere = nums[i];
            } else
                maxEndingHere = maxEndingHere + nums[i];

            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                // end = i;
            }
        }
        // System.out.print("Found Maximum Subarray between " + Math.min(start, end) + " and " + end);

        return maxSoFar;
    }

    /**Solution2, DP DFS, OUT OF MEMORY!!!*/
    int max = Integer.MIN_VALUE;
    Integer[][] memo;
    public int maxSubArray2(int[] nums) {

        int n = nums.length;
        memo = new Integer[n + 1][n + 1];
        int start = 0;
        int end = n - 1;

        if(nums.length == 1) return nums[0];

        dfs(nums, start, end);

        return max;
    }

    private int dfs(int[] nums, int start, int end) {
        // base condition:  head > tail
        if(start >= end) {
            return nums[start];
        }

        if(memo[start][end] != null) return memo[start][end];

        // sub-problem divide: kick head or kick tail or kick both
        int ret = 0;
        int kickHead = dfs(nums, start + 1, end);
        int now = sumRange(nums, start, end);
        int kickTail = dfs(nums, start, end - 1);
        int kickBoth = dfs(nums, start + 1, end - 1);

        int i = Math.max(kickHead, now);
        int j = Math.max(kickTail, kickBoth);
        ret = Math.max(i, j);

        if(ret >= max) {
            max = ret;
        }

        memo[start][end] = ret;

        return ret;
    }

    private int sumRange(int[] nums, int start, int end) {
        int sum = 0;
        for(int i = start; i <= end; i++) {
            sum += nums[i];
        }

        return sum;
    }
}
