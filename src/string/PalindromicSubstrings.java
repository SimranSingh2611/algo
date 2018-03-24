package string;

/*
* Calculate the number of palindromic substrings of a given string.
* https://leetcode.com/problems/palindromic-substrings/
 */

public class PalindromicSubstrings {
    private int countPalindromes(int left, int right, String s) {
        int count = 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
    public int countSubstrings(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            // Single character is palindrome by definition
            count++;
            // count both even and odd palindromes starting at (i) and (i, i+1)
            count += countPalindromes(i - 1, i + 1, s) + countPalindromes(i, i + 1, s);
        }
        return count;
    }
}
