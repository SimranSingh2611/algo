package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * http://codeforces.com/problemset/problem/868/C
 *
 * Snark and Philip are preparing the problemset for the upcoming pre-qualification round for semi-quarter-finals.
 *
 * They have a bank of n problems, and they want to select any non-empty subset of it as a problemset.
 * k experienced teams are participating in the contest. Some of these teams already know some of the problems.
 * To make the contest interesting for them, each of the teams should know at most half of the selected problems.
 *
 * Determine if Snark and Philip can make an interesting problemset!
 */
public class QualificationRounds {
    public static int binaryOperator(int a, int b) {
        return a == 1 && b == 1 ? 0 : 1;
    }

    public static boolean isLegitPair(int a, int b) {
        while (a != 0 && b != 0) {
            int temp = binaryOperator(a & 1, b & 1);
            if (temp == 0) return false;
            a >>= 1;
            b >>= 1;
        }
        return true;
    }

    public static boolean solve(int n, int k) throws IOException {
        boolean[] tasks = new boolean[16];

        for (int i = 0; i < n; i++) {
            String[] vals = br.readLine().split(" ");
            int task = 0;
            int multiplier = 1;
            for (int j = k - 1; j >= 0; j--) {
                task += Integer.parseInt(vals[j]) * multiplier;
                multiplier *= 2;
            }
            if (task == 0) {
                return true;
            }
            tasks[task] = true;
        }

        int lim = (int) Math.pow(2, k);
        for (int i = 0; i < lim; i++) {
            for (int j = i + 1; j < lim; j++) {
                if (tasks[i] && tasks[j] && isLegitPair(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        boolean result = solve(n, k);
        System.out.println(result ? "YES" : "NO");
    }
}