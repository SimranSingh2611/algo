package graphs.trees;

import graphs.trees.helpers.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * BST iterator implementation with the following requirements:
 * - both next() and hasNext() should run in ammortized O(1) time
 * - both next() and hasNext() should use O(H) memory, where H is tree height
 * https://leetcode.com/problems/binary-search-tree-iterator/
 */
public class BSTIterator {
    Deque<TreeNode> state;

    public BSTIterator(TreeNode root) {
        state = new ArrayDeque<>();
        TreeNode temp = root;

        while(temp != null) {
            state.push(temp);
            temp = temp.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !state.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = state.pop();
        int toReturn = cur.val;

        if(cur.right != null) {
            cur = cur.right;

            while(cur != null) {
                state.push(cur);
                cur = cur.left;
            }
        }
        return toReturn;
    }
}
