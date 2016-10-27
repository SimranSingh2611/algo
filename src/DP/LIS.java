package DP;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * https://www.hackerrank.com/contests/master/challenges/longest-increasing-subsequent
 */
public class LIS {
    /*
    * Sub-optimal way, O(N^2)
     */
    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len < 2) {
            return len;
        }
        int longest = 0;
        int[] dp = new int[len];
        for(int i = 0; i < len; i++) {
            dp[i] = 1;
            // Go back to all the previous increasing subsequences
            // and try to append new element to them
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] < nums[i]) {
                    if(dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            // Note that it is not guaranteed that the result will
            // be stored in the last dp cell
            if(longest < dp[i]) {
                longest = dp[i];
            }
        }
        return longest;
    }
}
