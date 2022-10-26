package dataStructure_and_algorithm;

import java.util.Arrays;

/**
 * 二分查找: 解決 array 有序區間的 log(n) 算法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7}; // sorted array
        BinarySearch bs = new BinarySearch();

        int target = 6;
        // 1. 搜尋精確值
        System.out.println(Arrays.toString(arr));
        System.out.printf("搜尋精確值: target %s at index %s", target, bs.searchSpecificNum(arr, target));
        System.out.println();
        // 2. 搜尋模糊值  第一個比 target 小的數
//        bs.searchGreaterMinNum(arr, target);

        // 3. 搜尋模糊值 從頭算起, target 最初出現在 array 的位置
        target = 2;
        arr = new int[]{1, 1, 1, 2, 2, 6, 7};
        System.out.println(Arrays.toString(arr));
        System.out.printf("搜尋模糊值: first target %s at index %s", target, bs.searchFirstOccurrence(arr, target));
        System.out.println();

        // 4. 搜尋模糊值 從頭算起, target 最後出現在 array 的位置
        arr = new int[]{1, 1, 2, 2, 5, 6, 7};
        System.out.println(Arrays.toString(arr));
        System.out.printf("搜尋模糊值: last target %s at index %s", target, bs.searchLastOccurrence(arr, target));
        System.out.println();

        // 5. 搜尋最接近的值
        arr = new int[]{1, 4};
//        arr = new int[]{1, 1};
//        arr = new int[]{3, 4};
        System.out.println(Arrays.toString(arr));
        System.out.printf("搜尋最接近的值: nearest element to target %s at index %s", target, bs.searchNearestOccurrence(arr, target));

    }

    private int searchNearestOccurrence(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < target) {
                l = mid;
            } else {
                r = mid;
            }
        }

        if (arr[r] < target) { // 最大的右邊界值都比 target 小
            return r;
        } else if (arr[l] > target) { // 最小的左邊界值都比 target 大
            return l;
        } else { // target 的值界於左右邊界的值中間
            return target - arr[l] < arr[r] - target ? l : r; // 比較左右的邊界哪個比較接近 target
        }
    }

    private int searchLastOccurrence(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2; // NOTE: 防止偶數個元素的 array (ex: length 2)  死循環
            if (arr[mid] > target) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }

    private int searchFirstOccurrence(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;  // 每次縮減不能排除潛在答案
            }
        }
        return l;
    }

    /**
     * search the index of specific target number in array
     *
     * @param arr    array for search
     * @param target target number
     * @return index of specific number in array
     */
    private int searchSpecificNum(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
//            int mid = (l + r) / 2; // 有可能會超過 Integer.MAX_VALUE 造成 overflow
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

    private void searchGreaterMinNum(int[] arr, int target) {
    }
}
