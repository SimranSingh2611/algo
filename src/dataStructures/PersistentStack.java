package dataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PersistentStack {

    public static class Node {
        long cumMass;
        long mass;
        Node next;

        public Node(long mass) {
            this.mass = mass;
            this.cumMass = mass;
            this.next = null;
        }
    }

    public static Node versionedPush(Node head, long mass) {
        Node newHead = new Node(mass);
        newHead.cumMass = newHead.mass + (head == null ? 0 : head.cumMass);
        newHead.next = head;

        // Returning new version of the stack
        return newHead;
    }

    public static Node versionedPop(Node head) {
        // We know versionedPop will never get called on empty stack
        // So let's return new version of the stack
        return head.next;
    }
    /*
    It’s winter. Year 2222. The news is: cloning of snowmen becomes available.
    Snowman consists of zero or more snowballs put one atop another. Each snowball has some mass. Cloning
    of a snowman produces its exact copy. Andrew initially has one empty snowman and performs a sequence
    of the following operations. Clone one of his snowmen and either put a new snowball on the top of the
    new snowman; or remove the topmost snowball from the new snowman (the new snoman must be nonemtpy).
    He wants to know the total mass of all his snowmen after he performs all operations.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Node[] versions = new Node[n + 1];
        for(int i = 1; i <= n; i++) {
            String[] command = br.readLine().split(" ");
            int t = Integer.parseInt(command[0]);
            int m = Integer.parseInt(command[1]);
            if(m > 0) {
                versions[i] = versionedPush(versions[t], m);
            } else {
                versions[i] = versionedPop(versions[t]);
            }
        }
        long result = 0;
        for(Node node : versions) {
            if(node != null)
                result += node.cumMass;
        }
        System.out.println(result);
    }
}
