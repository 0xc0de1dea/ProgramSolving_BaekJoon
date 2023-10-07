/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

 import java.util.ArrayList;

class Tree {
    Node[] nodes;
    int diameter;

    public Tree(int n){
        nodes = new Node[n + 1];
        diameter = 0;
    }

    class Node {
        int id;
        int weight;
        ArrayList<Node> children = new ArrayList<>();

        public Node(int id, int weight){
            this.id = id;
            this.weight = weight;
        }

        public void addChildren(Node node){
            children.add(node);
        }
    }

    public void addNode(int id, int weight){
        Node node = new Node(id, weight);
        nodes[id] = node;
    }

    public int postOrder(Node node){
        int sz = node.children.size();

        if (sz == 0) return 0;

        int max = 0;
        int[] weights = new int[sz];

        for (int i = 0; i < sz; i++){
            weights[i] = postOrder(node.children.get(i)) + node.children.get(i).weight;
            max = Math.max(max, weights[i]);
        }
        if (sz == 1) diameter = Math.max(diameter, weights[0]);
        else {
            for (int i = 0; i < sz - 1; i++){
                for (int j = i + 1; j < sz; j++){
                    diameter = Math.max(diameter, weights[i] + weights[j]);
                }
            }
        }

        return max;
    }

    public int getDiameter(Node node){
        postOrder(node);
        return diameter;
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        Tree tree = new Tree(n);

        for (int i = 1; i <= n; i++) tree.addNode(i, 0);
        for (int i = 1; i < n; i++){
            int parent = in.nextInt();
            int child = in.nextInt();
            int weight = in.nextInt();
            tree.nodes[child].weight = weight;
            tree.nodes[parent].addChildren(tree.nodes[child]);
        }

        System.out.print(tree.getDiameter(tree.nodes[1]));
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) <= 32);
        do sb.appendCodePoint(c);
        while ((c = read()) > 32);
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32);
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32); //{ if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    boolean isAlphabet(byte c){
        return (64 < c && c < 91) || (96 < c && c < 123);
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}