package dataStructures;

import java.io.*;
import java.util.*;

/**
 * Disjoint Set Union task from Hackerrank, improved using path compression
 * https://www.hackerrank.com/challenges/merging-communities
 */
public class MergingCommunitiesImproved {
    public static class Node {
        public int size;
        public Node parent;

        public Node() {
            parent = null;
            size = 1;
        }
    }

    public static Node getParent(Node n) {
        if(n.parent == null) return n;
        n.parent = getParent(n.parent);
        return n.parent;
    }

    public static boolean merge(Node n1, Node n2) {
        Node p1 = getParent(n1);
        Node p2 = getParent(n2);

        if(p1 != p2) {
            p2.parent = p1;
            p1.size += p2.size;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nq = br.readLine().split(" ");
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);
        Map<Integer, Node> map = new HashMap<Integer, Node>();
        for(int i = 0; i < n; i++) {
            Node node = new Node();
            map.put(i + 1, node);
        }

        for(int i = 0; i < q; i++) {
            String[] query = br.readLine().split(" ");
            if(query.length > 2) {
                int index1 = Integer.parseInt(query[1]);
                int index2 = Integer.parseInt(query[2]);

                merge(map.get(index1), map.get(index2));
            } else {
                int index = Integer.parseInt(query[1]);
                Node n1 = map.get(index);
                Node parent = getParent(n1);
                System.out.println(parent.size);
            }
        }
    }
}