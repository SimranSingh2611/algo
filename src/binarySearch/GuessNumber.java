package binarySearch;

/*
 * Classic binary search, but on predicate
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 */

public class GuessNumber {
	/*
	 * The guess API is defined in the parent class GuessGame.
	 * @param num, your guess
	 * @return -1 if my number is lower, 1 if my number is higher, otherwise
	 * return 0
	 */
	public static int guess(int num) {
		return -1;
	}

	public int guessNumber(int n) {
		int begin = 1;
		int end = n;
		while (begin < end) {
			// Although this is identical to (begin + end)/2, always do this (or use long)
			// unless you want to get an int overflow
			int mid = begin + (end - begin) / 2;
			int result = guess(mid);
			if (result == 0) {
				return mid;
			} else if (result > 0) {
				// exclude mid in both cases since we know for sure we don't need it
				begin = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return guess(begin) == 0 ? begin : -1;
	}
}