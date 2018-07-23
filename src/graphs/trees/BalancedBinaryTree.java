package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced
 * https://leetcode.com/problems/balanced-binary-tree
 */
class BalancedBinaryTree {
    public static boolean isBalanced;
    public boolean isBalanced(TreeNode root) {
        isBalanced = true;
        traverse(root);
        return isBalanced;
    }
    private int traverse(TreeNode cur) {
        if(cur == null) {
            return 0;
        }
        int leftH = traverse(cur.left);
        int rightH = traverse(cur.right);
        if( Math.abs(leftH - rightH) > 1) {
            isBalanced = false;
        }
        return 1 + Math.max(leftH, rightH);
    }
}
