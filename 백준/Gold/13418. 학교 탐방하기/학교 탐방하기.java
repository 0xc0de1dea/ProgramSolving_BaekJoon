import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Node {
    int v, w;

    public Node(int v, int w){
        this.v = v;
        this.w = w;
    }
}

public class Main {
    static int[] parent;
    static int[] rank;

    public static int find(int x){
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);

        if (a != b){
            if (rank[a] >= rank[b]) parent[b] = a;
            else parent[a] = b;

            if (rank[a] == rank[b]) rank[a]++;
        }
    }

    public static int kruskal(int[][] edges, int n, int m){
        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 1; i <= n; i++){
            parent[i] = i;
        }

        int weight = 0;
        int cnt = 0;

        for (int i = 0; i < m; i++){
            if (find(edges[i][0]) != find(edges[i][1])){
                union(edges[i][0], edges[i][1]);
                weight += edges[i][2];
                cnt++;

                if (cnt == n - 1) break;
            }
        }

        return cnt != n - 1 ? -1 : weight;
    }

    public static int prim(ArrayList<ArrayList<Node>> edges, int n, Comparator<Node> comp){
        PriorityQueue<Node> pq = new PriorityQueue<>(comp);
        pq.add(new Node(1, 0));
        boolean[] isVisited = new boolean[n + 1];
        
        int weight = 0;
        int cnt = 0;

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if (isVisited[cur.v]) continue;

            isVisited[cur.v] = true;
            weight += cur.w;
            cnt++;

            if (cnt == n) break;

            for (Node nxt : edges.get(cur.v)){
                if (!isVisited[nxt.v]){
                    pq.add(nxt);
                }
            }
        }

        return cnt != n ? -1 : weight;
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<ArrayList<Node>> edges = new ArrayList<>();

        for (int i = 0; i <= n; i++){
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i <= m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt() ^ 1;

            edges.get(a).add(new Node(b, c));
            edges.get(b).add(new Node(a, c));
        }

        Comparator<Node> comp1 = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2){
                return o1.w - o2.w;
            }
        };

        Comparator<Node> comp2 = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2){
                return o2.w - o1.w;
            }
        };

        int min = prim(edges, n + 1, comp1);
        int max = prim(edges, n + 1, comp2);

        System.out.println(max * max - min * min);
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
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
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