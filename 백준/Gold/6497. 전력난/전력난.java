/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.PriorityQueue;

class Edge {
    int u, v, cost;

    public Edge(int u, int v, int cost){
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static int[] parents;
    static int[] rank;
    static PriorityQueue<Edge> edges;

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

    static int kruskal(int n){
        int ret = 0;
        int rem = n - 1;

        while (rem > 0){
            Edge cur = edges.poll();

            if (find(cur.u) != find(cur.v)){
                union(cur.u, cur.v);
                ret += cur.cost;
                rem--;
            }
        }

        return ret;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        while (true){
            int n = in.nextInt();
            int m = in.nextInt();

            if (n == 0 && m == 0) break;

            parents = new int[n + 1];
            rank = new int[n + 1];
            edges = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
            int tot = 0;

            for (int i = 1; i <= n; i++) parents[i] = i;
            for (int i = 0; i < m; i++){
                int x = in.nextInt() + 1;
                int y = in.nextInt() + 1;
                int z = in.nextInt();
                edges.add(new Edge(x, y, z));
                tot += z;
            }

            sb.append(tot - kruskal(n)).append('\n');
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