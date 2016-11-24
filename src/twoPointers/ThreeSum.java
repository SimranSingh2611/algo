package twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.
 * 
 * https://leetcode.com/problems/3sum/
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int N = nums.length;
        int i = 0;
        while(i < N - 1) {
            int begin = i + 1;
            int end = N - 1;
            
            while(begin < end) {
                int sum = nums[i] + nums[begin] + nums[end];
                if(sum == 0) {
                    List<Integer> triplet = new ArrayList<Integer>();
                    triplet.add(nums[i]);
                    triplet.add(nums[begin]);
                    triplet.add(nums[end]);
                    list.add(triplet);
                    
                    int num = nums[end];
                    while(end > begin && nums[end] == num) {
                        end--;
                    }
                } else if(sum < 0) {
                    int num = nums[begin];
                    while(end > begin && nums[begin] == num) {
                        begin++;
                    }
                } else {
                    int num = nums[end];
                    while(end > begin && nums[end] == num) {
                        end--;
                    }
                }
            }
            
            int num = nums[i];
            while(i < N && nums[i] == num) {
                i++;
            }
        }
        return list;
    }
}