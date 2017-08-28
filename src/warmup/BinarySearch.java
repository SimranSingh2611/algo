package warmup;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 *
 * https://leetcode.com/problems/search-insert-position/description/
 */
public class BinarySearch {
    public int searchInsert(int[] nums, int target) {
        int begin = 0, end = nums.length - 1;
        while(begin < end) {
            // Be careful - we need ceiling here to avoid potential infinite loop
            // By default double to int casting in Java rounds by truncating (IEEE 754 round-toward-zero)
            int mid = (int) Math.ceil( (begin - end)/2.0 ) + end;
            if(nums[mid] > target)
                end = mid - 1;
            else if(nums[mid] <= target)
                begin = mid;
            else
                return mid;
        }
        if(nums[begin] < target)
            return begin + 1;
        else
            return begin;
    }
    public static void main(String[] args) {
        System.out.println((int) 11.5);
    }
}