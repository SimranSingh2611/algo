package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 *
 * http://poj.org/problem?id=3185
 * My code from 2015
 *
 */
public class WaterBowls {

    public static class Bowl {
        public int bowls;
        public int steps;
        public Bowl(int bowls, int steps) {
            this.bowls = bowls;
            this.steps = steps;
        }
    }

    public static int countMinSteps(int bowls) {
        int size = 20;
        boolean[] visited = new boolean[(int)Math.pow(2, 21) - 1];
        LinkedList<Bowl> list = new LinkedList<Bowl>();
        visited[bowls] = true;
        list.add(new Bowl(bowls, 0));
        return dfs(size, visited, list, 0);

    }

    private static int dfs(int size, boolean[] visited, LinkedList<Bowl> queue, int goal) {
        while(queue.size() > 0) {
            Bowl bowl = queue.removeFirst();
            if(bowl.bowls == goal) {
                return bowl.steps;
            } else {
                // special cases
                int begin = left ^ bowl.bowls;
                if(!visited[begin]) {
                    visited[begin] = true;
                    queue.addLast(new Bowl(begin, bowl.steps + 1));
                }

                int flip = 7;
                for(int i = 0; i < size - 2; i++) {
                    int cur = flip ^ bowl.bowls;
                    if(!visited[cur]) {
                        visited[cur] = true;
                        queue.addLast(new Bowl(cur, bowl.steps + 1));
                    }
                    flip = flip << 1;
                }

                //special case 2
                int end = 3 ^ bowl.bowls;
                if(!visited[end]) {
                    visited[end] = true;
                    queue.addLast(new Bowl(end, bowl.steps + 1));
                }
            }
        }
        return -1;
    }

    private static int left = Integer.parseInt("11000000000000000000", 2);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int bowls = 0;
        for(int i = 0; i < str.length; i++) {
            bowls = bowls << 1;
            bowls = bowls + (Character.getNumericValue(str[i].charAt(0)));
        }
        System.out.println(countMinSteps(bowls));
    }
}
