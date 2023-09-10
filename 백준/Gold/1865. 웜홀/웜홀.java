import java.util.ArrayList;
import java.util.Arrays;

class Edge {
    public int s, e, t;

    public Edge(int s, int e, int t){
        this.s = s;
        this.e = e;
        this.t = t;
    }
}

public class Main {
    static final int INF = 123456789;
    static ArrayList<Edge> edge;

    public static boolean bellmanford(int n, int m, int start){
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                Edge cur = edge.get(j);
                dist[cur.e] = Math.min(dist[cur.e], dist[cur.s] + cur.t);
            }
        }

        for (int j = 0; j < m; j++){
            Edge cur = edge.get(j);

            if (dist[cur.e] > dist[cur.s] + cur.t){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] argu) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int tc = in.nextInt();
        
        while (tc-- > 0){
            int n = in.nextInt();
            int m = in.nextInt();
            int w = in.nextInt();
            edge = new ArrayList<>();
            
            for (int i = 0; i < m; i++){
                int s = in.nextInt();
                int e = in.nextInt();
                int t = in.nextInt();
                edge.add(new Edge(s, e, t));
                edge.add(new Edge(e, s, t));
            }

            for (int i = 0; i < w; i++){
                int s = in.nextInt();
                int e = in.nextInt();
                int t = in.nextInt();
                edge.add(new Edge(s, e, -t));
            }

            sb.append(bellmanford(n, 2 * m + w, 1) ? "NO" : "YES").append('\n');
        }
        
        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        while ((c = read()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return n;
    }
    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}