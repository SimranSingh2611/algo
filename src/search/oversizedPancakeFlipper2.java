package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;

/**
 *
 * GCJ2017 qualification, task A
 * Optimal
 *
 */
public class oversizedPancakeFlipper2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("PATH-IN"));
        int T = Integer.valueOf(br.readLine());

        FileWriter writer = new FileWriter("PATH-OUT");

        for(int t = 0; t < T; t++) {
            String[] s = br.readLine().split(" ");
            char[] seq = s[0].toCharArray();
            int N = Integer.valueOf(s[1]);
            int sz = seq.length;
            BitSet p = new BitSet(sz);

            for(int j = 0; j < seq.length; j++) {
                if(seq[j] == '+') {
                    p.set(j);
                }
            }
            int result = 0;
            for(int i = 0; i <= sz - N; i++) {
                if(!p.get(i)) {
                    result++;
                    p.flip(i, i + N);
                }
            }

            writer.write("Case #" + (t + 1) + ": " + (p.cardinality() == sz ? result  : "IMPOSSIBLE") + "\n");
        }
        writer.close();
    }
}
