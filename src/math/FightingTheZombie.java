package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Facebook Hackercup 2017, Qualification: "Fighting the zombie"
 * https://www.facebook.com/hackercup/problem/326053454264498/
 */
public class FightingTheZombie {
    public static List<double[][]> out = new ArrayList<double[][]>();

    public static void getRolls(int sides, int maxRolls) {
        double [][] outcomes = new double[maxRolls][maxRolls*sides];
        // One roll
        for(int i = 0; i < sides; i++) {
            outcomes[0][i] = 1.0/sides;
        }

        for(int k = 1; k < maxRolls; k++) {
            for(int i = 0; i < (k + 1) * sides; i++) {
                for(int j = 1; j <= sides; j++) {
                    if(i - j >= 0) {
                        outcomes[k][i] += 1.0/sides * outcomes[k - 1][i - j];
                    }
                }
            }
        }
        out.add(outcomes);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new FileReader("fighting_the_zombie.txt"));
        int num = Integer.parseInt(br.readLine());
        Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));

        int[] allSides = {4, 6, 8, 10, 12, 20};
        int[] trace = {0, 0, 0, 0, 0, 0, 1, 0, 2, 0, 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5};
        for(int i : allSides) {
            getRolls(i, 20);
        }

        for(int i = 0; i < num; i++) {
            String[] ssp = br.readLine().split(" ");
            int strength = Integer.parseInt(ssp[0]);
            int spells = Integer.parseInt(ssp[1]);

            String[] sp = br.readLine().split(" ");
            double maxProbability = 0.0;
            for(String s: sp) {
                Pattern p = Pattern.compile("(\\d+)d(\\d+)([+-]?)(\\d*)");
                Matcher m = p.matcher(s);
                m.find();
                int rolls = Integer.parseInt(m.group(1));
                int sides = Integer.parseInt(m.group(2));
                int extra = 0;
                if(!m.group(3).equals("")) {
                    if(m.group(3).equals("+")) {
                        extra = Integer.parseInt(m.group(4));
                    } else {
                        extra = -Integer.parseInt(m.group(4));
                    }
                }
                int currentStrength = strength - extra;

                double[][] outcomes = out.get(trace[sides]);
                double probability = 0.0;
                for(int o = 0; o < sides * rolls; o++) {
                    if(o + 1 >= currentStrength) {
                        probability += outcomes[rolls - 1][o];
                    }
                }
                if(Double.compare(probability, maxProbability) > 0) {
                    maxProbability = probability;
                }
            }

            writer.write(String.format("Case #%d: %f\n", i + 1, maxProbability));
        }
        writer.close();
    }
}

