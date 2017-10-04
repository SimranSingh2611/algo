package dataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Treap {
    public static class QueryResult {
        String result;
    }

    public static class Node {
        int key;
        int size;
        long priority;
        Node left;
        Node right;
        static Random rand;
        static {
            rand = new Random();
        }
        public Node(int key) {
            this.size = 1;
            this.key = key;
            this.priority = rand.nextLong();
            this.left = null;
            this.right = null;
        }
        private void update() {
            this.size = 1 + (this.right != null ? this.right.size : 0) + (this.left != null ? this.left.size : 0);
        }
    }

    public static Node[] split(Node root, int key) {
        Node[] LR = new Node[]{null, null};
        if(root == null)
            return LR;
        if(root.key <= key) {
            LR = split(root.right, key);
            root.right = null;
            root.update();
            LR[0] = merge(root, LR[0]);
        } else {
            // root.key > key
            LR = split(root.left, key);
            root.left = null;
            root.update();
            LR[1] = merge(LR[1], root);
        }
        return LR;
    }

    public static Node merge(Node root1, Node root2) {
        if(root1 == null)
            return root2;
        if(root2 == null)
            return root1;
        long priority1 = root1.priority;
        long priority2 = root2.priority;

        if(priority1 > priority2) {
            Node newRight = merge(root1.right, root2);
            root1.right = newRight;
            root1.update();
            return root1;
        } else {
            Node newLeft = merge(root1, root2.left);
            root2.left = newLeft;
            root2.update();
            return root2;
        }
    }

    public static Node insert(Node root, int key) {
        Node LR[] = split(root, key);
        Node newNode = new Node(key);
        return merge(merge(LR[0], newNode), LR[1]);
    }

    public static Node delete(Node root, int key) {
        Node LR[] = split(root, key);
        Node cutoff[] = split(LR[0], key - 1);
        return merge(cutoff[0], LR[1]);
    }

    public static Node exists(Node root, int key, QueryResult queryResult) {
        Node[] LR = split(root, key);
        Node cur = LR[0];
        if(cur == null)
            queryResult.result = "false";
        else {
            while (cur.right != null) {
                cur = cur.right;
            }
            queryResult.result = cur.key == key ? "true" : "false";
        }
        return merge(LR[0], LR[1]);
    }

    public static Node next(Node root, int key, QueryResult queryResult) {
        Node[] LR = split(root, key);
        Node cur = LR[1];
        if(cur == null)
            queryResult.result = "none";
        else {
            while (cur.left != null) {
                cur = cur.left;
            }
            queryResult.result = cur.key + "";
        }
        return merge(LR[0], LR[1]);
    }

    public static Node prev(Node root, int key, QueryResult queryResult) {
        Node[] LR = split(root, key - 1);
        Node cur = LR[0];
        if(cur == null)
            queryResult.result = "none";
        else {
            while (cur.right != null) {
                cur = cur.right;
            }
            queryResult.result = cur.key + "";
        }
        return merge(LR[0], LR[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = null;

        String l = br.readLine();
        QueryResult queryResult = new QueryResult();
        while(l != null) {
            String[] command = l.split(" ");

            int arg = Integer.parseInt(command[1]);
            if("insert".equals(command[0])) {
                root = insert(root, arg);
            } else if("delete".equals(command[0])) {
                root = delete(root, arg);
            } else if("exists".equals(command[0])) {
                root = exists(root, arg, queryResult);
                System.out.println(queryResult.result);
            } else if("next".equals(command[0])) {
                root = next(root, arg, queryResult);
                System.out.println(queryResult.result);
            } else if("prev".equals(command[0])) {
                root = prev(root, arg, queryResult);
                System.out.println(queryResult.result);
            }
            l = br.readLine();
        }
    }
}
