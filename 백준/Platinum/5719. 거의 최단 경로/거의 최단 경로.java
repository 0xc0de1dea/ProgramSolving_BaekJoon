import java.util.ArrayList;
import java.util.Arrays;
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
    int to;
    int dist;

    public Node(int to, int dist){
        this.to = to;
        this.dist = dist;
    }
}

public class Main {
    static ArrayList<ArrayList<Node>> edges;
    static int[] dist;
    static ArrayList<Integer>[] tracking;
    static boolean[][] deletedEdge;

    public static void dijkstra(int start, int end, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist); 
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if (dist[cur.to] < cur.dist) continue;

            for (Node nxt : edges.get(cur.to)){
                int newDist = cur.dist + nxt.dist;

                if (dist[nxt.to] > newDist){
                    tracking[nxt.to] = new ArrayList<>(Arrays.asList(cur.to));
                    dist[nxt.to] = newDist;
                    pq.add(new Node(nxt.to, dist[nxt.to]));
                } else if (dist[nxt.to] == newDist){
                    tracking[nxt.to].add(cur.to);
                }
            }
        }
    }

    public static void dijkstraOri(int start, int end, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist); 
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if (dist[cur.to] < cur.dist) continue;
            
            for (Node nxt : edges.get(cur.to)){
                if (deletedEdge[cur.to][nxt.to]) continue;

                int newDist = cur.dist + nxt.dist;

                if (dist[nxt.to] > newDist){
                    dist[nxt.to] = newDist;
                    pq.add(new Node(nxt.to, dist[nxt.to]));
                }
            }
        }
    }
    
    public static void deleteEdge(int start, int n){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()){
            int cur = queue.poll();

            for (int nxt : tracking[cur]){
                if (!deletedEdge[nxt][cur]){
                    deletedEdge[nxt][cur] = true;
                    queue.add(nxt);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        while (true){
            int n = in.nextInt();
            int m = in.nextInt();

            if (n == 0 && m == 0) break;

            int s = in.nextInt();
            int d = in.nextInt();

            edges = new ArrayList<>();

            for (int i = 0; i < n; i++){
                edges.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++){
                int a = in.nextInt();
                int b = in.nextInt();
                int c = in.nextInt();
    
                edges.get(a).add(new Node(b, c));
            }
    
            dist = new int[n];
            tracking = new ArrayList[n];
            deletedEdge = new boolean[n][n];

            final int INF = 123456789;

            for (int i = 0; i < n; i++){
                dist[i] = INF;
                tracking[i] = new ArrayList<>();
            }

            dist[s] = 0;

            dijkstra(s, d, n);
            deleteEdge(d, n);

            // for (int i = 0; i < n; i++){
            //     System.out.println(tracking[i]);
            // }

            // for (int i = 0; i < n; i++){
            //     for (Node item : edges.get(i)){
            //         System.out.print(item.to + " ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();

            dist = new int[n];

            for (int i = 0; i < n; i++){
                dist[i] = INF;
            }

            dist[s] = 0;

            dijkstraOri(s, d, n);

            sb.append(dist[d] == INF ? -1 : dist[d]).append('\n');
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