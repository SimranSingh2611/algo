package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center)
 * https://leetcode.com/problems/symmetric-tree
 */
public class SymmetricTree {
    private boolean helper(TreeNode a, TreeNode b) {
        if(a == null && b == null)
            return true;
        else if(a == null || b == null)
            return false;
        else if(a.val != b.val)
            return false;
        else
            return helper(a.right, b.left) && helper(a.left, b.right);

    }
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }
}
