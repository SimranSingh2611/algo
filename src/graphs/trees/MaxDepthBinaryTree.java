package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Given a binary tree, find its maximum depth
 * https://leetcode.com/problems/maximum-depth-of-binary-tree
 */
class MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

