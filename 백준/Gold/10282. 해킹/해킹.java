import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Node {
    int to;
    int time;

    public Node(int to, int time){
        this.to = to;
        this.time = time;
    }
}

public class Main {
    static final int INF = 123456789;
    static int max = 0;
    static int cnt = 0;

    public static void bfs(ArrayList<ArrayList<Node>> edges, int start, int n){
        PriorityQueue<Node> queue = new PriorityQueue<>((x, y) -> x.time - y.time);
        queue.add(new Node(start, 0));
        
        int[] time = new int[n + 1];
        

        for (int i = 1; i <= n; i++){
            time[i] = INF;
        }

        time[start] = 0;

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            
            if (time[cur.to] < cur.time) continue;

            for (Node nxt : edges.get(cur.to)){
                if (time[nxt.to] > time[cur.to] + nxt.time){
                    time[nxt.to] = time[cur.to] + nxt.time;
                    queue.add(new Node(nxt.to, time[nxt.to]));
                }
            }
        }

        max = 0;
        cnt = 0;

        for (int i = 1; i <= n; i++){
            if (time[i] != INF){
                max = Math.max(max, time[i]);
                cnt++;
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int t = in.nextInt();

        while (t-- > 0){
            int n = in.nextInt();
            int d = in.nextInt();
            int c = in.nextInt();

            ArrayList<ArrayList<Node>> edges = new ArrayList<>();

            for (int i = 0; i <= n; i++){
                edges.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++){
                int a = in.nextInt();
                int b = in.nextInt();
                int s = in.nextInt();

                edges.get(b).add(new Node(a, s));
            }

            bfs(edges, c, n);

            sb.append(cnt).append(' ').append(max).append('\n');
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