package _6.leetcode_dynamic_programming;

/**
 * Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 * <p>
 * The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.
 * <p>
 * Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.
 * <p>
 * Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.
 * <p>
 * Example 1:
 * <p>
 * Input: piles = [5,3,4,5]
 * <p>
 * Output: true
 * <p>
 * Explanation:
 * Alice starts first, and can only take the first 5 or the last 5.
 * Say she takes the first 5, so that the row becomes [3, 4, 5].
 * If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
 * If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alice, so we return true.
 */
public class StoneGame {
    public static void main(String[] args) {
        StoneGame sg = new StoneGame();
        int[] piles = new int[]{5, 3, 4, 5};
        System.out.println(sg.stoneGame(piles));

        /**
         * "子問題分割形式為: Alice 先拿頭或者先拿尾"
         *
         * 偶數堆, Alice 先拿, 代表 Bob 永遠是拿最後一堆, 所以可以用 (當前堆數量 - 上一層所能拿到的最大堆) 推斷最後是否 Alice 贏
         *                          [5,3,4,5] Alice
         *                          i       j
         *                          /       \
         *                     [3,4,5]      [5,3,4]  Bob
         *                     /     \      /     \
         *                   i+1      j    i      j-1
         *   State:(i, j) maximum relative score we can earn with subarray of stones [i..j]
         *   ex: dfs([5,3,4,5]) - dfs([3,4,5]) > 0 說明 Alex 拿了這層之後肯定會贏  = 0 則為平手  < 0  就輸了
         *
         *                          [5,3,4,5] (0,3) Alice
         *                          /              \
         *              [5,3,4](0,2)                [3,4,5](1,3) Bob
         *             /            \               /           \
         *       [5,3](0,1)       #[3,4](1,2)     #[3,4](1,2)  [4,5](2,3) Alice
         *       /        \        /        \                    /      \
         * [5](0.0)  @[3](1,1)  @[3](1,1) $[4](2,2)         $[4](2,2)   [5](3,3) Bob
         *
         * "base case 為: 拿到最後一堆(i > j)或者是空石頭堆"
         * "子母狀態變化二個: 2D DP, i j 為拿頭或者拿尾的 index"
         * "遞推關係為: 取頭(i-1) 或 取尾(j-1) 與當前堆相減的最大值"
         */
    }

    Integer[][] memo;

    /**
     * 2D DP dfs
     * @param piles given stone piles
     * @return true if the first taker Alice won at any case
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        //紀錄 n         拿頭  拿尾
        memo = new Integer[n][n]; // 每個指針有 n 個位置
        dfs(piles, 0, n - 1); // transfer to index

        return memo[0][n - 1] > 0; // 拿到最後一個(dfs 最高層問題), 相減值 > 0, 代表 Alice 贏
    }

    private int dfs(int[] piles, int i, int j) {
        if (i > j) return 0; // 空 array, 代表誰都拿不到, 大家都 0 分.  平手

        if (i == j)
            return piles[i]; // base case: break down 到單一元素的值

        if (memo[i][j] != null) return memo[i][j];

        int res = Math.max( // 層數相減值 > 0 說明 Alex 拿了這層之後肯定會贏 (<0代表當前層所能組合拿到的最大值,沒有比上一層的最大值大)
                piles[i] - dfs(piles, i + 1, j), // 取頭, 子問題就變成 i + 1
                piles[j] - dfs(piles, i, j - 1)); // 取尾, 子問題就變成 j - 1


        return memo[i][j] = res;
    }
}
