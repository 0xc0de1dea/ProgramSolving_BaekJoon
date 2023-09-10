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
    static ArrayList<Edge>[] edges = new ArrayList[801];
    static int[] dist;
    static final int INF = 123456789;

    static int dijkstra(int start, int end){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        pq.add(new Edge(start, 0));
        dist = new int[801];

        for (int i = 1; i <= 800; i++) dist[i] = INF;
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

        int n = in.nextInt();
        int e = in.nextInt();
        int v1, v2;
        
        for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < e; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            edges[u].add(new Edge(v, w));
            edges[v].add(new Edge(u, w));
        }

        v1 = in.nextInt();
        v2 = in.nextInt();

        int total = Math.min(dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n), dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n));
        System.out.print(total >= INF ? -1 : total);
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