package _4.leetcode_trees;

import java.util.*;

/**
 * 103. Binary Tree Zigzag Level Order Traversal, medium
 * <p>
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeZigzagLevelOrderTraversal btl = new BinaryTreeZigzagLevelOrderTraversal();
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(9);
        tree.right = new TreeNode(20);
        tree.right.left = new TreeNode(15);
        tree.right.right = new TreeNode(7);

        btl.zigzagLevelOrder(tree);
    }

    // ref. https://www.jiuzhang.com/solution/binary-tree-zigzag-level-order-traversal/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
         List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        // 正反向標誌
        boolean isForward = true;
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = q.poll();
                subList.add(treeNode.val);
                if (treeNode.left != null) {
                    q.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    q.offer(treeNode.right);
                }
            }
            // 根據標誌來加入當前遍歷數值的方向
            if (!isForward) {
                Collections.reverse(subList); // 翻轉
            }
            ans.add(subList);
            // 更新方向
            isForward = !isForward;
        }
        return ans;
    }
}
