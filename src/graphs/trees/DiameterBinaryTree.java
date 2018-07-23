package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree
 * https://leetcode.com/problems/diameter-of-binary-tree
 */
public class DiameterBinaryTree {
    private int[] inner(TreeNode node) {
        int longestLeft = 0, longestRight = 0;
        int best = 0;
        if(node.left != null) {
            int[] left = inner(node.left);
            longestLeft = left[0];
            best = Math.max(best, left[1]);
        }
        if(node.right != null) {
            int[] right = inner(node.right);
            longestRight = right[0];
            best = Math.max(best, right[1]);
        }
        best = Math.max(best, longestLeft + longestRight);
        return new int[]{Math.max(longestLeft, longestRight) + 1, best};
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        return inner(root)[1];
    }
}
