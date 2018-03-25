package array.sort;

import java.util.Arrays;

/*
* Given an unsorted array of numbers 0, 1 and 2 only, sort it in place and in linear time
 */
public class DutchNationalFlag {
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void arrange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(nums[left] == 0) left++;
        while(nums[right] == 2) right--;

        for(int mid = left; mid <= right; mid++) {
            if(nums[mid] == 0) {
                swap(nums, left++, mid--);
            } else if(nums[mid] == 2) {
                swap(nums, right--, mid--);
            }
        }
    }
    public static void main(String[] args) {
        int[] colors = new int[]{0, 1, 2, 2, 1, 1, 0, 2, 0, 2, 1, 2, 0, 2, 1, 2, 0, 0, 1, 2};
        int[] sortedColors = (int[]) colors.clone();
        Arrays.sort(sortedColors);
        arrange(colors);
    }
}
