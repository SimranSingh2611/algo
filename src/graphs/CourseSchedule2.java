package graphs;

import java.util.ArrayList;
import java.util.List;

class CourseSchedule2 {
    private boolean dfs(int cur, byte[] visited, List<List<Integer>> nodes, List<Integer> result) {
        // Mark as pending
        visited[cur] = 0x01;
        for(Integer i : nodes.get(cur)) {
            // Cycle detected
            if(visited[i] == 0x01) return false;
            else if(visited[i] != 0x02) {
                // Call DFS recursively if not visited
                boolean valid = dfs(i, visited, nodes, result);
                if(!valid) return false;
            }
        }
        // Mark as visited
        visited[cur] = 0x02;
        // Don't add the first ephemeral node
        if(cur != nodes.size() - 1) result.add(0, cur);

        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> nodes = new ArrayList<List<Integer>>();
        for(int i = 0; i <= numCourses; i++) nodes.add(new ArrayList<>());

        // Store the fact that node has no incoming edges
        boolean[] hasIncoming = new boolean[numCourses];

        for(int[] dependency : prerequisites) {
            nodes.get(dependency[1]).add(dependency[0]);
            hasIncoming[dependency[0]] = true;
        }

        byte[] visited = new byte[numCourses + 1];
        for(int i = 0; i < numCourses; i++) {
            if(!hasIncoming[i]) {
                // Create an ephemeral node that will point to nodes with no incoming edges
                nodes.get(numCourses).add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        boolean valid = dfs(numCourses, visited, nodes, result);
        // Cycles were detected or not all the courses have been taken
        if(!valid || result.size() < numCourses) return new int[]{};

        int[] res = new int[result.size()];
        for(int i = 0; i < result.size(); i++) res[i] = result.get(i);
        return res;
    }
}