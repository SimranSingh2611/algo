package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Mandragora forest
 * https://www.hackerrank.com/challenges/mandragora
 */
public class MandragoraForest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int k = 0; k < T; k++) {
            int N = Integer.parseInt(br.readLine());
            String[] vals = br.readLine().split(" ");
            long[] vali = new long[N];
            long sum = 0;
            for (int i = 0; i < N; i++) {
                vali[i] = Long.parseLong(vals[i]);
                sum += vali[i];
            }

            Arrays.sort(vali);
            long max_sum = 0;
            for (int i = 0; i < N; i++) {
                long max = Math.max((i + 1) * sum, (i + 2) * (sum - vali[i]));
                if (max_sum < max) {
                    max_sum = max;
                }
                sum -= vali[i];
            }
            System.out.println(max_sum);
        }
    }
}
