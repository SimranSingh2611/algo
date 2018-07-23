package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * https://leetcode.com/problems/convert-bst-to-greater-tree
 */
public class ConvertBST {
    public TreeNode convertBST(TreeNode root) {
        int sum = sumBST(root);
        updateBST(root, sum);
        return root;
    }

    private int sumBST(TreeNode root) {
        // Easy way to compute sum of all elements
        return root == null ? 0 : root.val + sumBST(root.left) + sumBST(root.right);
    }

    private int updateBST(TreeNode root, int sum) {
        if(root == null)
            return sum;
        // In-order traversal
        sum = updateBST(root.left, sum);
        sum -= root.val;
        root.val += sum;
        sum = updateBST(root.right, sum);
        return sum;
    }
}
