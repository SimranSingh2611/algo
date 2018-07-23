package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Path sum
 * https://leetcode.com/problems/path-sum
 */
public class TreePathSum {
    public boolean traverse(TreeNode root, int curSum, int sum) {
        if(root == null)
            return false;
        if(curSum + root.val == sum && root.left == null && root.right == null)
            return true;
        return traverse(root.right, curSum + root.val, sum) || traverse(root.left, curSum + root.val, sum);
    }
    public boolean hasPathSum(TreeNode root, int sum) {
        return traverse(root, 0, sum);
    }
}
