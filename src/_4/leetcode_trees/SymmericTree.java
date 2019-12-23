package _4.leetcode_trees;

/*
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
 

Note:
Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmericTree {

	TreeNode root;

	public static void main(String[] args) {
		SymmericTree tree = new SymmericTree();
		tree.root = new TreeNode(1);
		tree.root.left = new TreeNode(2);
		tree.root.right = new TreeNode(2);
		tree.root.left.left = new TreeNode(3);
		tree.root.left.right = new TreeNode(4);
		tree.root.right.left = new TreeNode(4);
		tree.root.right.right = new TreeNode(3);

		System.out.print(tree.isSymmetric(tree.root));

	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}

		return isMirror(root.left, root.right);
	}

	public boolean isMirror(TreeNode l, TreeNode r) {
		if (l == null && r == null) {
			return true;
		}
		if (l == null || r == null) {
			return false;
		}

		return (l.val == r.val) && isMirror(l.left, r.right) && isMirror(l.right, r.left);
	}

}
