package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Red John is Back
 * https://www.hackerrank.com/challenges/red-john-is-back?h_r=internal-search
 */
public class RedJohn {
    public static Map<Integer, Integer> primeNums = new HashMap<>();
    public static int primeCount(int N) {
        if(primeNums.containsKey(N)) {
            return primeNums.get(N);
        }

        if(N <= 3) {
            return N - 1;
        }

        boolean[] notPrime = new boolean[N];
        notPrime[0] = true;

        for(int i = 1; i < N; i++) {
            if(notPrime[i]) {
                continue;
            }

            int val = i + 1;
            for(int j = 2 * val; j <= N; j += val) {
                notPrime[j - 1] = true;
            }
        }

        int primes = 0;
        for(int i = 0; i < N; i++) {
            if(!notPrime[i]) {
                primes++;
            }
        }
        primeNums.put(N, primes);
        return primes;
    }

    public static int countWays(int N) {
        if(N < 4) {
            return 1;
        }

        int[]dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= N; i++) {
            dp[i] = 0;
            // There are only two distinct building blocks:
            // (1) is vertical bar and (2) is 4 horizontal bars
            // Everything else can be composed out of these two

            // Attach vertical bar and compute the number of ways
            // for dp[i  - 1].
            dp[i] += dp[i - 1];

            // Attach 4 horizontal bars and compute the number of ways
            // for dp[i - 4]
            if(i - 4 >= 0) {
                dp[i] += 1 * dp[i - 4];
            }
        }

        return dp[N];
    }
}
