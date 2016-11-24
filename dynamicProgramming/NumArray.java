package dynamicProgramming;

/**
 * SumRange
 * https://leetcode.com/problems/range-sum-query-immutable/
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * - You may assume that the array does not change.
 * - There are many calls to sumRange function.
 */
public class NumArray {
    // one dimensional dp for prefix sums
    public int[] dp;

    public NumArray(int[] nums) {
        int len = nums.length;
        if(len > 0) {
            // prepare prefix sums
            dp = new int[len + 1];
            dp[0] = nums[0];
            for(int i = 1; i < len; i++) {
                dp[i] = dp[i - 1] + nums[i];
            }
        }
    }

    public int getSum(int i, int j) {
        if(j >= i) {
            if(i > 0) {
                // inclusive sums
                return(dp[j] - dp[i - 1]);
            } else {
                return(dp[j]);
            }
        }
        return 0;
    }
}