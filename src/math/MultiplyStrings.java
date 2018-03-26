package math;
/*
* Multiply two arbitrary size numbers encoded in two strings
* https://leetcode.com/problems/multiply-strings/
* https://www.interviewbit.com/problems/multiply-strings/
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        int nm = n + m;
        int[] res = new int[nm];

        int offset = 0;
        for(int i = num1.length() - 1; i >= 0; i--) {
            if(num1.charAt(i) == '0') {
                offset++;
                continue;
            }

            int carry = 0;
            for(int j = num2.length() - 1; j >= 0; j--) {
                int mult = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + carry;
                // indices look crappy since it's reverse order
                int index = nm - 1 - offset - (m - 1 - j);
                carry = mult / 10;
                int newVal = res[index] + (mult % 10);
                res[index] = newVal % 10;
                carry += newVal / 10;
            }
            if(carry > 0) {
                int index = nm - 1 - offset - m;
                res[index] = carry;
            }
            offset++;
        }

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < nm; i++) {
            if(result.length() == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        if(result.length() == 0) return "0";
        else return result.toString();
    }
}