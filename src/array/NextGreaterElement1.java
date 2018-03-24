package array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
* https://leetcode.com/problems/next-greater-element-i/
 */

public class NextGreaterElement1 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> answer = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for(int val : nums2) {
            while(!stack.isEmpty() && stack.peek() < val) {
                int head = stack.pop();
                answer.put(head, val);
            }
            stack.push(val);
        }

        for(int i = 0; i < nums1.length; i++) {
            int val = nums1[i];
            if(answer.containsKey(val)) nums1[i] = answer.get(val);
            else nums1[i] = -1;
        }
        return nums1;
    }
}
