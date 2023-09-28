/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

import java.util.ArrayList;

public class Main {
    static ArrayList<Integer>[] edges;
    static boolean[] isVisited;

    static boolean dfs(int n, int start){
        int[] stack = new int[n];
        int ptr = -1;
        stack[++ptr] = start;
        isVisited[start] = true;

        int vertexCnt = 1;
        int edgeCnt = 0;

        while (ptr >= 0){
            int cur = stack[ptr--];
            edgeCnt += edges[cur].size();

            for (int next : edges[cur]){
                if (!isVisited[next]){
                    stack[++ptr] = next;
                    isVisited[next] = true;
                    vertexCnt++;
                }
            }
        }

        return edgeCnt == (vertexCnt - 1) << 1;
    }
    
    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int testCase = 1;

        while (true){
            int n = in.nextInt();
            int m = in.nextInt();

            if (n == 0 && m == 0) break;

            edges = new ArrayList[n + 1];
            isVisited = new boolean[n + 1];

            for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();
            for (int i = 0; i < m; i++){
                int u = in.nextInt();
                int v = in.nextInt();
                edges[u].add(v);
                edges[v].add(u);
            }

            int treeCnt = 0;
            sb.append(String.format("Case %d: ", testCase++));

            for (int i = 1; i <= n; i++){
                if (!isVisited[i]){
                    treeCnt += dfs(n, i) ? 1 : 0;
                }
            }
            if (treeCnt > 1) sb.append(String.format("A forest of %d trees.", treeCnt)).append('\n');
            else if (treeCnt == 1) sb.append("There is one tree.").append('\n');
            else sb.append("No trees.").append('\n');
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
        while ((c = read()) <= 32);
        do sb.appendCodePoint(c);
        while (isAlphabet(c = read()));
        return sb.toString();
    }

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