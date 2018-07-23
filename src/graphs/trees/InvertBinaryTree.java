package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Invert a binary tree
 * https://leetcode.com/problems/invert-binary-tree
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }
}
