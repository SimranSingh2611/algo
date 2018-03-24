package string;

/*
* Classic window problem
* https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters
 */

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringAtMost2DistinctCharacters {

    // (Much) slower version, remember that HashMap has a large constant factor to its operations
    public int lengthOfLongestSubstringTwoDistinctSlow(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int maxWidth = 0, curWidth = 0;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(window.containsKey(ch)) window.put(ch, window.get(ch) + 1);
            else window.put(ch, 1);
            curWidth++;

            while(window.size() > 2) {
                char toTrim = s.charAt(i - curWidth + 1);
                curWidth--;
                if(window.get(toTrim) == 1) window.remove(toTrim);
                else window.put(toTrim, window.get(toTrim) - 1);
            }

            maxWidth = Math.max(maxWidth, curWidth);
        }
        return maxWidth;
    }

    // Fast version, no hashing involved
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] counters = new int[256];
        int maxWidth = 0, curWidth = 0, distinct = 0;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            counters[ch]++;
            if(counters[ch] == 1) distinct++;
            curWidth++;

            while(distinct > 2) {
                char toTrim = s.charAt(i - curWidth + 1);
                curWidth--;
                counters[toTrim]--;
                if(counters[toTrim] == 0) distinct--;
            }

            maxWidth = Math.max(maxWidth, curWidth);
        }
        return maxWidth;
    }
}
