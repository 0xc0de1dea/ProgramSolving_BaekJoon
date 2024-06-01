import java.util.ArrayList;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Edge {
    int a, b;
    int cost;

    public Edge(int a, int b, int cost){
        this.a = a;
        this.b = b;
        this.cost = cost;
    }
}

public class Main {
    static final int INF = 123456789;
    static int[] dist;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static boolean bellmanford(ArrayList<Edge> edges, int n, int m){
        boolean isUpdate = false;

        for (int i = 0; i < n; i++){
            isUpdate = false;

            for (int j = 0; j < m; j++){
                Edge cur = edges.get(j);

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
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        int[][] map = new int[n][m];
        dist = new int[n * m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                char c = in.nextChar();

                if (c == 'H'){
                    map[i][j] = -1;
                } else {
                    map[i][j] = c - '0';
                }
            }
        }

        for (int i = 0; i < n * m; i++){
            dist[i] = INF;
        }

        dist[0] = -1;

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                int num = map[i][j];

                if (num == -1) continue;
                
                int curNode = i * m + j;

                for (int k = 0; k < 4; k++){
                    int nx = i + dx[k] * num;
                    int ny = j + dy[k] * num;
                    
                    if (0 <= nx && nx < n && 0 <= ny && ny < m){
                        int nxtNode = nx * m + ny;
                        edges.add(new Edge(curNode, nxtNode, -1));
                    }
                }
            }
        }

        if (bellmanford(edges, n * m, edges.size())){
            sb.append(-1);
        } else {
            int max = 0;

            for (int i = 0; i < n * m; i++){
                if (dist[i] != INF && map[i / m][i % m] != -1){
                    max = Math.max(max, Math.abs(dist[i]));
                }
            }

            sb.append(max);
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