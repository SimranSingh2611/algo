package graphs.trees;

/**
 *
 * https://leetcode.com/problems/redundant-connection/
 */
class RedundantConnection {
    public static class Node {
        int id;
        Node parent;

        public Node(int id) {
            this.id = id;
            parent = null;
        }
    }

    private Node getParent(Node n) {
        if(n.parent == null) return n;
        return n.parent = getParent(n.parent);
    }

    private boolean unite(Node n1, Node n2) {
        Node parent1 = getParent(n1);
        Node parent2 = getParent(n2);
        if(parent1 == parent2) return false;
        parent2.parent = parent1;
        return true;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int N = edges.length;

        Node[] nodes = new Node[N];
        for(int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }

        int notTaken = -1;
        for(int i = 0; i < N; i++) {
            Node n1 = nodes[edges[i][0] - 1];
            Node n2 = nodes[edges[i][1] - 1];
            boolean united = unite(n1, n2);
            if(!united) {
                notTaken = i;
                break;
            }
        }

        return edges[notTaken];
    }
}
