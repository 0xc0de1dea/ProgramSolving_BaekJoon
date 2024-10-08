import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Bar implements Comparable<Bar> {
    int x, h;

    public Bar(int x, int h){
        this.x = x;
        this.h = h;
    }

    @Override
    public int compareTo(Bar o) {
        return this.x - o.x;
    }
}

public class Main {
    static int[] ans;
    static int[] inDegree;
    static ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

    public static boolean topologySort(int n){
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++){
            if (inDegree[i] == 0){
                queue.add(i);
            }
        }

        int idx = 1;

        for (int i = 1; i <= n; i++){
            if (queue.isEmpty()) return false;

            int cur = queue.poll();
            ans[idx++] = cur;

            for (int nxt : edges.get(cur)){
                if (--inDegree[nxt] == 0){
                    queue.add(nxt);
                }
            }
        }

        return true;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        ans = new int[n + 1];
        inDegree = new int[n + 1];
        
        for (int i = 0; i <= n; i++){
            edges.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++){
            int cnt = in.nextInt();
            int prev = 0;

            for (int j = 0; j < cnt; j++){
                int singer = in.nextInt();

                if (prev != 0){
                    edges.get(prev).add(singer);
                    inDegree[singer]++;
                }

                prev = singer;
            }
        }

        boolean flag = topologySort(n);

        if (flag){
            for (int i = 1; i <= n; i++){
                sb.append(ans[i]).append('\n');
            }
        } else sb.append(0);

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