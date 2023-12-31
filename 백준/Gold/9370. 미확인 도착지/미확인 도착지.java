/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Edge {
    int v, w;

    public Edge(int v, int w){
        this.v = v;
        this.w = w;
    }
}

public class Main {
    static ArrayList<Edge>[] edges;
    static int[] dist;
    static final int INF = 123456789;

    static int dijkstra(int n, int start, int end){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        pq.add(new Edge(start, 0));
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) dist[i] = INF;
        dist[start] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (cur.w > dist[cur.v]) continue;
            for (Edge next : edges[cur.v]){
                int newW = dist[cur.v] + next.w;

                if (newW < dist[next.v]){
                    dist[next.v] = newW;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }

        return dist[end];
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int T = in.nextInt();

        while (T-- > 0){
            int n = in.nextInt();
            int m = in.nextInt();
            int t = in.nextInt();
            int s = in.nextInt();
            int g = in.nextInt();
            int h = in.nextInt();
            edges = new ArrayList[n + 1];
            PriorityQueue<Integer> candidates = new PriorityQueue<>();
            
            for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();
            for (int i = 0; i < m; i++){
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                edges[u].add(new Edge(v, w));
                edges[v].add(new Edge(u, w));
            }
            for (int i = 0; i < t; i++){
                int x = in.nextInt();
                int s2x = dijkstra(n, s, x);
                
                if (dijkstra(n, s, g) + dijkstra(n, g, h) + dijkstra(n, h, x) == s2x ||
                    dijkstra(n, s, h) + dijkstra(n, h, g) + dijkstra(n, g, x) == s2x){
                    candidates.add(x);
                }
            }
            while (!candidates.isEmpty()) sb.append(candidates.poll()).append(' ');

            sb.append('\n');
        }

        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
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
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}