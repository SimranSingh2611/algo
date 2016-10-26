package DP;

import java.util.Arrays;

/**
 * Created by vladimir on 26/10/16.
 */
public class CoinChange {

    /*
    * How many different ways can you make change for an amount, given a list of coins?
    * https://www.hackerrank.com/challenges/coin-change
     */
    public static long getNumberOfWays(int[] coins, int sum) {
        int num = coins.length;
        Arrays.sort(coins);
        /*
        * dp[i][j] is the number of ways to give change of sum j
        * using coins up to of indices 0..i
        * if we have a set of coins {2, 3, 5}: dp[1][3] will be the number
        * of ways to give change of sum 3 using only coins 2 and 3.
         */
        long[][] dp = new long[num][sum + 1];

        // Initialization: how many ways are there to give
        // change for 0 sum? There is only one way, empty set
        for(int i = 0; i < num; i++) {
            dp[i][0] = 1L;
        }

        // At every step we compute the number of ways for sum = j,
        // this is our optimal solution for the sub-problem when sum = j
        for(int j = 1; j < sum + 1; j++) {
            for(int i = 0; i < num; i++) {
                dp[i][j] = 0L;

                // Accumulate ways that don't use coin i
                if(i > 0) {
                    // The number of ways using coins 0..i-1,
                    // here we reuse dp to accumulate sum
                    dp[i][j] = dp[i - 1][j];
                }

                // Calculate number of ways when we use coin i
                if(j >= coins[i]) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }

        return dp[num - 1][sum];
    }

    /*
    * You are given coins of different denominations and a total amount of money amount.
    * Write a function to compute the fewest number of coins that you need to make up
    * that amount.
    * https://leetcode.com/problems/coin-change/
     */
    public static int getMinimumNumberOfCoins(int[] coins, int sum) {
        int m = coins.length;
        int[] dp = new int[sum + 1];
        dp[0] = 0;

        for(int i = 1; i < sum + 1; i++) {
            // Let -1 be a special value - impossibility of getting
            // ith sum using the existing set of coins
            dp[i] = -1;

            for(int j = 0; j < m; j++) {
                // If the sum we are currently checking is not smaller
                // than the coin value and it is possible to get (sum - coin_value)
                // using existing set of coins
                if(coins[j] <= i && dp[i - coins[j]] != -1) {
                    // The number of coins needed is the current one + the minimum number
                    // of coins needed to get (sum - coin_value)
                    int current = 1 + dp[i - coins[j]];
                    if(dp[i] == -1 || current < dp[i]) {
                        dp[i] = current;
                    }
                }
            }
        }
        return dp[sum];
    }
}
