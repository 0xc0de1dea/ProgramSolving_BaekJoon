/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static int[] order;
    static int depth;
    static boolean[] isVisited;

    static void dfs(ArrayList<Integer>[] edges, int start){
        order[start] = ++depth;
        isVisited[start] = true;
        for (int node : edges[start]){
            if (!isVisited[node]){
                dfs(edges, node);
            }
        }
    }

    @SuppressWarnings("unchecked")
    static public void main(String[] args) throws Exception{
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int m = in.nextInt();
        int r = in.nextInt();

        ArrayList<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            edges[u].add(v); edges[v].add(u);
        }
        for (int i = 1; i <= n; i++) Collections.sort(edges[i], Comparator.reverseOrder());

        order = new int[n + 1];
        depth = 0;
        isVisited = new boolean[n + 1];
        dfs(edges, r);

        for (int i = 1; i <= n; i++){
            if (isVisited[i]) sb.append(order[i]).append('\n');
            else sb.append(0).append('\n');
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