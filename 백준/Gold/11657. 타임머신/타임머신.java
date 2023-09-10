/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.ArrayList;

class Edge {
    int u, v;
    int cost;

    public Edge(int u, int v, int cost){
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static int v, e;
    static long[] cost;
    static ArrayList<Edge> edges;

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final long INF = (long)1e10;

        v = in.nextInt();
        e = in.nextInt();
        cost = new long[v + 1];
        edges = new ArrayList<>();
        
        for (int i = 2; i <= v; i++) cost[i] = INF;
        for (int i = 0; i < e; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            edges.add(new Edge(a, b, c));
        }

        boolean flag = true;

        for (int i = 1; i <= v; i++){
            for (int j = 0; j < e; j++){
                Edge cur = edges.get(j);
                if (cost[cur.u] != INF && cost[cur.v] > cost[cur.u] + cur.cost){
                    cost[cur.v] = cost[cur.u] + cur.cost;
                    if (i == v) flag = false;
                }
            }
        }

        if (flag){
            for (int i = 2; i <= v; i++){
                sb.append(cost[i] == INF ? -1 : cost[i]).append('\n');
            }
        }
        else sb.append(-1);

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