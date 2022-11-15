package _9.leetcode_others.graph;

/**
 * 200. Number of Islands, medium, companies
 * <p>
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example:
 * <p>
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * <p>
 * Output: 3
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        NumberOfIslands noi = new NumberOfIslands();
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};

        System.out.println(noi.numIslands(grid));
    }

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 上下左右

    /**
     * 遍歷 matrix 圖, 遇到是 1 的元素就當成起始節點就 count++ 島數, 並進行 bfs 將其相連的 1 節點消除,
     * <p>
     * 最後就可以得到不相連的 1 構成的島的數量 count
     * <p>
     * time: O(m*n)
     *
     * @param grid input graph
     * @return island count
     */
    private int numIslands(char[][] grid) {
        int count = 0;
        // 遍歷所有節點, 對是1的節點進行 bfs
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j); // dfs recursive 通常需要新開 method
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0'; // pre-order 直接將1改成0, 代表訪問過(取代使用 set 紀錄)
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') {
                continue;
            }
            dfs(grid, x, y);
        }
    }

}