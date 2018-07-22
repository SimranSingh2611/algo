package dataStructures;

import java.io.*;
import java.util.*;

/**
 * Disjoint Set Union task from Hackerrank, improved using path compression
 * https://www.hackerrank.com/challenges/merging-communities
 */
public class MergingCommunitiesImproved {
    public static class Node {
        public int value;
        public int size;
        public Node parent;

        public Node(int value) {
            this.value = value;
            parent = null;
            size = 1;
        }
    }

    public static Node getParent(Node n) {
        if(n.parent == null) return n;
        n.parent = getParent(n.parent);
        return n.parent;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nq = br.readLine().split(" ");
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);
        Map<Integer, Node> map = new HashMap<Integer, Node>();
        for(int i = 0; i < n; i++) {
            Node node = new Node(i + 1);
            map.put(i + 1, node);
        }

        for(int i = 0; i < q; i++) {
            String[] query = br.readLine().split(" ");
            if(query.length > 2) {
                int index1 = Integer.parseInt(query[1]);
                int index2 = Integer.parseInt(query[2]);

                Node n1 = getParent(map.get(index1));
                Node n2 = getParent(map.get(index2));

                if(n2 != n1) {
                    n2.parent = n1;
                    n1.size += n2.size;
                }
            } else {
                int index = Integer.parseInt(query[1]);
                Node n1 = map.get(index);
                Node parent = getParent(n1);
                System.out.println(parent.size);
            }
        }
    }
}