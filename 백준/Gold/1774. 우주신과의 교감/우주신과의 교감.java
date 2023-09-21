/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.PriorityQueue;

class Edge implements Comparable<Edge> {
    int u, v;
    double cost;

    public Edge(int u, int v, double cost){
        this.u = u;
        this.v = v;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o){
        return Double.compare(this.cost, o.cost);
    }
}

public class Main {
    static int[] parents;
    static int[] rank;
    static PriorityQueue<Edge> edges;

    static double getDist(long[] a, long[] b){
        long dx = a[0] - b[0];
        long dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    static int find(int node){
        if (node == parents[node]) return node;
        return parents[node] = find(parents[node]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if (x != y){
            if (rank[x] > rank[y]) parents[y] = x;
            else parents[x] = y;
            
            if (rank[x] == rank[y]) rank[y]++;
        }
    }

    static double kruskal(int n){
        double ret = 0;
        int rem = n;

        while (!edges.isEmpty()){
            Edge cur = edges.poll();

            if (find(cur.u) != find(cur.v)){
                ret += cur.cost;
                if (--rem == 0) break;
                union(cur.u, cur.v);
            }
        }

        return ret;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        long[][] axis = new long[n + 1][2];
        parents = new int[n + 1];
        rank = new int[n + 1];
        edges = new PriorityQueue<>();

        for (int i = 1; i <= n; i++){
            parents[i] = i;
            axis[i][0] = in.nextLong();
            axis[i][1] = in.nextLong();
        }
        for (int i = 1; i <= n; i++){
            for (int j = i + 1; j <= n; j++){
                double dist = getDist(axis[i], axis[j]);
                edges.add(new Edge(i, j, dist));
            }
        }
        for (int i = 0; i < m; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            union(x, y);
        }

        System.out.print(String.format("%.2f", kruskal(n - m)));
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
        while (isAlphabet(c = read()));
        return sb.toString();
    }

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