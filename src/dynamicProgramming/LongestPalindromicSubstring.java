package dynamicProgramming;

/**
 * LPS - longest palindromic substring (subsequence)
 * Given a string s, find the longest palindromic subsequence's length in s.
 * https://leetcode.com/problems/longest-palindromic-subsequence
 *
 *
 */
public class LongestPalindromicSubstring {
    public int lps(String s) {
        char[] ch = s.toCharArray();
        int n = ch.length;

        int[][] dp = new int[n][n + 1];
        for(int j = 0; j <= n; j++) {
            for(int i = j - 1; i >= 0; i--) {
                if(i == j) {
                    dp[i][j] = 0;
                } else if(j == i + 1) {
                    dp[i][j] = 1;
                } else {
                    if(ch[j - 1] == ch[i]) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    }
                }
            }
        }
        return dp[0][n];
    }
}
