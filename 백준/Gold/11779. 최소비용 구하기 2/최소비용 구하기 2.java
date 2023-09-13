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
    static int[] counting;
    static int[] tracking;

    static void bfs(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        pq.add(new Edge(start, 0));
        dist[start] = 0;
        counting[start]++;
        
        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (dist[cur.v] < cur.w) continue;
            for (Edge next : edges[cur.v]){
                int newDist = dist[cur.v] + next.w;

                if (newDist < dist[next.v]){
                    dist[next.v] = newDist;
                    counting[next.v] = counting[cur.v] + 1;
                    tracking[next.v] = cur.v;
                    pq.add(new Edge(next.v, dist[next.v]));
                }
            }
        }
    }

    static String tracking(int start){
        StringBuilder sb = new StringBuilder();
        int[] stack = new int[1_000];
        int ptr = -1;
        stack[++ptr] = start;

        while (tracking[start] != 0){
            stack[++ptr] = tracking[start];
            start = tracking[start];
        }

        while (ptr >= 0) sb.append(stack[ptr--]).append(' ');

        return sb.toString();
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final int INF = 123456789;

        int n = in.nextInt();
        int m = in.nextInt();
        edges = new ArrayList[n + 1];
        dist = new int[n + 1];
        counting = new int[n + 1];
        tracking = new int[n + 1];

        for (int i = 0; i <= n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            edges[u].add(new Edge(v, w));
        }

        int s = in.nextInt();
        int e = in.nextInt();

        for (int i = 1; i <= n; i++) dist[i] = INF;

        bfs(s);

        sb.append(dist[e]).append('\n').append(counting[e]).append('\n').append(s == e ? "1 1" : tracking(e));
        
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