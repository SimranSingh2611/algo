package graphs.trees;

import graphs.trees.helpers.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst
 */
public class TwoSumBST {
    private void flatten(TreeNode node, List<Integer> ar) {
        if(node.left != null) {
            flatten(node.left, ar);
        }
        ar.add(node.val);
        if(node.right != null) {
            flatten(node.right, ar);
        }
    }
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> ar = new ArrayList<>();
        flatten(root, ar);

        int start = 0;
        int end = ar.size() - 1;

        while(start < end) {
            int sum = ar.get(start) + ar.get(end);
            if(sum == k) return true;
            else if(sum < k) start++;
            else end--;
        }
        return false;
    }
}