package graphs;

import graphs.helpers.Graph;
import graphs.helpers.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class ArticulationPoints {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            Integer num = Integer.parseInt(br.readLine());

            if(num == 0) {
                break;
            } else {
                List<Integer[]> edges = new ArrayList<>();

                while(true) {
                    String x = br.readLine();
                    if(x.equals("0")) {
                        break;
                    }

                    String[] items = x.split(" ");
                    Integer base = Integer.parseInt(items[0]);
                    for(int i = 1; i < items.length; i++) {
                        Integer[] edge = new Integer[2];
                        edge[0] = base;
                        edge[1] = Integer.parseInt(items[i]);
                        edges.add(edge);
                    }
                }
                Graph<Integer> g = new Graph<>(num, edges);
                System.out.println(getArticulationVertices(g).size());
            }
        }
    }

    public static boolean[] visited;
    public static int timer;
    public static int[] tin;
    public static int[] fup;

    public static Set<Integer> getArticulationVertices(Graph<Integer> g) {
        Set<Integer> articulationPoints = new HashSet<>();

//        g.v.forEach(v -> {
//            if(!visited[v.index]) {
//                bfsInner(v, visited);
//            }
//        });
        int n = g.v.size();
        visited = new boolean[n];
        tin = new int[n];
        fup = new int[n];
        timer = 0;

        for(Vertex v : g.v) {
            if(!visited[v.index]) {
                articulationPointsInner(v, null, articulationPoints);
            }
        }

        return articulationPoints;
    }

    public static void articulationPointsInner(Vertex<Integer> v, Vertex<Integer> parent, Set<Integer> articulationPoints) {
        visited[v.index] = true;
        tin[v.index] = fup[v.index] = timer++;
        int children = 0;

        for(Vertex<Integer> child : v.neighbors) {
            Vertex to = child;
            if(to == parent) {
                continue;
            }

            if(visited[to.index]) {
                fup[v.index] = Math.min(fup[v.index], tin[to.index]);
            } else {
                articulationPointsInner(to, v, articulationPoints);
                fup[v.index] = Math.min(fup[v.index], fup[to.index]);
                if((fup[to.index] >= tin[v.index]) && parent != null) {
                    articulationPoints.add(v.id);
                }
                children++;
            }
        }
        if(parent == null && children > 1) {
            articulationPoints.add(v.id);
        }
    }
}
