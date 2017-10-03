package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Triangle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] as = br.readLine().split(" ");
        int[] ai = new int[as.length];
        for(int i = 0; i < as.length; i++) {
            ai[i] = Integer.parseInt(as[i]);
        }

        Arrays.sort(ai);
        boolean possible = false;
        boolean any = false;
        if(ai[0] + ai[1] > ai[2] || ai[1] + ai[2] > ai[3]) {
            possible = true;
        } else if(ai[0] + ai[1] == ai[2] || ai[1] + ai[2] == ai[3]) {
            any = true;
        }
        System.out.println(possible ? "TRIANGLE" : any ? "SEGMENT" : "IMPOSSIBLE");
    }
}
