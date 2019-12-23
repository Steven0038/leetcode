package _4.leetcode_trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 * @author steven
 *
 */
public class BinaryTreeLevelOrderTraversal {
	
	public static void main(String[] args) {
		BinaryTreeLevelOrderTraversal tree = new BinaryTreeLevelOrderTraversal();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		tree.levelOrder(root);
	}

	private List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			List<Integer> level = new ArrayList<>();
			int cnt = queue.size();
			for (int i = 0; i < cnt; i++) {
				TreeNode node = queue.poll();
				System.out.print(node.val + " ");
				level.add(node.val);
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			res.add(level);
		}
		return res;
	}

}
