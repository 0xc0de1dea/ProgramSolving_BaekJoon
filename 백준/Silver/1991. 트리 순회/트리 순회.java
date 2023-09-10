import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;

class Tree {
    private int cnt;
    private StringBuilder sb;

    public Tree(){
        cnt = 0;
        sb = new StringBuilder();
    }

    class Node {
        String data;
        Node left;
        Node right;

        public Node(String item){
            data = item;
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

    public Node addNode(String item){
        return new Node(item);
    }

    public int getCnt(){
        return cnt;
    }

    public void setPreOrder(Node node){
        if (node == null){
            return;
        }

        sb.append(node.data);
        setPreOrder(node.left);
        setPreOrder(node.right);
    }

    public void setInOrder(Node node){
        if (node == null){
            return;
        }

        setInOrder(node.left);
        sb.append(node.data);
        setInOrder(node.right);
    }

    public void setPostOrder(Node node){
        if (node == null){
            return;
        };

        setPostOrder(node.left);
        setPostOrder(node.right);
        sb.append(node.data);
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

        int n = Integer.parseInt(br.readLine());
        Tree binaryTree = new Tree();
        Tree.Node[] nodes = new Tree.Node[92];

        for (int i = 65; i < 92; i++){
            nodes[i] = binaryTree.addNode(Character.toString(i));
        }

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String root = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            if (!left.equals(".")){
                nodes[root.charAt(0)].addLeft(nodes[left.charAt(0)]);
            }

            if (!right.equals(".")){
                nodes[root.charAt(0)].addRight(nodes[right.charAt(0)]);
            }
        }

        binaryTree.setPreOrder(nodes[65]);
        sb.append(binaryTree.print()).append('\n');
        binaryTree.setInOrder(nodes[65]);
        sb.append(binaryTree.print()).append('\n');
        binaryTree.setPostOrder(nodes[65]);
        sb.append(binaryTree.print());
        System.out.println(sb);
    }
}