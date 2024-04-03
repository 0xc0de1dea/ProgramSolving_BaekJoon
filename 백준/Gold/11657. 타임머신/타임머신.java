import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Edge implements Comparable<Edge> {
    int a;
    int b;
    int cost;

    public Edge(int a, int b, int cost){
        this.a = a;
        this.b = b;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o){
        return this.cost - o.cost;
    }
}

public class Main {
    static int n;
    static int m;
    static Edge[] edges;
    static long[] dist;
    static final int INF = 123_456_789;

    public static boolean bellmanford(int start){
        dist[start] = 0;
        boolean isUpdate = false;

        for (int i = 0; i < n; i++){
            isUpdate = false;

            for (int j = 0; j < m; j++){
                Edge cur = edges[j];

                if (dist[cur.a] == INF) continue;
                
                if (dist[cur.b] > dist[cur.a] + cur.cost){
                    dist[cur.b] = dist[cur.a] + cur.cost;
                    isUpdate = true;
                }
            }

            if (!isUpdate) break;

        }

        if (isUpdate) return true;
        else return false;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        n = in.nextInt();
        m = in.nextInt();
        edges = new Edge[m];
        
        for (int i = 0; i < m; i++){
            edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }

        dist = new long[n + 1];
        
        for (int i = 1; i <= n; i++){
            dist[i] = INF;
        }

        if (bellmanford(1)){
            sb.append(-1);
        } else {
            for (int i = 2; i <= n; i++){
                sb.append(dist[i] == INF ? -1 : dist[i]).append('\n');
            }
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