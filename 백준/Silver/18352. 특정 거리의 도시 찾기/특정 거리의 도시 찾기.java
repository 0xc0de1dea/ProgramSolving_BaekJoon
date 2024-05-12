import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Node {
    int to;
    int cost;

    public Node(int to, int cost){
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static ArrayList<ArrayList<Node>> edges = new ArrayList<>();
    static int[] cost;

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        pq.add(new Node(start, 0));
        cost[start] = 0;

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if (cur.cost > cost[cur.to]) continue;

            for (Node nxt : edges.get(cur.to)){
                if (cost[nxt.to] > cost[cur.to] + nxt.cost){
                    cost[nxt.to] = cost[cur.to] + nxt.cost;
                    pq.add(new Node(nxt.to, cost[nxt.to]));
                }
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        final int MAX = 123456789;

        cost = new int[n + 1];

        for (int i = 1; i <= n; i++){
            cost[i] = MAX;
        }

        for (int i = 0; i <= n; i++){
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();

            edges.get(a).add(new Node(b, 1));
        }
        
        dijkstra(x);

        boolean flag = false;

        for (int i = 1; i <= n; i++){
            if (cost[i] == k){
                sb.append(i).append('\n');
                flag = true;
            }
        }

        if (flag){
            System.out.print(sb);
        } else {
            System.out.print(-1);
        }
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
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
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
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