package graphs.helpers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Graph<T> {
    public List<Vertex<T>> v;

    public Graph(int numberVertices, List<Integer[]> edges) {
        v = new ArrayList<>();
        for(int i = 0; i < numberVertices; i++) {
            v.add(new Vertex<T>(i + 1, i));
        }

        for(Integer[] edge : edges) {
            v.get(edge[0] - 1).neighbors.add(v.get(edge[1] - 1));
            v.get(edge[1] - 1).neighbors.add(v.get(edge[0] - 1));
        }
    }

    public static void bfs(Graph<Integer> g) {
        boolean[] visited = new boolean[g.v.size()];

        g.v.forEach(v -> {
            if(!visited[v.index]) {
                bfsInner(v, visited);
            }
        });
//        for(Vertex v : g.v) {
//            if(!visited[v.index]) {
//                bfsInner(v, visited);
//            }
//        }
    }

    public static void bfsInner(Vertex<Integer> v, boolean[] visited) {
        Deque<Vertex<Integer>> queue = new ArrayDeque<>();

        visited[v.index] = true;
        queue.add(v);

        while(!queue.isEmpty()) {
            Vertex<Integer> cur = queue.pollFirst();
            System.out.println(cur.id);

            for(Vertex<Integer> child : cur.neighbors) {
                if(!visited[child.index]) {
                    visited[child.index] = true;
                    queue.addLast(child);
                }
            }
        }
    }
}
