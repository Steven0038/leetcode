package _4.leetcode_trees;

import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's
 * key. The right subtree of a node contains only nodes with keys greater than
 * the node's key. Both the left and right subtrees must also be binary search
 * trees.
 * 
 * 
 * Example 1:
 * 
 * 		2 
 * 	   / \ 
 *    1   3
 * 
 * Input: [2,1,3] 
 * Output: true 
 * 
 * Example 2:
 * 
 * 		5 
 * 	   / \ 
 *    1  4 
 *   / \ 
 *  3   6
 * 
 * Input: [5,1,4,null,null,3,6] 
 * Output: false 
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * 
 * @author steven
 *
 */
public class ValidateBinarySearchTree {

	public static void main(String[] args) {
		TreeNode one = new TreeNode(5);
		TreeNode two = new TreeNode(1);
		TreeNode three = new TreeNode(4);
		TreeNode four = new TreeNode(3);
		TreeNode five = new TreeNode(6);
		one.left = two;
		one.right = three;
		two.left = four;
		two.right = five;
		
		ValidateBinarySearchTree vbs = new ValidateBinarySearchTree();
		vbs.isValidBST(one);
	}
	
    /*
    * In-order traversal
    */
	private boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null;
		while (root != null || !stack.isEmpty()) {
			while (root != null) { // 將最左的全部加到 stack 中, 直到碰到底
				stack.push(root);
				root = root.left;
			}

			root = stack.pop(); // 再慢慢地從底吐出來,
			/// 只要吐出來的底的前一個不是 null, 就比較大小, 前一個在左, 吐出來的在右, 前一個 < 吐出來
			if (pre != null && root.val <= pre.val)
				return false;
			pre = root;
			root = root.right;
		}
		return true;
	}
	
	/*
	 * Recursive solution 
	class Solution {
	    private boolean isValid = true;
	    private TreeNode pre = null;
	    public boolean isValidBST(TreeNode root) {
	        helper(root);
	        return isValid;
	    }
	    
	    private void helper(TreeNode root)  {
	        if (root == null) 
	            return;
	        helper(root.left);
	        if (pre != null && pre.val >= root.val) {
	            isValid = false;
	            return;
	        }
	        pre = root;
	        helper(root.right);
	    }
	}
	*/
}




