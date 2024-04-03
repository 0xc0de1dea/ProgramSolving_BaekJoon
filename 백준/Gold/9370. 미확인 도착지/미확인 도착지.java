import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Node implements Comparable<Node> {
    int x;
    int cost;

    public Node(int x, int cost){
        this.x = x;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o){
        return this.cost - o.cost;
    }
}

public class Main {
    static int n;
    static ArrayList<ArrayList<Node>> nodes;
    static int[][] dist;
    static final int INF = 123_456_789;

    public static void dijkstra(int start, int pathType){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[pathType][start] = 0;

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if (dist[pathType][cur.x] < cur.cost){
                continue;
            }

            for (int i = 0; i < nodes.get(cur.x).size(); i++){
                Node nxt = nodes.get(cur.x).get(i);

                if (dist[pathType][nxt.x] > cur.cost + nxt.cost){
                    dist[pathType][nxt.x] = cur.cost + nxt.cost;
                    pq.add(new Node(nxt.x, dist[pathType][nxt.x]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        int T = in.nextInt();
        StringBuilder sb = new StringBuilder();

        while (T-- > 0){
            int n = in.nextInt();
            int m = in.nextInt();
            int t = in.nextInt();

            int s = in.nextInt();
            int g = in.nextInt();
            int h = in.nextInt();

            nodes = new ArrayList<>();

            for (int i = 0; i <= n; i++){
                nodes.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++){
                int a = in.nextInt();
                int b = in.nextInt();
                int d = in.nextInt();

                nodes.get(a).add(new Node(b, d));
                nodes.get(b).add(new Node(a, d));
            }

            ArrayList<Integer> candidates = new ArrayList<>();
            int[] dest = new int[t];

            for (int i = 0; i < t; i++){
                dest[i] = in.nextInt();
            }

            dist = new int[3][n + 1];

            for (int i = 0; i < 3; i++){
                for (int j = 1; j <= n; j++){
                    dist[i][j] = INF;
                }
            }

            dijkstra(s, 0);
            dijkstra(g, 1);
            dijkstra(h, 2);

            for (int i = 0; i < t; i++){
                if ((dist[0][dest[i]] == dist[0][g] + dist[1][h] + dist[2][dest[i]]) 
                || (dist[0][dest[i]] == dist[0][h] + dist[2][g] + dist[1][dest[i]])){
                    candidates.add(dest[i]);
                }
            }

            Collections.sort(candidates);
            
            for (int item : candidates){
                sb.append(item).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
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