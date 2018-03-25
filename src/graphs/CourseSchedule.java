package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Classic topological sorting problem, "white-grey-black" DFS solution
 * https://leetcode.com/problems/course-schedule-ii/
 */

public class CourseSchedule {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] visited = new int[numCourses];
        Map<Integer, List<Integer>> dependencies = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < numCourses; i++) {
            dependencies.put(i, new ArrayList<>());
            visited[i] = 0;
        }
        for(int i = 0; i < prerequisites.length; i++) {
            List<Integer> cur = dependencies.get(prerequisites[i][1]);
            cur.add(prerequisites[i][0]);
            dependencies.put(prerequisites[i][1], cur);
        }
        List<Integer> sorted = new ArrayList<>();
        int[] empty = {};

        // We don't know the entry points of this graph so we check all        
        for(int i = 0; i < numCourses; i++) {
            if(visited[i] == 0) {
                boolean result = topsort(i, dependencies, visited, sorted);
                if(!result) {
                    return empty;
                }
            }
        }
        
        // Reformat to an array
        int[] answer = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            answer[i] = sorted.get(i);
        }
        return answer;
    }
    
    public static boolean topsort(int start, Map<Integer, List<Integer>> depend, int[] visited, List<Integer> sorted) {
        if(visited[start] == 1) {
            return false;
        } else if(visited[start] == 2) {
            return true;
        } else {
            visited[start] = 1;
            List<Integer> cur = depend.get(start);
            
            for(Integer i : cur) {
                boolean result = topsort(i, depend, visited, sorted);
                if(!result) {
                    return result;
                }
            }
            visited[start] = 2;

            // Get a proper order (add-first)
            sorted.add(0, start);
            return true;
        }
    }
}