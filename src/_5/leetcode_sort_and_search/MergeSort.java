package _5.leetcode_sort_and_search;

/**
 * WORST(n log n, AVG(n log n), BEST(n log n)
 * @author steven
 *
 */
class MergeSort {
	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m+1..r]
	void merge(int[] arr, int l, int m, int r) {//int arr[] = { 12, 11, 13, 5, 6, 7 };
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m; // 不包含 m

		/* Create temp arrays */
		int[] L = new int[n1];
		int[] R = new int[n2];

		/* Copy data to temp arrays, 用中心點分為左右二個 array*/
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) { //在從二個 array 合併到第三個的過程中, 就有選擇小的先插入的過程, 即為排序
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	void sort(int[] arr, int l, int r) {// initial l=0, r=5 in index
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;
			
			// Sort first and second halves
			//, sort() 到最後為元素等級, 停止條件為 l=r, 之後左右回傳的元素在執行下面的 merge()
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}

	/* A utility function to print array of size n */
	static void printArray(int[] arr) {
		int n = arr.length;
		for (int j : arr) System.out.print(j + " ");
		System.out.println();
	}

	// Driver method
	public static void main(String[] args) {
		int[] arr = { 12, 11, 13, 5, 6, 7 };

		System.out.println("Given Array");
		printArray(arr);

		MergeSort ob = new MergeSort();
		
		ob.sort(arr, 0, arr.length - 1);

		System.out.println("\nSorted array");
		printArray(arr);
	}
}