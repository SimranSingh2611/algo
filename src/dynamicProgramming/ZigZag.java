package dynamicProgramming;

/**
 * ZigZag
 * https://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493
 */
public class ZigZag {
    /*
    * Marginal case: O(N^2) dp with single parameter
     */
    public static int longestZigZag(int[] sequence) {
        int len = sequence.length;
        if(len < 2) {
            return 1;
        }

        int[] dp = new int[len];
        // Array to store the last difference between numbers
        // diff[i] = 1 if length of the optimal zigzag sequence is
        // more than 1 and the last difference was positive. Note that
        // boolean array is not enough since we need to have a case when
        // no difference exists (in case length of zigzag sequence is 1)
        int[] diff = new int[len];
        dp[0] = 1;
        diff[0] = 0;

        for(int i = 1; i < len; i++) {
            dp[i] = 1;
            diff[i] = 0;
            for(int j = i - 1; j >= 0; j--) {
                // in case there was no previous difference and elements
                // are not equal
                if(diff[j] == 0 && sequence[i] != sequence[j]) {
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        diff[i] = sequence[j] < sequence[i] ? 1 : -1;
                    }
                }
                // there was a previously observed difference
                else if( (sequence[j] < sequence[i] && diff[j] == -1) ||
                         (sequence[j] > sequence[i] && diff[j] == 1) ) {
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        diff[i] = -diff[j];
                    }
                }
            }
        }
        return dp[len - 1];
    }
}
