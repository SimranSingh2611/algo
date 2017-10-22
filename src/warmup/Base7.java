package warmup;

/*
* Given an integer, return its base 7 string representation
* The input will be in range of [-1e7, 1e7].
* https://leetcode.com/problems/base-7/
 */
public class Base7 {
    public String convertToBase7(int num) {
        int result = 0;
        int pow = 1;
        while(num != 0) {
            result += (num % 7) * pow;
            pow *= 10;
            num /= 7;
        }

        return String.valueOf(result);
    }
}
