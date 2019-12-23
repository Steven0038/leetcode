package _4.leetcode_trees;

public class MaxDepthOfBinaryTree {

	TreeNode root;

	public int maxDepth(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			/* compute the depth of each subtree */
			int lDepth = maxDepth(node.left);
			int rDepth = maxDepth(node.right);

			 /* use the larger one */
			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}

		/*solution 2*/
//       return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}

	/* Driver program to test above functions */
	public static void main(String[] args) {
		MaxDepthOfBinaryTree tree = new MaxDepthOfBinaryTree();

		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(3);
		tree.root.left.left = new TreeNode(4);
		tree.root.left.right = new TreeNode(5);

		System.out.println("Height of tree is : " + tree.maxDepth(tree.root));

	}

}
