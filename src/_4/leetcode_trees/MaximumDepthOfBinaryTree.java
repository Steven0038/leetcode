package _4.leetcode_trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. Maximum Depth of Binary Tree, easy
 * <p>
 * Given the root of a binary tree, return its maximum depth.
 * <p>
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 */
public class MaximumDepthOfBinaryTree {
    TreeNode root;

    /* Driver program to test above functions */
    public static void main(String[] args) {
        MaximumDepthOfBinaryTree tree = new MaximumDepthOfBinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        System.out.println("[recursive] Height of tree is : " + tree.maxDepthRecursive(tree.root));
        System.out.println("[iterate] Height of tree is : " + tree.maxDepthIterative(tree.root));
    }

    // BFS
    private int maxDepthIterative(TreeNode root) {
        if (root == null) return 0;

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            depth++; // NOTE
        }

        return depth;
    }

    // DFS
    public int maxDepthRecursive(TreeNode node) {
        if (node == null) return 0;

        /* compute the depth of each subtree */
        int lDepth = maxDepthRecursive(node.left);
        int rDepth = maxDepthRecursive(node.right);

        /* use the larger one */
        return Math.max(lDepth, rDepth) + 1;
    }

}