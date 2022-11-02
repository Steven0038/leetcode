package _4.leetcode_trees;

/**
 * 129. Sum Root to Leaf Numbers, medium, companies
 * <p>
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * <p>
 * Each root-to-leaf path in the tree represents a number.
 * <p>
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * A leaf node is a node with no children.
 * <p>
 * Input: root = [4,9,0,5,1]
 * <p>
 * Output: 1026
 * <p>
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class SumRootToLeafNumber {
    public static void main(String[] args) {

        SumRootToLeafNumber tree = new SumRootToLeafNumber();

        /**
         *               4
         *              / \
         *             9   0
         *            / \   (40)
         *           5   1
         *        (495)  (491)
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        System.out.println(tree.sumNumbers(root)); // 495 + 491 + 40 = 1026
    }

    int sum = 0;

    private int sumNumbers(TreeNode root) {

        if (root == null) return 0;

        dfs(root, 0);
        return sum;
    }

    // top down DFS
    private void dfs(TreeNode node, int num) {
        num = num * 10 + node.val;

        if (node.left == null && node.right == null) {
            sum += num;
            return;
        }

        if (node.left != null) {
            dfs(node.left, num);
        }
        if (node.right != null) {
            dfs(node.right, num);
        }
    }
}
