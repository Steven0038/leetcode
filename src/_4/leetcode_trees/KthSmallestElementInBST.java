package _4.leetcode_trees;

import java.util.ArrayList;
import java.util.List;

/**
 * 230. Kth Smallest Element in a BST, medium
 * <p>
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 */
public class KthSmallestElementInBST {
    public static void main(String[] args) {
        KthSmallestElementInBST kt = new KthSmallestElementInBST();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);

        /**
         *                    3
         *                 /    \
         *                1      4
         *                 \
         *                  2
         */

        int k = 1;
        System.out.println(kt.kthSmallest(root, k));
    }

    public int kthSmallest(TreeNode root, int k) {
        // do buttom-up inorder traversal dfs
        List<Integer> ret = dfs(root, new ArrayList<Integer>());

        return ret.get(k - 1);
    }

    private List<Integer> dfs(TreeNode root, List<Integer> nums) {

        if (root == null) return nums;

        dfs(root.left, nums);

        nums.add(root.val);

        dfs(root.right, nums);

        return nums;
    }
}
