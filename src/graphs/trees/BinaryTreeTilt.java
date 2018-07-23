package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Binary Tree Tilt
 * https://leetcode.com/problems/binary-tree-tilt/description
 */
public class BinaryTreeTilt {
    public static int tilt;
    public int findTilt(TreeNode root) {
        tilt = 0;
        findTiltInner(root);
        return tilt;
    }

    public int findTiltInner(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int sumLeft = findTiltInner(root.left);
        int sumRight = findTiltInner(root.right);

        tilt += Math.abs(sumLeft - sumRight);
        return(sumLeft + sumRight + root.val);
    }
}
