import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Node {
    int to, cost;

    public Node(int to, int cost){
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static ArrayList<ArrayList<Node>> edges = new ArrayList<>();
    static boolean[] isVisited;
    static int v2;
    static int diameter;

    public static void dfs(int cur, int dist){
        isVisited[cur] = true;
        boolean flag = true;

        for (Node nxt : edges.get(cur)){
            if (!isVisited[nxt.to]){
                dfs(nxt.to, dist + nxt.cost);
                flag = false;
            }
        }

        if (flag){
            if (diameter < dist){
                v2 = cur;
                diameter = dist;
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();

        int v = in.nextInt();
        isVisited = new boolean[v + 1];

        for (int i = 0; i <= v; i++){
            edges.add(new ArrayList<>());
        }

        for (int i = 1; i <= v; i++){
            int a = in.nextInt();

            while (true){
                int b = in.nextInt();

                if (b == -1) break;

                int c = in.nextInt();

                edges.get(a).add(new Node(b, c));
            }
        }

        dfs(1, 0);
        isVisited = new boolean[v + 1];
        dfs(v2, 0);
        System.out.println(diameter);
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