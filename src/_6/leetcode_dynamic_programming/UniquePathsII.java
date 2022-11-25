package _6.leetcode_dynamic_programming;

/**
 * 63. Unique Path II, medium, companies
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 * <p>
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
 * <p>
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * <p>
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 * <p>
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 */
public class UniquePathsII {
    public static void main(String[] args) {
        UniquePathsII up = new UniquePathsII();
        int[][] obstacleGrid = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};

        /**
         *                        (3,3)         終點
         *                    /          \
         *                 (2,3)        (3,2)   可以從上面,也可以從左邊走過來
         *                 /  \          /   \
         *            (1,3)   (2,2)   (2,2)   (3,1)
         *                     中間有石頭
         */

        System.out.println(up.uniquePathsWithObstacles(obstacleGrid));
    }

    Integer[][] memo;
    int m, n;

    /**
     * 找出從起點 (0,0) 到給定的 matrix 右下角位置的所有可能 unique 路徑數量,
     * matrix 標註值為 1 的表示為石頭, 不能走
     *
     * @param obstacleGrid given matrix
     * @return number of possible routes
     */
    private int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        memo = new Integer[m][n];

        return dfs(obstacleGrid, m - 1, n - 1); // transfer length to index
    }

    // 2D DP
    private int dfs(int[][] matrix, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || matrix[i][j] == 1) {
            return 0; // base case: 出界或遇到石頭
        }
        if (i == 0 && j == 0) return 1; // 只有一格 or break down 到起點
        if (memo[i][j] != null) return memo[i][j];

        int res = 0;
        res += dfs(matrix, i - 1, j); // 左邊的子問題(上邊路徑)
        res += dfs(matrix, i, j - 1); // 右邊的子問題(左邊路徑)

        return memo[i][j] = res;
    }
}
