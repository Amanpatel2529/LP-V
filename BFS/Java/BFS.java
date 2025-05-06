import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

public class BFS {

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();

            if (temp.left == null) {
                temp.left = new Node(data);
                return root;
            } else {
                q.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = new Node(data);
                return root;
            } else {
                q.add(temp.right);
            }
        }
        return root;
    }

    public static void bfs(Node root) {
        if (root == null) return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();
            System.out.print(curr.data + " ");

            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node root = null;
        char choice;

        do {
            System.out.print("Enter data: ");
            int data = sc.nextInt();
            root = insert(root, data);
            System.out.print("Add another node? (y/n): ");
            choice = sc.next().charAt(0);
        } while (choice == 'y' || choice == 'Y');

        System.out.println("BFS Traversal:");
        bfs(root);
    }
}
