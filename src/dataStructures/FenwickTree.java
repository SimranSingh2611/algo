package dataStructures;

/*
* Sum on range problem solved by Fenwick Tree data structure
* https://leetcode.com/problems/range-sum-query-mutable/
 */

class FenwickTree {
    private int[] values;
    private int n;
    private int[] parts;

    public FenwickTree(int[] nums) {
        n = nums.length;
        values = new int[n];
        parts = new int[n];

        for(int i = 0; i < n; i++) {
            update(i, nums[i]);
        }
    }

    public void update(int i, int val) {
        int delta = val - values[i];
        // don't forget to update the original array!!!
        values[i] = val;
        while(i < n) {
            parts[i] += delta;
            i = i | (i + 1);
        }
    }

    private int sumRange(int i) {
        int sum = 0;
        while(i >= 0) {
            sum += parts[i];
            i = (i & (i + 1)) - 1;
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        return sumRange(j) - sumRange(i - 1);
    }
}
