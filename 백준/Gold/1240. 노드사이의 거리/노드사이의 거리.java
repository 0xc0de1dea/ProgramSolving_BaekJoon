import java.util.ArrayList;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Edge {
    int to;
    int dist;

    public Edge(int to, int dist){
        this.to = to;
        this.dist = dist;
    }
}

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
    static boolean[] isVisited;

    public static int dfs(Edge cur, int dest){
        if (cur.to == dest) return cur.dist;
        
        isVisited[cur.to] = true;

        int dist = 0;

        for (Edge nxt : edges.get(cur.to)){
            if (!isVisited[nxt.to]){
                int res = dfs(nxt, dest);

                if (res > 0) dist = cur.dist + res;
            }
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();

        for (int i = 0; i <= n; i++){
            edges.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int dist = in.nextInt();
            
            edges.get(a).add(new Edge(b, dist));
            edges.get(b).add(new Edge(a, dist));
        }

        for (int i = 0; i < m; i++){
            int start = in.nextInt();
            int end = in.nextInt();
            isVisited = new boolean[n + 1];
            
            int dist = dfs(new Edge(start, 0), end);
            sb.append(dist).append('\n');
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