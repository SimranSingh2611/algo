package dynamicProgramming;

/**
 * Bad Neighbors
 * https://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009
 */
public class BadNeighbors {
    public static int[] revert(int[] array) {
        int limit = Math.floorDiv(array.length, 2);
        for (int i = 0; i < limit; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }

/*
    Problem arises when we are checking the last Nth neighbor,
    he has limitations from both N-1st and first neighbor. It might
    be worth it taking Nth element instead of first, but note that
    simple swapping of first and Nth won't work:
    {5, 4, 1, 1, 10}
    Going from left to right gives us 5 + 1
    Swapping Nth and first gives us  10 + 1
    But the best sum is 4 + 10, so we need to recompute the whole thing.
    Hence, we will compute from left to right and from right to left.
*/
    public static int maxDonations(int[] donations) {
        return Math.max(oneWayDonations(donations), oneWayDonations(revert(donations)));
    }

    public static int oneWayDonations(int[] donations) {
        int N = donations.length;
        int result = 0;
        if (N == 0) {
            return result;
        }
        int[] dp = new int[N + 2];
        boolean[] firstTaken = new boolean[N + 2];
        dp[0] = 0;
        dp[1] = 0;
        firstTaken[0] = false;
        firstTaken[1] = false;

        for (int i = 2; i <= N + 1; i++) {
            dp[i] = 0;
            for (int j = 0; j < i - 1; j++) {
                if (firstTaken[j] && i == N + 1) {
                    continue;
                }

                if (dp[i] < dp[j] + donations[i - 2]) {
                    dp[i] = dp[j] + donations[i - 2];
                    if (i == 2 || firstTaken[j]) {
                        firstTaken[i] = true;
                    }
                }
            }
            if (dp[i] > result) {
                // Corner case when them best sum is not in the last two dp cells:
                // {5, 1, 1}
                result = dp[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] don = {94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,
                6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
                52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72};
        System.out.println(Math.max(maxDonations(don), maxDonations(revert(don))));
    }
}
