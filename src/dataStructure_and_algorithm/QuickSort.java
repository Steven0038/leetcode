package dataStructure_and_algorithm;

import java.util.Arrays;

// also called 'partition-exchange sort'
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        QuickSort qs = new QuickSort();
        qs.quickSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int partitionIndex = partition(arr, left, right);
        quickSort(arr, left, partitionIndex - 1);
        quickSort(arr, partitionIndex + 1, right);
    }

    /**
     * 目標是將數組從左到右, 比 pivot 小的排到左邊, 比 pivot 大的排到右邊
     * 實作為建立二個 pointer,使用左右 pointer 與 pivot 比大小, 找出左大於右的元素, 如果左邊元素比右邊大, 就進行交換,
     * 最後再將 pivot 與 最後一個大於 pivot 的元素交換, 達成目標, 並回傳 pivot index
     *
     * @param arr the array to be handled
     * @param leftEdge smaller element index begin
     * @param rightEdge larger element index begin
     * @return the last pivot index
     */
    private int partition(int[] arr, int leftEdge, int rightEdge) {
        int pivot = arr[rightEdge];
        int leftPointer = leftEdge;
        int rightPointer = rightEdge - 1; // NOTE: 不要忘記-1
        while (true) { // 注意等號條件, break 前的動作為, left++=>left=right=>left++=>right--=>left>right=>break
            while (leftPointer < rightEdge && arr[leftPointer] <= pivot) { // 比 pivot 小就不用交換, 換到下一個
                leftPointer++;
            }
            while (rightPointer >= leftEdge && arr[rightPointer] > pivot) { // 比 pivot 大也不用交換, 換到下一個, NOTE: 注意等號條件,不給會有死循環
                rightPointer--;
            }

            if (leftPointer > rightPointer) break;
            swap(arr, leftPointer, rightPointer); // 此時左邊元素一定比 pivot 大, 右邊元素一定比 pivot 小, 對其進行互換
        }
        // Now array[smallerElementIndex-leftPointer] is the first element bigger than pivot (break at line 44)
        swap(arr, leftPointer, rightEdge); // swap pivot(at rightEdge) to the right position

        return leftPointer; // 回傳交換過的 pivot index, 此時左邊元素一定比 pivot 小, 右邊元素一定比 pivot 大
    }

    private void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

}
