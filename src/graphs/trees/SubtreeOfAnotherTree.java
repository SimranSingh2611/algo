package graphs.trees;

import graphs.trees.helpers.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Subtree of another tree
 * https://leetcode.com/problems/subtree-of-another-tree
 */
public class SubtreeOfAnotherTree {
    private void findNode(TreeNode node, int key, List<TreeNode> nodes) {
        if(node.val == key) nodes.add(node);

        if(node.left != null) {
            findNode(node.left, key, nodes);
        }
        if(node.right != null) {
            findNode(node.right, key, nodes);
        }
    }

    private boolean compare(TreeNode st, TreeNode t) {
        if(st == null && t == null) return true;
        else if(st == null || t == null) return false;

        if(st.val != t.val) {
            return false;
        }
        return compare(st.right, t.right) && compare(st.left, t.left);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(t == null) return true;
        int key = t.val;
        List<TreeNode> st = new ArrayList<>();
        findNode(s, key, st);

        for(TreeNode node : st) {
            boolean found = compare(node, t);
            if(found) return true;
        }

        return false;
    }
}