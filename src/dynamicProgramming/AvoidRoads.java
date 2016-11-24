package dynamicProgramming;

/**
 * https://community.topcoder.com/stat?c=problem_statement&pm=1889&rd=4709
 */
public class AvoidRoads {
    public static long numWays(int width, int height, String[] forbidden) {
        long[][] dp = new long[height + 1][width + 1];
        int[][] block = new int[height + 1][width + 1];
        for(String x: forbidden) {
            String[] fromto = x.split(" ");
            int x1 = Integer.parseInt(fromto[0]);
            int y1 = Integer.parseInt(fromto[1]);
            int x2 = Integer.parseInt(fromto[2]);
            int y2 = Integer.parseInt(fromto[3]);

            // possible since we are always one block away
            if(x1 != x2) {
                if(block[Math.max(x1, x2)][y2] == -2) {
                    block[Math.max(x1, x2)][y2] = -3;
                } else {
                    block[Math.max(x1, x2)][y2] = -1;
                }
            }

            if(y1 != y2) {
                if(block[Math.max(x1, x2)][y2] == -1) {
                    block[Math.max(x1, x2)][y2] = -3;
                } else {
                    block[Math.max(x1, x2)][y2] = -2;
                }
            }
        }
        for(int i = 0; i <= height; i++) {
            for(int j = 0; j <= width; j++) {
                dp[i][j] = 0;
                if(i == 0 && j == 0) {
                    dp[i][j] = 1;
                }

                if(block[i][j] == -3) {
                    continue;
                }
                boolean noup = block[i][j] == -2;
                boolean noright = block[i][j] == -1;

                if(i > 0 && !noright) {
                    dp[i][j] += dp[i - 1][j];
                }

                if(j > 0 && !noup) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[height][width];
    }
}
