package dynamicProgramming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Facebook Hackercup 2018, Round 1: "Let it flow"
 * https://www.facebook.com/hackercup/problem/180494849326631/
 */
public class LetItFlow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("let_it_flow.txt"));
        int T = Integer.valueOf(br.readLine());
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        for(int t = 0; t < T; t++) {
            int N = Integer.valueOf(br.readLine());
            int modulo = 1000000007;
            String[] plan = new String[3];
            for(int i = 0; i < 3; i++) plan[i] = br.readLine();

            long[] dp = new long[3];
            dp[0] = 1;
            for(int i = 0; i < N; i++) {
                long[] dp_new = new long[3];
                if(plan[0].charAt(i) != '#' && plan[1].charAt(i) != '#') dp_new[0] = dp[1];
                if(plan[1].charAt(i) != '#' && plan[2].charAt(i) != '#') dp_new[2] = dp[1];
                if(plan[2].charAt(i) != '#' && plan[1].charAt(i) != '#') dp_new[1] += dp[2];
                if(plan[1].charAt(i) != '#' && plan[0].charAt(i) != '#') dp_new[1] += dp[0];

                for(int j = 0; j < 3; j++) dp_new[j] %= modulo;
                dp = dp_new;
            }
            writer.println(String.format("Case #%d: %s", (t + 1), dp[2]));
        }
        writer.close();
    }
}

