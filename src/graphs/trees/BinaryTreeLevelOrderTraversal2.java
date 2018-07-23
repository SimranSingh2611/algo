package graphs.trees;

import graphs.trees.helpers.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root)
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii
 */
public class BinaryTreeLevelOrderTraversal2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Deque<TreeNode> queue = new ArrayDeque<>();
        Deque<Integer> level = new ArrayDeque<>();
        queue.addLast(root);
        level.addLast(0);

        while(!queue.isEmpty()) {
            TreeNode cur = queue.removeFirst();
            int l = level.removeFirst();
            if(result.size() <= l) result.add(new ArrayList<>());
            result.get(l).add(cur.val);
            if(cur.left != null) {
                queue.addLast(cur.left);
                level.addLast(l + 1);
            }
            if(cur.right != null) {
                queue.addLast(cur.right);
                level.addLast(l + 1);
            }
        }

        for(int i = 0; i < result.size() / 2; i++) {
            List<Integer> temp = result.get(i);
            result.set(i, result.get(result.size() - 1 - i));
            result.set(result.size() - 1 - i, temp);
        }

        return result;
    }
}
