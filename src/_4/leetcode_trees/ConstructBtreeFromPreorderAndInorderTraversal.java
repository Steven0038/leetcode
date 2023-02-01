package _4.leetcode_trees;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal, medium
 * <p>
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Example 1:
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 */
public class ConstructBtreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        /**
         *                    3
         *                 /     \
         *                9      20
         *                      /  \
         *                     15   7
         */
        ConstructBtreeFromPreorderAndInorderTraversal cb = new ConstructBtreeFromPreorderAndInorderTraversal();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};

        TreeNode tree = cb.buildTree(preorder, inorder);
        BinaryTreeLevelOrderTraversal bl = new BinaryTreeLevelOrderTraversal();
        System.out.println(bl.levelOrder(tree));
    }

    int preorderIdx;
    Map<Integer, Integer> inorderIdxMap = new HashMap<>();

    /**
     * Topic: 給出 B-tree 值的 preorder 及 inorder 遍歷 array, 回傳該 B-tree 本身
     * Solution: preorder 先遍歷 root, 所以可以得到整個樹的根為 preorder[0],
     * 然後可以從 inorder 的 root 得知其左右節點的範圍。
     * 使用遞規, 從 root 開始建立 node 與樹, base case 是邊界 left > right
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderIdxMap.put(inorder[i], i); // <val, inorderElementIdx>
        }

        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    // left & right is the edge of inorder index
    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootVal = preorder[preorderIdx++];
        TreeNode root = new TreeNode(rootVal);

        // we pick the root from preorder arr,
        // so the root's left leaf and right leaf's index range depends on the position of root in the inorder arr.
        root.left = arrayToTree(preorder, left, inorderIdxMap.get(rootVal) - 1);
        root.right = arrayToTree(preorder, inorderIdxMap.get(rootVal) + 1, right);

        return root;
    }
}
