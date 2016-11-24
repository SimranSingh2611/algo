package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Bricks game
 * https://www.hackerrank.com/challenges/play-game
 */
public class BricksGame {
    public static long countBestScore(int[] vali, int N) {

        // dp[i] stores the best sum player is able to achieve from step i
        // foward to the end of the game (cumulative)
        long[] dp = new long[N];
        // next stores the index to which we jump after player makes a move
        int[] next = new int[N];

        // initialize the last element: when we have only one brick left
        // the best option is to take it, same holds true for N - 2 and N - 3
        dp[N - 1] = vali[N - 1];
        next[N - 1] = N;

        // iterate back to front
        for (int i = N - 2; i >= 0; i--) {
            dp[i] = 0;

            long max = -1;
            int index = i + 1;
            long sum = 0;
            for (int j = i + 1; j < i + 4 && j <= N; j++) {
                sum += vali[j - 1];
                
                // 2 corner cases: j = N or next[j] = N
                if (j < N && next[j] < N) {
                    if (sum + dp[next[j]] > max) {
                        max = sum + dp[next[j]];
                        index = j;
                    }
                } else {
                    if (sum > max) {
                        max = sum;
                        index = j;
                    }
                }

            }
            dp[i] = max;
            next[i] = index;
        }
        return dp[0];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int k = 0; k < T; k++) {
            int N = Integer.parseInt(br.readLine());
            String[] vals = br.readLine().split(" ");
            int[] vali = new int[N];
            for (int i = 0; i < N; i++) {
                vali[i] = Integer.parseInt(vals[i]);
            }
            System.out.println(countBestScore(vali, N));
        }
    }
}

