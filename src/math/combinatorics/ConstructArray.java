package math.combinatorics;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/101hack52/challenges/construct-the-array
 *
 * Your goal is to find the number of ways to construct an array such that consecutive positions contain different values.
 * Specifically, we want to construct an array with n elements such that each element between 1 and k, inclusive.
 * We also want the first and last elements of the array to be 1 and x respectively.
 *
 * Given n, x and k, find the number of ways to construct such an array.
 */
public class ConstructArray {

    static long countArray(int n, int kk, int x) {
        long total = 1;
        long k = (long) kk;
        long xs = x == 1 ? 1 : 0;
        for(int i = 0; i < n - 2; i++) {
            long temp = total;
            total = total * (k - 1);
            xs = (temp - xs);
            total = total % 1000000007;
            xs = xs % 1000000007;

        }
        long result = (total - xs) % 1000000007;
        if(result < 0) result += 1000000007;
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        long answer = countArray(n, k, x);
        System.out.println(answer);
        in.close();
    }
}