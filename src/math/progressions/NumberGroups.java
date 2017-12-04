package math.progressions;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/101hack52/challenges/number-groups
 */
public class NumberGroups {

    static long sumRow(long a1, long d, long n) {
        return (2*a1 + d*(n - 1))*n/2;
    }
    static long sumOfGroup(int k) {
        long numberBefore = sumRow(1, 1, k - 1);
        long an = 1 + (numberBefore) * 2;
        return sumRow(an, 2, k);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        long answer = sumOfGroup(k);
        System.out.println(answer);
        in.close();
    }
}