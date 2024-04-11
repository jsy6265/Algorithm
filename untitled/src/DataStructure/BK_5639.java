package DataStructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BK_5639 {
    static class Node {
        int num;
        Node left, rigth, perant;

        public Node(int num, Node left, Node rigth, Node perant) {
            this.num = num;
            this.left = left;
            this.rigth = rigth;
            this.perant = perant;
        }

        public Node(int num) {
            this.num = num;
        }

        public void addNode(int newNum) {
            if (newNum < num) {
                if (left == null) {
                    left = new Node(newNum);
                } else {
                    left.addNode(newNum);
                }
            } else {
                if (rigth == null) {
                    rigth = new Node(newNum);
                } else {
                    rigth.addNode(newNum);
                }
            }
        }
    }

    static Node root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> nodes = new ArrayList<>();
        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.equals(""))
                break;
            nodes.add(Integer.parseInt(input));
        }

        root = new Node(nodes.get(0));

        makeNode(nodes);

        postorderTraversal(root);

    }

    private static void postorderTraversal(Node node) {

        if (node.left != null) {
            postorderTraversal(node.left);
        }
        if (node.rigth != null) {
            postorderTraversal(node.rigth);
        }
        System.out.println(node.num);
    }

    private static void makeNode(List<Integer> nodes) {

        for (int i = 1; i < nodes.size(); i++) {
            int temp = nodes.get(i);
            root.addNode(temp);
        }
    }

}
