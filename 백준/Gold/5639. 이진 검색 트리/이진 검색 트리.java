import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Tree {
    private int cnt;
    private StringBuilder sb;

    public Tree(){
        cnt = 0;
        sb = new StringBuilder();
    }

    class Node {
        int data;
        Node left;
        Node right;

        public Node(int item){
            data = item;
        }

        public void add(Node node){
            Node search = this;

            while (true){
                if (search.data > node.data){
                    if (search.left == null){
                        search.addLeft(node);
                        break;
                    }
                    else {
                        search = search.left;
                    }
                }
                else {
                    if (search.right == null){
                        search.addRight(node);
                        break;
                    }
                    else {
                        search = search.right;
                    }
                }
            }
        }

        public void addLeft(Node node){
            left = node;
            cnt++;
        }

        public void addRight(Node node){
            right = node;
            cnt++;
        }

        public void deleteLeft(){
            left = null;
            cnt--;
        }

        public void deleteRight(){
            right = null;
            cnt--;
        }
    }

    public Node addNode(int item){
        return new Node(item);
    }

    public int getCnt(){
        return cnt;
    }

    public void setPreOrder(Node node){
        if (node == null){
            return;
        }

        sb.append(node.data).append('\n');
        setPreOrder(node.left);
        setPreOrder(node.right);
    }

    public void setInOrder(Node node){
        if (node == null){
            return;
        }

        setInOrder(node.left);
        sb.append(node.data).append('\n');
        setInOrder(node.right);
    }

    public void setPostOrder(Node node){
        if (node == null){
            return;
        };

        setPostOrder(node.left);
        setPostOrder(node.right);
        sb.append(node.data).append('\n');
    }

    public String print(){
        String tmp = sb.toString();
        sb.setLength(0);
        return tmp;
    }
}

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Tree tree = new Tree();
        Tree.Node[] nodes = new Tree.Node[10000];
        String node = br.readLine();
        int root = Integer.parseInt(node);
        nodes[0] = tree.addNode(root);

        int idx = 1;

        while (true){
            node = br.readLine();

            if (node == null || node.equals("")) break;

            int item = Integer.parseInt(node);
            nodes[idx] = tree.addNode(item);
            nodes[0].add(nodes[idx]);
        }

        tree.setPostOrder(nodes[0]);
        System.out.print(tree.print());
    }
}