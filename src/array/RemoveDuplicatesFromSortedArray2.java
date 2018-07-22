package array;

/*
* Remove duplicates from a sorted array in-place, max 2 duplicates are allowed
* https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class RemoveDuplicatesFromSortedArray2 {
    public int removeDuplicates(int[] nums) {
        int insertionPos = 0;
        int prev = 1;
        int counter = 0;
        for(int i = 0; i < nums.length; i++) {
            if(counter == 0 || prev != nums[i]) {
                counter = 0;
                prev = nums[i];
            }
            if(counter < 2) {
                nums[insertionPos++] = prev;
                counter++;
            }
        }
        return insertionPos;
    }
}
