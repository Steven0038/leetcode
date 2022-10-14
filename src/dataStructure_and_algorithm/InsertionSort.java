package dataStructure_and_algorithm;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        InsertionSort is = new InsertionSort();
        is.insertionSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int curVal = arr[i];
            int insertIndex = i - 1;

            while (insertIndex >= 0 && curVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = curVal;
        }
    }
}
