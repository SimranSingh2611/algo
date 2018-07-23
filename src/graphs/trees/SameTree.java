package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Given two binary trees, write a function to check if they are the same or not
 * https://leetcode.com/problems/same-tree
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
