package DP;

import java.math.BigInteger;

/**
 * Nth Fibonacci number when ith number ti = ti-2 + (ti-1 ^ 2)
 * Numbers may exceed 64bit integer by far.
 *
 * https://www.hackerrank.com/challenges/fibonacci-modified
 *
 * Fibonacci numbers computation is an simple and comprehensible example of DP.
 * The straight-forward way of computing these numbers is to recursively define
 * fib(i) = fib(i - 1) + fib(i - 2) and compute an exhaustive search tree.
 */
public class Fibonacci2 {
    public static BigInteger fib2(int t1i, int t2i, int n) {
        BigInteger t1 = BigInteger.valueOf((long) t1i);
        BigInteger t2 = BigInteger.valueOf((long) t2i);

        BigInteger answer = BigInteger.ZERO;
        for(int i = 3; i <= n; i++) {
            answer = t1.add(t2.pow(2));
            t1 = t2;
            t2 = answer;
        }

        return answer;
    }
}
