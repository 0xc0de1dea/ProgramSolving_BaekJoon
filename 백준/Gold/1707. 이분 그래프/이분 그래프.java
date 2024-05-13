import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int[] color;

    public static boolean bfs(ArrayList<ArrayList<Integer>> edges, int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 1; // 1 : red, -1 : blue

        while (!queue.isEmpty()){
            int cur = queue.poll();

            for (int nxt : edges.get(cur)){
                if (color[nxt] == 0){
                    color[nxt] = -1 * color[cur];
                    queue.add(nxt);
                } else if (color[nxt] == color[cur]){
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int k = in.nextInt();

        while (k-- > 0){
            int V = in.nextInt();
            int E = in.nextInt();
            ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
            color = new int[V + 1];
            
            for (int i = 0; i <= V; i++){
                edges.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++){
                int u = in.nextInt();
                int v = in.nextInt();

                edges.get(u).add(v);
                edges.get(v).add(u);
            }

            boolean flag = true;

            for (int i = 1; i <= V; i++){
                if (!flag) break;

                if (color[i] == 0){
                    flag = bfs(edges, i);
                }
            }
            sb.append(flag ? "YES" : "NO").append('\n');
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