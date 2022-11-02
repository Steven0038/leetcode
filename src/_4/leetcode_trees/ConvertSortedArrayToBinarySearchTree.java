package _4.leetcode_trees;

public class ConvertSortedArrayToBinarySearchTree {
    static TreeNode root;


    public static void main(String[] args) {
        ConvertSortedArrayToBinarySearchTree tree = new ConvertSortedArrayToBinarySearchTree();
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

        root = tree.sortedArrayToBST(arr);

        tree.preOrderPrint(root);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        return helper(nums, start, end);
    }

    public TreeNode helper(int[] nums, int start, int end) {
        // find the middle value in nums and recursively build node
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, start, mid - 1);
        node.right = helper(nums, mid + 1, end);

        return node;
    }

    void preOrderPrint(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.val + " ");

        preOrderPrint(node.left);
        preOrderPrint(node.right);
    }

}
