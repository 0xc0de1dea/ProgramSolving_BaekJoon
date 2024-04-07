import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int[] parent;
    static int[] rank;

    public static int find(int a){
        if (a == parent[a]) return a;

        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);

        if (a != b){
            if (rank[a] < rank[b]){
                parent[a] = b;
            } else {
                parent[b] = a;
            }

            if (rank[a] == rank[b]){
                rank[a]++;
            }
        }
    }

    public static int kruskal(int[][] edges, int n, int m){
        int cost = 0;
        parent = new int[n + 1];
        rank = new int[n + 1];
        Arrays.sort(edges, (x, y) -> x[2] - y[2]);

        for (int i = 1; i <= n; i++){
            parent[i] = i;
        }

        int cnt = 0;

        for (int i = 0; i < m; i++){
            if (find(edges[i][0]) != find(edges[i][1])){
                union(edges[i][0], edges[i][1]);
                cost += edges[i][2];
                cnt++;

                if (cnt == n - 1) break;
            }
        }

        return cost;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        while (true){
            int n = in.nextInt();
            int m = in.nextInt();

            if (n == 0 && m == 0) break;

            int[][] edges = new int[m][3];
            int totCost = 0;
    
            for (int i = 0; i < m; i++){
                edges[i][0] = in.nextInt();
                edges[i][1] = in.nextInt();
                edges[i][2] = in.nextInt();
                totCost += edges[i][2];
            }

            int minCost = kruskal(edges, n, m);

            sb.append(totCost - minCost).append('\n');
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
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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