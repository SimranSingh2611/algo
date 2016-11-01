package DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Disjoint Set Union task from Hackerrank
 * https://www.hackerrank.com/challenges/merging-communities
 */
public class MergingCommunities {

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nq = br.readLine().split(" ");
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);
        Map<Integer, Node> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            Node node = new Node(i + 1);
            map.put(i + 1, node);
        }

        for(int i = 0; i < q; i++) {
            String[] query = br.readLine().split(" ");
            if(query.length > 2) {
                int index1 = Integer.parseInt(query[1]);
                int index2 = Integer.parseInt(query[2]);

                Node n1 = map.get(index1);
                Node n2 = map.get(index2);

                while(n1.parent != null) {
                    n1 = n1.parent;
                }

                while(n2.parent != null) {
                    n2 = n2.parent;
                }

                if(n2 != n1) {
                    // Not the best way to make the tree balanced
                    // Worst case is height of N, which is linked list
                    Random randomno = new Random();
                    boolean base = randomno.nextBoolean();
                    if(base) {
                        n2.parent = n1;
                        n1.size += n2.size;
                    } else {
                        n1.parent = n2;
                        n2.size += n1.size;
                    }
                }
            } else {
                int index = Integer.parseInt(query[1]);
                Node n1 = map.get(index);
                if(n1.parent == null) {
                    System.out.println(n1.size);
                } else {
                    Node n2 = n1;
                    while(n2.parent != null) {
                        n2 = n2.parent;
                    }
                    n1.parent = n2;
                    System.out.println(n2.size);
                }
            }
        }
    }
}
