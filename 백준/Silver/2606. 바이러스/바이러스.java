/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static ArrayList<Integer>[] edges;
    static boolean[] isVisited;

    static int bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;
        int cnt = 0;

        while (q.size() > 0){
            int cur = q.poll();
            
            for (int edge : edges[cur]){
                if (!isVisited[edge]){
                    q.add(edge);
                    isVisited[edge] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int v = in.nextInt();
        int e = in.nextInt();
        edges = new ArrayList[v + 1];
        isVisited = new boolean[v + 1];

        for (int i = 1; i <= v; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < e; i++){
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            edges[v1].add(v2);
            edges[v2].add(v1);
        }

        System.out.print(bfs(1));
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