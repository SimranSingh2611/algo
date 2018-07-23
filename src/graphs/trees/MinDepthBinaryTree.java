package graphs.trees;

import graphs.trees.helpers.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a binary tree, find its minimum depth
 * https://leetcode.com/problems/minimum-depth-of-binary-tree
 */
public class MinDepthBinaryTree {
    public int minDepth(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        Deque<Integer> level = new ArrayDeque<>();
        if(root == null) {
            return 0;
        }
        queue.addLast(root);
        level.addLast(1);

        while(!queue.isEmpty()) {
            TreeNode cur = queue.removeFirst();
            int cur_level = level.removeFirst();
            if(cur.left == null && cur.right == null)
                return cur_level;
            if(cur.left != null) {
                queue.addLast(cur.left);
                level.add(cur_level + 1);
            }
            if(cur.right != null) {
                queue.addLast(cur.right);
                level.add(cur_level + 1);
            }
        }
        return 0;
    }
}