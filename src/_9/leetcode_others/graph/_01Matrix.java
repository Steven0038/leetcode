package _9.leetcode_others.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 542. 01 Matrix, medium, companies
 * <p>
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * <p>
 * Input: mat =
 * [[0,0,0],
 * [0,1,0],
 * [1,1,1]]
 * <p>
 * Output:
 * [[0,0,0],
 * [0,1,0],
 * [1,2,1]]
 */
public class _01Matrix {
    public static void main(String[] args) {
        _01Matrix m = new _01Matrix();
        int[][] mat = new int[3][3];
        mat[1][1] = 1;
        mat[2][0] = 1;
        mat[2][1] = 1;
        mat[2][2] = 1;

        System.out.println(Arrays.deepToString(mat));
        System.out.println(Arrays.deepToString(m.updateMatrix(mat)));
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}}; // 上下左右

    /**
     * Time: O(mn), Space: O(mn)
     *
     * @param mat input matrix
     * @return distance of the nearest 0 matrix
     */
    private int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] res = new int[m][n];

        // 將所有數值為零的節點標記為訪問過, 並放入queue之後作為檢查 starting point

        boolean[][] visited = new boolean[m][n]; // 訪問過的節點
        Queue<int[]> queue = new LinkedList<>();// i j
        for (int i = 0; i < m; i++) { // 將所有的 0 節點作為初始 starting point
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // 逆向思維, 對於所有的0, 利用 bfs 填充到每一個1 的距離
        // 遍歷 queue 裡的 0 節點, 搜尋上下左右不是 0 的節點, 如果發現就把其離 0 節點的層數放入回傳 matrix
        int cost = 0; // level 層數
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                if (mat[x][y] == 1) {
                    res[x][y] = cost; // 1 走到最近的0 需要 cost 步
                }

                // 查看上下左右是否有未訪問的鄰居節點
                for (int[] dir : dirs) {
                    int xP = x + dir[0], yP = y + dir[1];
                    if (xP >= 0 && xP < m && yP >= 0 && yP < n && !visited[xP][yP]) {
                        queue.offer(new int[]{xP, yP}); // 加入其所有的鄰居節點 (未出界且未訪問過)
                        visited[xP][yP] = true;
                    }
                }
            }
            cost++;
        }

        return res;
    }
}
