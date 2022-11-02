package _4.leetcode_trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. Binary Tree Right Side View
 * <p>
 * medium, company
 * <p>
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * Input: root = [1,2,3,null,5,null,4]
 * <p>
 * Output: [1,3,4]
 */
public class BinaryTreeRightSideView {
    TreeNode root;

    public static void main(String[] args) {
        BinaryTreeRightSideView tree = new BinaryTreeRightSideView();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.right = new TreeNode(4);

        System.out.println(tree.rightSideView(tree.root));
    }

    private List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // NOTE: 這邊 queue.size() 不能直接放進迴圈裡用 TODO:why?
            ret.add(queue.peek().val); // 因為下面都是先加入右節點, 所以從右向左看都是 queue 中的 top, 也就是每層的右節點先被看到,
                                       // 除非該層沒有右節點, 才會看到左節點
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.right != null) { // 先加右節點
                    queue.offer(curr.right);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
            }
        }

        return ret;
    }

}
