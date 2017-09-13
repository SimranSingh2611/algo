package warmup;

/**
 * https://leetcode.com/problems/trim-a-binary-search-tree/description/
 */

class TrimBST {
    // Definition for a binary tree node, supplied to you
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null)
            return null;

        if(root.val < L)
            return trimBST(root.right, L, R);

        if(root.val > R)
            return trimBST(root.left, L, R);

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }
}