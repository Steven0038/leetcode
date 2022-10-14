package dataStructure_and_algorithm;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        System.out.println("原序列為：" + Arrays.toString(arr));
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("合併排序後序列為：" + Arrays.toString(arr));
    }

    // divide and conquer
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左遞迴進行分解
            mergeSort(arr, left, mid, temp);
            // 向右遞迴進行分解
            mergeSort(arr, mid + 1, right, temp);
            // 合併
            merge(arr, left, mid, right, temp);
        }
    }

    // merge
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i, 左邊有序序列的初始索引
        int j = mid + 1; // 初始化j, 右邊有序序列的初始索引
        int t = 0; // 指向temp陣列的當前索引

        // (一) 左右子序列比大小, 將小的放到temp陣列
        while (i <= mid && j <= right) { // 左右子序列都還沒遍歷到最後時
            if (arr[i] <= arr[j]) { // 若左子序列值小於等於右子序列值，則將之放到 temp 陣列中
                temp[t] = arr[i];
                t += 1; // temp陣列索引後移
                i += 1; // 遍歷下一個
            } else { // 右子序列值小於等於左子序列值
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // (二) 把有剩餘數據的子序列依次全部放入temp
        while (i <= mid) { // 左子序列有剩餘的元素
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) { // 右子序列有剩餘的元素
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // (三) 將 tmep 陣列的元素 copy 到 arr , 並不是每次都 copy 所有
        t = 0;
        int tempLeft = left; // 用於暫時遍歷 temp 陣列
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }

    }

//    public static void main(String[] args) {
//        int[] array = {12, 11, 13, 5, 6};
//        MergeSort ms = new MergeSort();
//        ms.mergeSort(array);
//        Arrays.stream(array).forEach(System.out::println);
//    }
//
//    private void mergeSort(int[] array) {
//        int[] helper = copy(array);
//        mergeSort(array, helper, 0, array.length - 1);
//    }
//
//    public void mergeSort(int[] array, int[] helper, int left, int right) {
//        if (right - left < 1) return;
//        int mid = left + (right - left) / 2;
//        mergeSort(array, helper, left, mid);
//        mergeSort(array, helper, mid + 1, right);
//        merge(array, helper, left, mid, right);
//    }
//
//    public void merge(int[] array, int[] helper, int left, int mid, int right) {
//        for (int i = left; i <= right; i++) {
//            helper[i] = array[i];
//        }
//        int leftStart = left;
//        int rightStart = mid + 1;
//        for (int i = left; i <= right; i++) {
//            if (leftStart > mid) {
//                array[i] = helper[rightStart++];
//            } else if (rightStart > right) {
//                array[i] = helper[leftStart++];
//            } else if (helper[leftStart] < helper[rightStart]) {
//                array[i] = helper[leftStart++];
//            } else {
//                array[i] = helper[rightStart++];
//            }
//        }
//    }
//
//    public int[] copy(int[] array) {
//        int[] newArray = new int[array.length];
//        for (int i = 0; i < array.length; i++) {
//            newArray[i] = array[i];
//        }
//        return newArray;
//    }


}
