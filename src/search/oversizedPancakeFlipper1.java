package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * GCJ2017 qualification, task A
 * Suboptimal
 *
 */
public class oversizedPancakeFlipper1 {

    // This version is suboptimal and won't pass the large data set.
    // It follows the same idea as WaterBowls. After the contest I've
    // written a follow-up that solves large data set (oversizedPancakeFlipper2).
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

            Set<String> exists = new HashSet<>();

            Queue<BitSet> queue = new LinkedList<>();
            // Steps
            Queue<Integer> squeue = new LinkedList<>();
            int result = -1;

            queue.add(p);
            squeue.add(0);

            while(!queue.isEmpty()) {
                BitSet current = queue.poll();
                int step = squeue.poll();
                if(current.cardinality() == sz) {
                    result = step;
                    break;
                }

                String repr = current.toString();
                if(exists.contains(repr)) {
                    continue;
                }

                exists.add(repr);

                for(int i = 0; i <= sz - N; i++) {
                    BitSet bs = (BitSet) current.clone();
                    bs.flip(i, i + N);

                    if(!exists.contains(bs.toString())) {
                        queue.add(bs);
                    }
                    squeue.add(step + 1);
                }
            }

            writer.write("Case #" + (t + 1) + ": " + (result == -1 ? "IMPOSSIBLE" : result) + "\n");
        }
        writer.close();
    }
}
