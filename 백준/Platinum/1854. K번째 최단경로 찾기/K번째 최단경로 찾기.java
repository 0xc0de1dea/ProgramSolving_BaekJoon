import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

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
    static ArrayList<ArrayList<Node>> edges = new ArrayList<>();
    static PriorityQueue<Integer>[] dist;

    public static void dijkstra(int start, int k){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist); 
        pq.add(new Node(start, 0));
        dist[start].add(0);
        
        while (!pq.isEmpty()){
            Node cur = pq.poll();
            
            for (Node nxt : edges.get(cur.to)){
                if (dist[nxt.to].size() < k){
                    dist[nxt.to].add(cur.dist + nxt.dist);
                    pq.add(new Node(nxt.to, cur.dist + nxt.dist));
                } else if (dist[nxt.to].peek() > cur.dist + nxt.dist){
                    dist[nxt.to].poll();
                    dist[nxt.to].add(cur.dist + nxt.dist);
                    pq.add(new Node(nxt.to, cur.dist + nxt.dist));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        
        for (int i = 0; i <= n; i++){
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            edges.get(a).add(new Node(b, c));
        }

        dist = new PriorityQueue[n + 1];

        for (int i = 1; i <= n; i++){
            dist[i] = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }

        dijkstra(1, k);

        for (int i = 1; i <= n; i++){
            if (dist[i].size() < k){
                sb.append(-1).append('\n');
            } else {
                sb.append(dist[i].peek()).append('\n');
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