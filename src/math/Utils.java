package math;

public class Utils {
    private static long mulmod(long a, long b, long mod) {
        return (a * b) % mod;
    }

    public static long binpow(long a, long pow, long mod) {
        if (pow == 0)
            return 1;
        long res = binpow(a, pow / 2, mod);
        res = mulmod(res, res, mod);

        if (pow % 2 == 1)
            res = mulmod(res, a, mod);
        return res;
    }

    public static long gcd(long a, long b) {
        if(a == 0) return b;
        return gcd(b % a, a);
    }
}
