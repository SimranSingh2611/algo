package search.warmup;
/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * There is one clarification that you might want to know:
 * - there might be duplicates and counting the same element twice is NOT ok (might impact some solutions)
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted
 */
public class SortedTwoSum {
    public int[] twoSum(int[] nums, int target) {
        int i1 = 0, i2 = nums.length - 1;
        while(i1 < i2) {
            int sum = nums[i1] + nums[i2];
            if(sum < target)
                i1++;
            else if(sum > target)
                i2--;
            else
                return new int[]{i1 + 1, i2 + 1};
        }
        return new int[]{-1, -1};
    }
}