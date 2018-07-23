package graphs.trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Facebook Hackercup 2018, Round 1: "Ethan traverses a tree"
 * https://www.facebook.com/hackercup/problem/232395994158286/
 */
public class EthanTraversesATree {
    public static class Node {
        int id;
        Node left;
        Node right;

        public Node(int id) {
            this.id = id;
            left = null;
            right = null;
        }
    }

    public static class DSUNode {
        int labeled;
        DSUNode parent;

        public DSUNode() {
            labeled = -1;
            parent = null;
        }
    }

    public static DSUNode getParent(DSUNode n) {
        if(n.parent == null) return n;
        n.parent = getParent(n.parent);
        return n.parent;
    }

    public static void merge(DSUNode n1, DSUNode n2) {
        DSUNode p1 = getParent(n1);
        DSUNode p2 = getParent(n2);

        if(p1 != p2) p2.parent = p1;
    }

    private static void getPreOrder(Node node, List<Integer> preOrder) {
        preOrder.add(node.id);
        if(node.left != null) getPreOrder(node.left, preOrder);
        if(node.right != null) getPreOrder(node.right, preOrder);
    }

    private static void getPostOrder(Node node, List<Integer> postOrder) {
        if(node.left != null) getPostOrder(node.left, postOrder);
        if(node.right != null) getPostOrder(node.right, postOrder);
        postOrder.add(node.id);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ethan_traverses_a_tree.txt"));
        int T = Integer.valueOf(br.readLine());
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        for (int t = 0; t < T; t++) {
            String[] NK = br.readLine().split(" ");
            int N = Integer.parseInt(NK[0]);
            int K = Integer.parseInt(NK[1]);
            Node[] nodes = new Node[N];
            for(int i = 0; i < N; i++) nodes[i] = new Node(i);
            for(int i = 0; i < N; i++) {
                String[] AB = br.readLine().split(" ");
                int A = Integer.parseInt(AB[0]);
                int B = Integer.parseInt(AB[1]);

                if(A > 0) nodes[i].left = nodes[A - 1];
                if(B > 0) nodes[i].right = nodes[B - 1];
            }

            List<Integer> preOrder = new ArrayList<>();
            getPreOrder(nodes[0], preOrder);
            List<Integer> postOrder = new ArrayList<>();
            getPostOrder(nodes[0], postOrder);

            // DSU + coloring info in the parent node
            DSUNode[] dsuNodes = new DSUNode[N];
            for(int i = 0; i < N; i++) dsuNodes[i] = new DSUNode();

            for(int i = 0; i < N; i++) {
                int ai = preOrder.get(i);
                int bi = postOrder.get(i);
                merge(dsuNodes[ai], dsuNodes[bi]);
            }

            int counter = 0;
            for(int i = 0; i < N; i++) {
                DSUNode parent = getParent(dsuNodes[i]);
                if(parent.labeled < 0) {
                    counter++;
                    parent.labeled = Math.min(counter, K);
                }
            }

            if(counter < K) writer.println(String.format("Case #%d: %s", (t + 1), "Impossible"));
            else {
                List<String> labels = new ArrayList<>();
                for(int i = 0; i < N; i++) {
                    DSUNode parent = getParent(dsuNodes[i]);
                    labels.add(String.valueOf(parent.labeled));
                }
                writer.println(String.format("Case #%d: %s", (t + 1), String.join(" ", labels)));
            }
        }
        writer.close();
    }
}

