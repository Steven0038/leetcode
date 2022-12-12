package dataStructure_and_algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 二分查找: 解決 array 有序區間的 log(n) 算法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7}; // sorted array
        BinarySearch bs = new BinarySearch();
        int target;
        /**
        // ==== iterative solutions ====
        target = 6;
        // 1. 搜尋精確值
        System.out.println(Arrays.toString(arr));
        System.out.printf("搜尋精確值: target %s at index %s", target, bs.searchSpecificNum(arr, target));
        System.out.println();
        // 2. 搜尋模糊值  第一個比 target 小的數
//        bs.searchGreaterMinNum(arr, target);

        // 3. 搜尋模糊值 從頭算起, target 最初出現在 array 的位置
        target = 2;
        arr = new int[]{1, 1, 2, 2, 2, 6, 7};
//        arr = new int[]{1, 1, 1, 2, 2, 6, 7};
//        arr = new int[]{2, 3};
        System.out.println(Arrays.toString(arr));
        System.out.printf("搜尋模糊值: first occurrence of %s at index %s", target, bs.searchFirstOccurrence(arr, target));
        System.out.println();

        // 4. 搜尋模糊值 從頭算起, target 最後出現在 array 的位置
//        arr = new int[]{1, 1, 2, 2, 2, 6, 7};
//        arr = new int[]{1, 1, 2, 2, 5, 6, 7};
        arr = new int[]{2, 3};
        System.out.println(Arrays.toString(arr));
        System.out.printf("搜尋模糊值: last occurrence of %s at index %s", target, bs.searchLastOccurrence(arr, target));
        System.out.println();

        // 5. 搜尋最接近的值
        arr = new int[]{1, 4};
//        arr = new int[]{1, 1};
//        arr = new int[]{3, 4};
        System.out.println(Arrays.toString(arr));
        System.out.printf("搜尋最接近的值: nearest element to target %s at index %s", target, bs.searchNearestOccurrence(arr, target));
        System.out.println();
         **/

        // ==== recursive solutions (萬用, 如果沒要求用 iterative 實現, 學這個就好)====
        target = 1000;
        arr = new int[]{1, 8, 10, 89, 1000, 1000, 1000, 1234};
        System.out.println(Arrays.toString(arr));
        System.out.printf("搜尋所有重複值的位置: target %s at %s", target, searchMultipleOccurrence(arr, 0, arr.length - 1, target));

    }

    private static ArrayList<Integer> searchMultipleOccurrence(int[] arr, int left, int right, int target) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (left > right) { // 代表遞迴整個陣列但是沒有找到
            return new ArrayList<>();
        }

        if (target > midVal) {
            return searchMultipleOccurrence(arr, mid + 1, right, target);
        } else if (target < midVal) {
            return searchMultipleOccurrence(arr, left, right - 1, target);
        } else { // 已找到目標數
            ArrayList<Integer> resIndexes = new ArrayList<>();

            // 向左掃描加入重複的目標數 Index
            int tmpIndex = mid - 1;
            while (tmpIndex > 0 && arr[tmpIndex] == target) {
                resIndexes.add(tmpIndex);
                tmpIndex -= 1;
            }

            // 加入最初找到目標數的 index
            resIndexes.add(mid);

            // 向右掃描
            tmpIndex = mid + 1;
            while (tmpIndex < right && arr[tmpIndex] == target) {
                resIndexes.add(tmpIndex);
                tmpIndex += 1;
            }

            return resIndexes;
        }
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
