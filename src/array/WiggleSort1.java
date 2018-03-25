package array;

import java.util.Arrays;

/*
* https://leetcode.com/problems/wiggle-sort/
 */
public class WiggleSort1 {
    private static void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

    // Fastest way, no sorting needed, in-place and linear in time
    public static void wiggleSortFast(int[] nums) {
        boolean lessOrEquals = true;
        for(int i = 0; i < nums.length - 1; i++) {
            if(lessOrEquals && nums[i] > nums[i + 1])
                swap(nums, i, i + 1);
            if(!lessOrEquals && nums[i] < nums[i + 1])
                swap(nums, i, i + 1);
            lessOrEquals = !lessOrEquals;
        }
    }

    // Very simple and readable solution, yet it's O(N * logN) because of sorting
    public static void wiggleSortSimple(int[] nums) {
        Arrays.sort(nums);
        for(int i = 2; i < nums.length; i+= 2) {
            swap(nums, i, i - 1);
        }
    }
}
