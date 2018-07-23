package graphs.trees;

import graphs.trees.helpers.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths
 * https://leetcode.com/problems/binary-tree-paths
 */
class BinaryTreePaths {
    private void traverse(TreeNode node, List<String> result, Deque<Integer> path) {
        path.addLast(node.val);
        if(node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            boolean addArrow = false;

            for(Integer i : path) {
                if(addArrow) sb.append("->");
                sb.append(i);
                addArrow = true;
            }
            result.add(sb.toString());
        }

        if(node.left != null) traverse(node.left, result, path);
        if(node.right != null) traverse(node.right, result, path);

        path.removeLast();
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();

        if(root == null) return result;

        Deque<Integer> path = new ArrayDeque<>();
        traverse(root, result, path);
        return result;
    }
}