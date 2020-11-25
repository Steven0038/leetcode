package _1.leetcode_array;


public class RemoveDuplicateFromSortedArray {

    /**
     * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * Given nums = [0,0,1,1,1,2,2,3,3,4],
     * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
     * It doesn't matter what values are set beyond the returned length.
     *
     * @param args
     */
    public static void main(String[] args) {
        RemoveDuplicateFromSortedArray rd = new RemoveDuplicateFromSortedArray();
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//		int[] nums = {1,1,2};
        rd.removeDuplicates(nums);

        for (int i : nums) {
            System.out.print(i);
        }
    }

    /**
     *  Remove Duplicates from Sorted Array
     *  Topic: 在不改變原 array 的情況下去除重複元素(inplace)
     *  Solution: 快慢指針, 快指針 iterate 過程中如果跟慢指針的值不一樣, 就 assing 快指針的值給慢指針
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        //Given nums = [0,0,1,1,1,2,2,3,3,4], return 5, nums should be [0,1,2,3,4,*****]
        int slow = 0;
        int fast = 1;

        for (; fast < nums.length; fast++){
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }

        return slow + 1; // return value is the length of new num should be, but it's in-place
    }
}
