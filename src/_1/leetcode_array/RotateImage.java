package _1.leetcode_array;

import java.util.Arrays;

/**
 * 48. Rotate Image, medium
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 */
public class RotateImage {

    public static void main(String[] args) {
        RotateImage ri = new RotateImage();
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ri.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    private void rotate(int[][] matrix) {
        /*1. 先交换行（先转置再交换列也行，但交换列对java不友好）
          2. 再转置
        */
        if (matrix.length <= 1) return;
        int n = matrix.length;
        /*  1,2,3
            4,5,6
            7,8,9
        */
        // 對邊 row 交換
        for (int i = 0; i < n / 2; i++) { // 交換 Array, /2 是因為對邊交換只需做到中點就可以
            int[] temp = matrix[i];
            matrix[i] = matrix[n - 1 - i]; // 長度轉 index(-1) 再從最後的對邊(n) 開始交換 (-0)
            matrix[n - 1 - i] = temp;
        }
        /*  7,8,9
            4,5,6
            1,2,3
        */
        // 對角 元素 轉置
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) { // note! 轉置二維陣列元素
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        /*  7,4,1
            8,5,2
            9,6,3
        */
    }
}
