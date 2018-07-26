package search.binarySearch;

/**
 * https://leetcode.com/problems/peak-index-in-a-mountain-array
 *
 * A really nice problem that can be solved using binary search.
 * Strictly speaking, it wouldn't have been possible to solve it with binary search if not the fact that
 * this function is strictly increasing up till some point and then it is strictly decreasing. That means
 * at any point in time we can get a slope and understand which direction are we moving (uphill or downhill).
 * Once we realize that, the rest is a trivial binary search.
 */
class PeakIndex {
    public int peakIndexInMountainArray(int[] A) {
        int start = 0, end = A.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start)/2;
            if(A[mid] - A[mid - 1] > 0) start = mid;
            else end = mid;
        }
        return A[start] > A[end] ? start : end;
    }
}
