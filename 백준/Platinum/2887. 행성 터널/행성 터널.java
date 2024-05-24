import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class SegmentTree {
    long[] arr;
    int size;

    public SegmentTree(long[] data){
        size = (int)Math.ceil(Math.log10(data.length) / Math.log10(2));
        arr = new long[size << 1];

        for (int i = 0; i < data.length; i++){
            arr[size + i] = data[i];
        }
        
        construct();
    }

    void construct(){
        for (int i = size - 1; i > 0; i--){
            arr[i] = arr[i << 1] + arr[i << 1 | 1];
        }
    }

    public long sum(int s, int e){
        return sum(s, e, 1, 0, arr.length - 1);
    }

    long sum(int s, int e, int node, int ns, int ne){
        if (e < ns || ne < s) return 0;
        if (s <= ns && ne <= e) return arr[node];

        int m = ns + ne >> 1;

        return sum(s, e, node >> 1, ns, m) + sum(s, e, node >> 1 | 1, m + 1, ne);
    }

    public void update(int idx, long val){
        idx += size;
        arr[idx] = val;

        while (idx > 1){
            idx >>= 1;
            arr[idx] = arr[idx >> 1] + arr[idx >> 1 | 1];
        }
    }
}

class Node {
    int v;
    long w;

    public Node(int v, long w){
        this.v = v;
        this.w = w;
    }
}

class Planet {
    long cost;
    int id;

    public Planet (long cost, int id){
        this.cost = cost;
        this.id = id;
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

    public static long kruskal(int[][] edges, int n, int m){
        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 1; i <= n; i++){
            parent[i] = i;
        }

        long weight = 0;
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

    public static long prim(ArrayList<ArrayList<Node>> edges, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> (int)(x.w - y.w));
        pq.add(new Node(1, 0));
        boolean[] isVisited = new boolean[n + 1];
        
        long weight = 0;
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

        ArrayList<Planet> xArr = new ArrayList<>();
        ArrayList<Planet> yArr = new ArrayList<>();
        ArrayList<Planet> zArr = new ArrayList<>();

        for (int i = 1; i <= n; i++){
            long x = in.nextLong();
            long y = in.nextLong();
            long z = in.nextLong();
            
            xArr.add(new Planet(x, i));
            yArr.add(new Planet(y, i));
            zArr.add(new Planet(z, i));
        }

        Collections.sort(xArr, (o1, o2) -> (int)(o1.cost - o2.cost));
        Collections.sort(yArr, (o1, o2) -> (int)(o1.cost - o2.cost));
        Collections.sort(zArr, (o1, o2) -> (int)(o1.cost - o2.cost));

        ArrayList<ArrayList<Node>> edges = new ArrayList<>();

        for (int i = 0; i <= n; i++){
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < xArr.size() - 1; i++){
            Planet x1 = xArr.get(i);
            Planet x2 = xArr.get(i + 1);

            Planet y1 = yArr.get(i);
            Planet y2 = yArr.get(i + 1);

            Planet z1 = zArr.get(i);
            Planet z2 = zArr.get(i + 1);

            edges.get(x1.id).add(new Node(x2.id, Math.abs(x1.cost - x2.cost)));
            edges.get(x2.id).add(new Node(x1.id, Math.abs(x1.cost - x2.cost)));
            edges.get(y1.id).add(new Node(y2.id, Math.abs(y1.cost - y2.cost)));
            edges.get(y2.id).add(new Node(y1.id, Math.abs(y1.cost - y2.cost)));
            edges.get(z1.id).add(new Node(z2.id, Math.abs(z1.cost - z2.cost)));
            edges.get(z2.id).add(new Node(z1.id, Math.abs(z1.cost - z2.cost)));
        }

        long min = prim(edges, n);

        System.out.print(min);
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