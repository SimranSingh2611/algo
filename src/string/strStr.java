package string;

/**
 * Search for substring occurrence in a string
 * https://leetcode.com/problems/implement-strstr/description/
 * Solved with KMP algorithm, 2 implementations
 */
class strStr {
    public int search(String haystack, String needle) {
        if("".equals(needle)) return 0;

        int[] kmp = new int[needle.length()];
        kmp[0] = 0;
        for(int i = 1; i < needle.length(); i++) {
            int index = kmp[i - 1];
            while(true) {
                if(needle.charAt(i) == needle.charAt(index)) {
                    kmp[i] = index + 1;
                    break;
                }

                if(index == 0) break;
                else index = kmp[index - 1];
            }
        }


        int counter = 0;
        for(int i = 0; i < haystack.length(); i++) {
            int index = counter;
            while(true) {
                if(haystack.charAt(i) == needle.charAt(index)) {
                    counter = index + 1;

                    if(counter == needle.length()) {
                        return i - needle.length() + 1;
                    }
                    break;
                }

                if(index == 0) {
                    counter = 0;
                    break;
                }
                else index = kmp[index - 1];
            }
        }

        return -1;
    }

    // Concatenating with terminator char simplifies the implementation, but requires extra auxiliary space
    public int search2(String haystack, String needle) {
        if("".equals(needle)) return 0;
        char terminator = '#';
        String composite = needle + terminator + haystack;
        int[] kmp = new int[composite.length()];
        kmp[0] = 0;
        for(int i = 1; i < kmp.length; i++) {
            int index = i - 1;
            while(index >= 0) {
                if(composite.charAt(i) == composite.charAt(kmp[index])) {
                    kmp[i] = kmp[index] + 1;
                    if(kmp[i] == needle.length()) return i - 2 * needle.length();
                    break;
                }
                index = kmp[index] - 1;
            }
        }

        return -1;
    }
}
