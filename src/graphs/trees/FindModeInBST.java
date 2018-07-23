package graphs.trees;

import graphs.trees.helpers.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Find Mode(s) in a BST
 * https://leetcode.com/problems/find-mode-in-binary-search-tree
 */
class FindModeInBST {
    private int[] traverse(TreeNode node, int[] prev, int[] best) {
        if(node.left != null) {
            prev = traverse(node.left, prev, best);
        }
        if(prev == null) prev = new int[]{node.val, 1};
        else {
            if(prev[0] == node.val) prev[1]++;
            else {
                prev = new int[]{node.val, 1};
            }
        }

        best[0] = Math.max(best[0], prev[1]);

        if(node.right != null)
            prev = traverse(node.right, prev, best);

        best[0] = Math.max(best[0], prev[1]);

        return prev;
    }

    private void updateIfBest(int[] prev, int best, Set<Integer> answers) {
        if(prev[1] == best) answers.add(prev[0]);
    }

    private int[] generate(TreeNode node, int[] prev, int best, Set<Integer> answers) {

        updateIfBest(prev, best, answers);

        if(node.left != null) {
            prev = generate(node.left, prev, best, answers);
        }

        updateIfBest(prev, best, answers);

        if(prev == null) prev = new int[]{node.val, 1};
        else {
            if(prev[0] == node.val) prev[1]++;
            else prev = new int[]{node.val, 1};
        }

        updateIfBest(prev, best, answers);

        if(node.right != null) {
            prev = generate(node.right, prev, best, answers);
        }

        updateIfBest(prev, best, answers);

        return prev;
    }

    public int[] findMode(TreeNode root) {
        if(root == null) return new int[0];
        int[] best = new int[1];
        traverse(root, null, best);
        Set<Integer> answer = new HashSet<>();

        generate(root, new int[]{0, 0}, best[0], answer);
        int[] res = new int[answer.size()];
        int index = 0;
        for(int i : answer) res[index++] = i;
        return res;
    }
}