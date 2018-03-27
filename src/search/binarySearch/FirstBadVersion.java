package search.binarySearch;

/**
 * https://leetcode.com/problems/first-bad-version
 * Binary search on predicate
 */
public class FirstBadVersion extends VersionControl {
    public FirstBadVersion(boolean[] versions) {
        super(versions);
    }

    public int firstBadVersion(int n) {
        int begin = 1, end = n;
        while(begin < end) {
            int mid = begin + (int) Math.floor((end - begin)/2.0);
            /**
             * Sometimes you need control over which element will be chosen as mid in an array of two [x_i, x_i+1],
             * in this case floor is not needed since 1/2 will give you 0 in Java.
             * However, in some cases you might want to get ceil, in that case you want to be explicit.
             * int mid = begin + (end - begin)/2; is not explicit enough
             * Want to check how rounding affects binary search algorithm? try using ceil instead of floor in this example.
              */
            if(isBadVersion(mid)) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}