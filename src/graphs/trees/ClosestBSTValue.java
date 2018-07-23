package graphs.trees;

import graphs.trees.helpers.TreeNode;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target
 * https://leetcode.com/problems/closest-binary-search-tree-value
 */
class ClosestBSTValue {
    private Integer innerFloor(TreeNode cur, int target) {
        if(cur.val > target) {
            if(cur.left != null) return innerFloor(cur.left, target);
            else return null;
        } else {
            if(cur.right != null) {
                Integer candidate = innerFloor(cur.right, target);
                if(candidate == null || candidate > target) return cur.val;
                else return candidate;
            } else {
                return cur.val;
            }
        }
    }

    private Integer innerCeiling(TreeNode cur, int target) {
        if(cur.val < target) {
            if(cur.right != null) return innerCeiling(cur.right, target);
            else return null;
        } else {
            if(cur.left != null) {
                Integer candidate = innerCeiling(cur.left, target);
                if(candidate == null || candidate < target) return cur.val;
                else return candidate;
            } else {
                return cur.val;
            }
        }
    }

    public int closestValue(TreeNode root, double target) {
        if(root == null) return 0;

        int left = (int) Math.floor(target);
        int right = (int) Math.ceil(target);

        Integer floor = innerFloor(root, left);
        Integer ceiling = innerCeiling(root, right);

        if(floor == null) return ceiling;
        if(ceiling == null) return floor;

        if(Math.abs(target - floor) > Math.abs(target - ceiling)) return ceiling;
        else return floor;
    }
}
