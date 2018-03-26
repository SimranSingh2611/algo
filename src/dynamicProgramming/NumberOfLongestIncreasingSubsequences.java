package dynamicProgramming;

/*
* Count the number of longest increasing subsequences
* https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 */
public class NumberOfLongestIncreasingSubsequences {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        // extra array of counters to store number of longest subsequences so far
        int[] counter = new int[n];
        int longest = 0;

        for(int i = 0; i < n; i++) {
            int val = nums[i];
            dp[i] = 1;
            counter[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] < val) {
                    if(dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        counter[i] = counter[j];
                    } else if(dp[i] == dp[j] + 1){
                        counter[i] += counter[j];
                    }
                }
            }
            longest = Math.max(longest, dp[i]);
        }

        int result = 0;
        for(int i = 0; i < n; i++) {
            if(dp[i] == longest) result += counter[i];
        }

        return result;
    }
}
