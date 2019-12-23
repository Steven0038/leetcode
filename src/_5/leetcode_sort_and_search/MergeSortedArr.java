package _5.leetcode_sort_and_search;

import java.util.Arrays;

public class MergeSortedArr {

	/**
	 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
	 * one sorted array.
	 * 
	 * Note:
	 * 
	 * The number of elements initialized in nums1 and nums2 are m and n
	 * respectively. You may assume that nums1 has enough space (size that is
	 * greater or equal to m + n) to hold additional elements from nums2. Example:
	 * 
	 * Input: nums1 = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6], n = 3
	 * 
	 * Output: [1,2,2,3,5,6]
	 * 
	 * @author steven
	 *
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		int nums1[] = { 1, 2, 3, 0, 0, 0 };
		int m = 3;
		int nums2[] = { 2, 5, 6 };
		int n = 3;

		s.merge(nums1, m, nums2, n);

		for (int i : nums1) {
			System.out.print(i);
		}

	}

}

class Solution {
	/* Merge Sort Solution */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int[] tmp = new int[m + n];

		int index1 = 0;
		int index2 = 0;
		int indexTmp = 0;

		// add nums1 and nums2 elements to Tmp order by number
		while (index1 < m && index2 < n) {
			if (nums1[index1] < nums2[index2]) {
				tmp[indexTmp] = nums1[index1];
				indexTmp++;
				index1++;
			} else {
				tmp[indexTmp] = nums2[index2];
				indexTmp++;
				index2++;
			}
		}

		// add the remain element in nums1 to tmp
		while (index1 < m) {
			tmp[indexTmp] = nums1[index1];
			indexTmp++;
			index1++;
		}

		// add the remain element in nums2 to tmp
		while (index2 < n) {
			tmp[indexTmp] = nums2[index2];
			indexTmp++;
			index2++;
		}
		
		for (int i = 0; i < tmp.length; i++) {
			nums1[i] = tmp[i];
		}

	}

    /*API Solution
    public void merge(int[] nums1, int m, int[] nums2, int n) {
		for(int i = 0; i < n; i++)
			nums1[m + i] = nums2[i];
		Arrays.sort(nums1);
	}
    */
    
    /*Insertion Sort Solution
    public void merge(int[] nums1, int m, int[] nums2, int n) {
       // merge the two arrays
        for (int i = 0; i < n; i++){
           nums1[m + i] = nums2[i];
       }
        // sort the merged array [1 2 3 2 5 6]
        for (int i = 1 ; i < nums1.length; i++){
            int key = nums1[i];
            int j = i - 1;
            
            //rearrange the elements if larger than key
            while (j >=0 && nums1[j] > key){
                nums1[j+1] = nums1[j];
                j--;
            }
            nums1[j+1] = key;
        }
    
    }
    */

}
