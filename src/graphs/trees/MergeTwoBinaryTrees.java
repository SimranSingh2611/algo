package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Merge two binary trees
 * https://leetcode.com/problems/merge-two-binary-trees
 */
public class MergeTwoBinaryTrees {
    private void mergeTreesInner(TreeNode t1, TreeNode t2) {
        if(t1 != null && t2 != null) {
            t1.val += t2.val;
        }

        if(t1.left != null && t2.left != null) {
            mergeTreesInner(t1.left, t2.left);
        } else if(t2.left != null) {
            t1.left = t2.left;
            t2.left = null;
        }

        if(t1.right != null && t2.right != null) {
            mergeTreesInner(t1.right, t2.right);
        } else if(t2.right != null) {
            t1.right = t2.right;
            t2.right = null;
        }
    }
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) return t2;
        else if(t2 == null) return t1;

        mergeTreesInner(t1, t2);
        return t1;
    }
}
