/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.PriorityQueue;

class Edge {
    int v, cost;

    public Edge(int v, int cost){
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static int v, e;
    static int[] cost;
    static ArrayList<Edge>[] edges;

    static void dijkstra(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Edge(start, 0));
        cost[start] = 0;
        
        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (cur.cost > cost[cur.v]) continue;
            for (Edge next : edges[cur.v]){
                int newCost = cur.cost + next.cost;
                
                if (newCost < cost[next.v]){
                    cost[next.v] = newCost;
                    pq.add(new Edge(next.v, cost[next.v]));
                }
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final int INF = 123456789;

        v = in.nextInt();
        e = in.nextInt();
        cost = new int[v + 1];
        edges = new ArrayList[v + 1];
        int start = in.nextInt();

        for (int i = 1; i <= v; i++){
            cost[i] = INF;
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            edges[u].add(new Edge(v, w));
        }

        dijkstra(start);

        for (int i = 1; i <= v; i++) sb.append(cost[i] == INF ? "INF" : cost[i]).append('\n');
    
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