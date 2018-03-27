package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
* Ternary search
* https://www.hackerearth.com/practice/algorithms/searching/ternary-search/tutorial/
 */

class TernarySearch {
    private static long f(long x) {
        return 2 * x * x - 12 * x + 7;
    }
    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; n++) {
            String[] lr = br.readLine().split(" ");
            long left = Integer.parseInt(lr[0]);
            long right = Integer.parseInt(lr[1]);

            while(left + 2 < right) {
                long l1 = left + (right - left)/3;
                long l2 = right - (right - left)/3;
                if(f(l1) > f(l2)) left = l1;
                else right = l2;
            }

            long min = Long.MAX_VALUE;
            for(long i = left; i <= right; i++) min = Math.min(f(i), min);
            System.out.println(min);
        }
    }
}
