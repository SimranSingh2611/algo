package dynamicProgramming;

/**
 * Given n, how many structurally unique BST's that store values 1 ... n
 * https://leetcode.com/problems/unique-binary-search-trees/
 */
class UniqueBST {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            int more = 0;
            int less = i - 1;
            while(less >= 0) {
                dp[i] += dp[more] * dp[less];
                more++;
                less--;
            }
        }

        return dp[n];
    }
}
