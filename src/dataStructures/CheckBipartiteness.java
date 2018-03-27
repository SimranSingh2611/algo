package dataStructures;

import java.util.ArrayDeque;
import java.util.Deque;

/*
* Greedy BFS coloring. Remember that graph can be disconnetcted
* https://leetcode.com/problems/is-graph-bipartite/
 */
public class CheckBipartiteness {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        if(n < 2) return true;
        int[] color = new int[n];

        for(int i = 0; i < n; i++) {
            if(color[i] == 0) {
                Deque<Integer> queue = new ArrayDeque<>();
                queue.addLast(i);
                color[i] = 1;

                while(!queue.isEmpty()) {
                    int cur = queue.removeFirst();
                    for(int neigh : graph[cur]) {
                        if(color[neigh] == 0) {
                            color[neigh] = -color[cur];
                            queue.addLast(neigh);
                        }
                        else if(color[neigh] == color[cur]) return false;
                    }
                }
            }
        }

        return true;
    }
}
