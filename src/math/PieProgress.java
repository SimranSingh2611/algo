package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Facebook Hackercup 2017, Round 1: "Pie progress"
 * https://www.facebook.com/hackercup/problem/1800890323482794/
 */
public class PieProgress {
    public static class Pie implements Comparable<Pie> {
        public int cost;
        public int day;

        public Pie(int cost, int day) {
            this.cost = cost;
            this.day = day;
        }

        @Override
        public int compareTo(Pie p2) {
            if(this.cost < p2.cost) {
                return -1;
            } else if(this.cost > p2.cost) {
                return 1;
            }
            return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new FileReader("/Users/vladimir/Downloads/pie_progress.txt"));
        int T = Integer.parseInt(br.readLine());
        Writer writer =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                        "/Users/vladimir/Downloads/output.txt"), "utf-8"));

        for(int t = 0; t < T; t++) {
            String[] NM = br.readLine().split(" ");
            int N = Integer.parseInt(NM[0]);
            int M = Integer.parseInt(NM[1]);

            int[][] prices = new int[N][M];
            for(int i = 0; i < N; i++) {
                String[] day = br.readLine().split(" ");
                int[] pricesDay = new int[M];
                for(int j = 0; j < M; j++) {
                    pricesDay[j] = Integer.parseInt(day[j]);
                }
                Arrays.sort(pricesDay);
                for(int j = 0; j < M; j++) {
                    prices[i][j] = pricesDay[j];
                }
            }

            int[][] endCosts = new int[N][M];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    int p = (int) Math.pow((j + 1), 2);
                    if(j > 0) {
                        int prevP = (int) Math.pow(j, 2);
                        endCosts[i][j] = endCosts[i][j - 1] - prevP + prices[i][j] + p;
                    } else {
                        endCosts[i][j] = prices[i][j] + p;
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = M - 1; j >= 0; j--) {
                    if(j > 0) {
                        endCosts[i][j] -= endCosts[i][j - 1];
                    }
                }
            }

            List<Pie> pies = new ArrayList<Pie>();

            for(int i = 0; i < M * N; i++) {
                Pie p = new Pie(endCosts[(i - i % M) / M][i % M], (i - i % M) / M);
                pies.add(p);
            }

            Collections.sort(pies);
            Set<Integer> bought = new HashSet<Integer>();

            int total = 0;
            // Let's do O(N^2) since the input is small
            for(int i = 0; i < N; i++) {
                int j = 0;
                while(pies.get(j).day > i || bought.contains(j)) {
                    j++;
                }
                total += pies.get(j).cost;
                bought.add(j);
            }

            writer.write(String.format("Case #%d: %d\n", t + 1, total));
        }
        writer.close();
    }
}
