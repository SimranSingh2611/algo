package graphs.trees;

import graphs.trees.helpers.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array
 * https://leetcode.com/problems/average-of-levels-in-binary-tree
 */
class AverageOfLevelsBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Long[]> levels = new ArrayList<>();

        Deque<TreeNode> queue = new ArrayDeque<>();
        Deque<Integer> level = new ArrayDeque<>();
        queue.addLast(root);
        level.addLast(0);

        while(!queue.isEmpty()) {
            TreeNode cur = queue.removeFirst();
            Integer l = level.removeFirst();

            if(levels.size() == l) {
                levels.add(new Long[]{(long) cur.val, 1L});
            } else {
                levels.get(l)[0] += (long) cur.val;
                levels.get(l)[1]++;
            }

            if(cur.left != null) {
                queue.addLast(cur.left);
                level.addLast(l + 1);
            }

            if(cur.right != null) {
                queue.addLast(cur.right);
                level.addLast(l + 1);
            }
        }

        List<Double> res = new ArrayList<>();
        for(Long[] avg : levels) {
            res.add(avg[0] * 1.0 / avg[1]);
        }

        return res;
    }
}
