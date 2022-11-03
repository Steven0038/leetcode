package _4.leetcode_trees;

/**
 * 124. Binary Tree Maximum Path Sum
 * <p>
 * hard, companies
 * <p>
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 * <p>
 * A node can only appear in the sequence "at most once". Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * <p>
 * Input: root = [-10,9,20,null,null,15,7]
 * <p>
 * Output: 42
 * <p>
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 */
public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        BinaryTreeMaximumPathSum tree = new BinaryTreeMaximumPathSum();
        /**
         *              -10
         *              / \
         *             9   20
         *                / \
         *               15  7
         */
        TreeNode root;
        root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(tree.maxPathSum(root));
    }

    int max = Integer.MIN_VALUE;

    private int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    /**
     * bottom up DFS
     * @param node
     * @return 下至上回傳值, 用來計算最大路徑
     * @see SumRootToLeafNumber
     */
    public int dfs(TreeNode node) {
        if (node == null) return 0;

        // 只能走人字形 path, 除了 root, 不能走二個左右節點,否則就不是單邊人字形路徑(意即子節點都是回傳最大的單邊)
        int left = dfs(node.left);
        int right = dfs(node.right);
        left = left < 0 ? 0 : left; // 如果一邊的值是負值,就不要走,直接取0
        right = right < 0 ? 0 : right;
        max = Math.max(max, left + right + node.val); // 最大路徑值必須用 global val 動態取值

        return Math.max(node.val + left, node.val + right); // 一次只能回傳一邊
    }
}
