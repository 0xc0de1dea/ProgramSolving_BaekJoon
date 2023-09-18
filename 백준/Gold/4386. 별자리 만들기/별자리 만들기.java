/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.PriorityQueue;

class Edge {
    int u, v;
    double cost;

    public Edge(int u, int v, double cost){
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static int[] parent, rank;
    static PriorityQueue<Edge> edges;

    static int find(int node){
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    static void union(int x, int y){
        x = find(x); y = find(y);

        if (x != y){
            if (rank[x] > rank[y]) parent[y] = x;
            else parent[x] = y;

            if (rank[x] == rank[y]) rank[y]++;
        }
    }

    static double kruskal(int n){
        double ans = 0;
        int rem = n - 1;

        while (rem > 0){
            Edge cur = edges.poll();

            if (find(cur.u) != find(cur.v)){
                union(cur.u, cur.v);
                ans += cur.cost;
                rem--;
            }
        }

        return ans / 100;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        parent = new int[n + 1];
        rank = new int[n + 1];
        edges = new PriorityQueue<>((o1, o2) -> (int)(o1.cost - o2.cost));
        double[][] stars = new double[n + 1][2];

        for (int i = 1; i <= n; i++) parent[i] = i;
        for (int i = 1; i <= n; i++){
            double x = in.nextDouble();
            double y = in.nextDouble();
            stars[i][0] = x;
            stars[i][1] = y;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                if (i != j){
                    double cost = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
                    edges.add(new Edge(i, j, cost * 100));
                }
            }
        }

        System.out.print(kruskal(n));
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