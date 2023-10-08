/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

import java.util.ArrayList;

class Edge {
    int v, dist;

    public Edge(int v, int dist){
        this.v = v;
        this.dist = dist;
    }
}

public class Main {
    static int n;
    static ArrayList<Edge>[] edges;
    static boolean[] isVisited;
    static int diameterV, diameterDist;

    static void dfs(int curNode, int totDist){
        isVisited[curNode] = true;
        boolean flag = true;

        for (Edge edge : edges[curNode]){
            if (!isVisited[edge.v]){
                dfs(edge.v, totDist + edge.dist);
                flag = false;
            }
        }
        if (flag){
            if (diameterDist < totDist){
                diameterV = curNode;
                diameterDist = totDist;
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        edges = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++){
            int u = in.nextInt();
            int v;
            int dist;
            
            while ((v = in.nextInt()) != -1){
                dist = in.nextInt();
                edges[u].add(new Edge(v, dist));
                edges[v].add(new Edge(u, dist));
            }
        }

        isVisited = new boolean[n + 1];
        dfs(1, 0);
        isVisited = new boolean[n + 1];
        dfs(diameterV, 0);

        System.out.print(diameterDist);
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
        while ((c = read()) > 32);
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32);
        return (char)c;
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