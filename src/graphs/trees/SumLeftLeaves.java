package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Find the sum of all left leaves in a given binary tree
 * https://leetcode.com/problems/sum-of-left-leaves
 */
public class SumLeftLeaves {
    public int compute(TreeNode node, TreeNode parent) {
        int sum = 0;
        if(node.left == null && node.right == null) {
            if(parent != null && parent.left == node) sum += node.val;
        } else {
            if(node.left != null) sum += compute(node.left, node);
            if(node.right != null) sum += compute(node.right, node);
        }

        return sum;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        return compute(root, null);
    }
}
