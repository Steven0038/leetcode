package _5.leetcode_sort_and_search;

/**
 * Best O(n), AVG(n^2), WOST(n^2)
 *
 * @author steven
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};

        Is ob = new Is();
        ob.sort(arr);

        printArray(arr);
    }

    /* A utility function to print array of size n */
    static void printArray(int[] arr) {
        int n = arr.length;
        for (int j : arr) System.out.print(j + " ");

        System.out.println();
    }

}

class Is {

    /* Function to sort array using insertion sort */
    public void sort(int[] arr) {
        // int arr[] = { 12, 11, 13, 5, 6 };
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];//一進入 i=1,從11開始比,比到arr[4],也就是6
            int j = i - 1;

            /*
             * Move elements of arr[0..i-1], that are greater than key, to one position
             * ahead of their current position
             */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // 只要發現 key 前一個元素比key大, 就移到原本 key 的位置(j+1), j 就是 key的位置 -1(i-1)
                j--; // while 控制條件, j 往前移 繼續向前檢查
            }
            arr[j + 1] = key; // 直到 j 移到 array 最前面或者發現沒有比 key 大了, 就把 key 放回去(如果到底j會是-1)
        }
    }
}
