package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Facebook Hackercup 2017, Round 1: "Manic moving"
 * https://www.facebook.com/hackercup/problem/300438463685411/
 */
public class ManicMoving {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new FileReader("manic_moving.txt"));
        int T = Integer.parseInt(br.readLine());
        Writer writer =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));
        int bigdist = 10000000;
        for(int t = 0; t < T; t++) {
            String[] NMK = br.readLine().split(" ");
            int N = Integer.parseInt(NMK[0]);
            int M = Integer.parseInt(NMK[1]);
            int K = Integer.parseInt(NMK[2]);

            int[][] g = new int[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(i == j) {
                        g[i][j] = 0;
                    } else {
                        g[i][j] = bigdist;
                    }
                }
            }

            for(int m = 0; m < M; m++) {
                String[] ABG = br.readLine().split(" ");
                int A = Integer.parseInt(ABG[0]);
                int B = Integer.parseInt(ABG[1]);
                int G = Integer.parseInt(ABG[2]);

                if(g[A - 1][B - 1] > G) {
                    g[A - 1][B - 1] = G;
                    g[B - 1][A - 1] = G;
                }
            }

            for(int mid = 0; mid < N; mid++) {
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                        g[i][j] = Math.min(g[i][j], g[i][mid] + g[mid][j]);
                    }
                }
            }

            int[] deliver = new int[K];
            int[] pickup = new int[K];
            for(int k = 0; k < K; k++) {
                String[] AB = br.readLine().split(" ");
                int A = Integer.parseInt(AB[0]) - 1;
                int B = Integer.parseInt(AB[1]) - 1;

                pickup[k] = A;
                deliver[k] = B;
            }

            int [][][] dp = new int[N][K + 1][3];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < K + 1; j++) {
                    for(int k = 0; k < 3; k++) {
                        dp[i][j][k] = bigdist;
                    }
                }
            }
            dp[0][0][0] = 0;

            for(int delivered = 0; delivered < K; delivered++) {
                for(int pickedExtra = 0; pickedExtra < 3; pickedExtra++) {
                    for(int city = 0; city < N; city++) {
                        if(dp[city][delivered][pickedExtra] < bigdist) {
                            if(pickedExtra == 0) {
                                //only pickup
                                int nextCity = pickup[delivered];
                                if(dp[nextCity][delivered][1] > dp[city][delivered][0] + g[city][nextCity]){
                                    dp[nextCity][delivered][1] = dp[city][delivered][0] + g[city][nextCity];
                                }
                            } else if(pickedExtra == 1) {
                                // pickup more
                                if(delivered + pickedExtra < K) {
                                    int nextCity = pickup[delivered + pickedExtra];
                                    if(dp[nextCity][delivered][pickedExtra + 1] > dp[city][delivered][1] + g[city][nextCity]){
                                        dp[nextCity][delivered][pickedExtra + 1] = dp[city][delivered][1] + g[city][nextCity];
                                    }
                                }

                                // or deliver
                                int nextCityD = deliver[delivered];
                                if(dp[nextCityD][delivered + 1][pickedExtra - 1] > dp[city][delivered][pickedExtra] + g[city][nextCityD]){
                                    dp[nextCityD][delivered + 1][pickedExtra - 1] = dp[city][delivered][pickedExtra] + g[city][nextCityD];
                                }
                            } else {
                                // or deliver
                                int nextCityD = deliver[delivered];
                                if(dp[nextCityD][delivered + 1][pickedExtra - 1] > dp[city][delivered][pickedExtra] + g[city][nextCityD]){
                                    dp[nextCityD][delivered + 1][pickedExtra - 1] = dp[city][delivered][pickedExtra] + g[city][nextCityD];
                                }
                            }
                        }
                    }
                }
            }

            int min = bigdist;
            for(int i = 0; i < N; i++) {
                if(dp[i][K][0] < min) {
                    min = dp[i][K][0];
                }
            }

            if(min >= bigdist) {
                writer.write(String.format("Case #%d: %d\n", t + 1, -1));
            } else {
                writer.write(String.format("Case #%d: %d\n", t + 1, min));
            }
        }
        writer.close();

    }
}
