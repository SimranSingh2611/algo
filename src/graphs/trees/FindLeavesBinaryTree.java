package graphs.trees;

import graphs.trees.helpers.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 */
class FindLeavesBinaryTree {
    private int markInverseDepth(TreeNode cur, List<List<Integer>> leaves) {
        int depth = 0;
        if(cur.left != null) depth = Math.max(depth, 1 + markInverseDepth(cur.left, leaves));
        if(cur.right != null) depth = Math.max(depth, 1 + markInverseDepth(cur.right, leaves));
        while(depth >= leaves.size()) leaves.add(new ArrayList<>());
        leaves.get(depth).add(cur.val);
        return depth;
    }
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        markInverseDepth(root, result);
        return result;
    }
}
