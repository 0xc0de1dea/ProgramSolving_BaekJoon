/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

class Edge {
    int u, v;
    int cost;

    public Edge(int u, int v, int cost){
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final long INF = (long)1e10;

        int n = in.nextInt();
        int m = in.nextInt();
        long[][] minCost = new long[n + 1][n + 1];
        
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                if (i != j){
                    minCost[i][j] = INF;
                }
            }
        }
        for (int i = 0; i < m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            if (minCost[u][v] > w) minCost[u][v] = w;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                for (int k = 1; k <= n; k++){
                    if (j == k) continue;
                    if (j == i || k == i) continue;
                    if (minCost[j][i] + minCost[i][k] < minCost[j][k]) minCost[j][k] = minCost[j][i] + minCost[i][k];
                }
            }
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                sb.append(minCost[i][j] == INF ? 0 : minCost[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

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
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}