package math;

/*
* A classic long division algorithm
* https://leetcode.com/problems/divide-two-integers
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        boolean negative = (dividend < 0 || divisor < 0) && !(dividend < 0 && divisor < 0);

        long dividendL = dividend;
        long divisorL  = divisor;

        if(dividend < 0) dividendL = - dividendL;
        if(divisor < 0) divisorL = -divisorL;

        long result = 0;
        long temp = 0;
        long mask = 1 << 30;

        while((mask & dividendL) == 0) {
            mask >>= 1;
        }

        while(dividendL > 0) {
            while(divisorL > temp && dividendL > 0) {
                int highBit = (dividendL & mask) == 0 ? 0 : 1;
                dividendL -= (dividendL & mask);
                mask >>= 1;

                temp <<= 1;
                temp += highBit;
            }

            if(divisorL <= temp) {
                result <<= 1;
                result += 1;
                temp = temp - divisorL;
            }
        }
        if(result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else return (int) result;
    }
}
