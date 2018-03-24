package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* https://leetcode.com/problems/find-eventual-safe-states/
 */

public class EventuallySafeStates {
    // "white-grey-black" DFS
    private boolean traverse(int[][] graph, int curNode, int[] visited, List<Integer> result) {
        visited[curNode] = 1;

        for(int neighbour : graph[curNode]) {
            if(visited[neighbour] == 1) return true;
            if(visited[neighbour] == 2) continue;

            boolean cycleFound = traverse(graph, neighbour, visited, result);
            if(cycleFound) return true;
        }

        result.add(curNode);
        visited[curNode] = 2;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(visited[i] == 0) traverse(graph, i, visited, result);
        }
        Collections.sort(result);
        return result;
    }
}
