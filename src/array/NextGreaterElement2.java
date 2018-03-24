package array;

import java.util.ArrayDeque;
import java.util.Deque;

/*
* Same to NextGreaterElement1 but with a circular array instead of the usual one
* https://leetcode.com/problems/next-greater-element-ii/
 */

public class NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        for(int i = 0; i < n; i++) answer[i] = -1;
        Deque<Integer[]> stack = new ArrayDeque<>();

        int counter = 0, index = 0;
        // We need to iterate over all the values for every element, including the last one
        // For the last element we start at index n - 1 and need to iterate another full circle
        while(counter < 2 * n) {
            while(!stack.isEmpty() && nums[index] > stack.peek()[0]) {
                Integer[] item = stack.pop();
                if(answer[item[1]] == -1) answer[item[1]] = nums[index];
            }
            stack.push(new Integer[]{nums[index], index});

            counter++;
            index++;
            if(index == n) index = 0;
        }

        return answer;
    }
}
