package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Facebook Hackercup 2018, Qualification: "Tourist"
 * https://www.facebook.com/hackercup/problem/1632703893518337/
 */
public class Tourist {
    private static String nextTrip(List<String> attractions, int offset, int K) {
        int N = attractions.size();
        Set<String> trip = new HashSet<>();
        int counter = 0;
        while(counter < K) {
            trip.add(attractions.get(offset++));
            if(offset >= N) offset -= N;

            counter++;
        }

        List<String> endTrip = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            if(trip.contains(attractions.get(i))) endTrip.add(attractions.get(i));
        }

        return String.join(" ", endTrip);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tourist.txt"));
        int T = Integer.valueOf(br.readLine());
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        for(int t = 0; t < T; t++) {
            String[] NKV = br.readLine().split(" ");
            int N = Integer.parseInt(NKV[0]);
            int K = Integer.parseInt(NKV[1]);
            long V = Long.parseLong(NKV[2]) - 1;

            List<String> attractions = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String attraction = br.readLine();
                attractions.add(attraction);
            }

            boolean found = false;

            int steps = 0;
            int ptr = 0;
            boolean[] beenHere = new boolean[N];
            while(!beenHere[ptr]) {
                if(steps == V) {
                    writer.println(String.format("Case #%d: %s", (t+1), nextTrip(attractions, ptr, K)));
                    found = true;
                    break;
                }

                int nextHop = ptr + K;
                if(nextHop >= N) nextHop -= N;

                beenHere[ptr] = true;
                ptr = nextHop;
                steps++;
            }

            if(!found) {
                long stepsRemaining = V % steps;
                while(stepsRemaining > 0) {
                    int nextHop = ptr + K;
                    if(nextHop >= N) nextHop -= N;
                    ptr = nextHop;
                    stepsRemaining--;
                }
                writer.println(String.format("Case #%d: %s", (t+1), nextTrip(attractions, ptr, K)));
            }
        }

        writer.close();
    }
}

