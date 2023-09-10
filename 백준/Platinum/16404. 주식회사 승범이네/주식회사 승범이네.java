/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;
import java.util.ArrayList;

class FenwickTree {
    final int SIZE;
    long[] tree;

    public FenwickTree(int size){
        SIZE = size;
        tree = new long[SIZE];
    }

    public void update(int idx, long value){
        while (idx < SIZE){
            tree[idx] += value;
            idx += (idx & -idx);
        }
    }

    public void update(int in_idx, int out_idx, long value){
        while (in_idx < SIZE){
            tree[in_idx] += value;
            in_idx += (in_idx & -in_idx);
        }

        while (out_idx < SIZE){
            tree[out_idx] -= value;
            out_idx += (out_idx & -out_idx);
        }
    }

    public long sum(int idx){
        long sum = 0;

        while (idx > 0){
            sum += tree[idx];
            idx -= (idx & -idx);
        }

        return sum;
    }
}

public class Main {
    static ArrayList<Integer>[] edge;
    static int[] inner;
    static int[] outer;
    static int id = 0;

    public static void dfs(int cur){
        int sz = edge[cur].size();
        inner[cur] = ++id;

        for (int next = 0; next < sz; next++){
            dfs(edge[cur].get(next));
        }

        outer[cur] = id;
    }

    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        edge = new ArrayList[n + 1];
        inner = new int[n + 1];
        outer = new int[n + 1];

        for (int i = 1; i <= n; i++) edge[i] = new ArrayList<>(); in.nextInt();

        for (int i = 2; i <= n; i++){
            edge[in.nextInt()].add(i);
        }

        FenwickTree fwTree = new FenwickTree(n + 1);
        dfs(1);

        while (m-- > 0){
            int query = in.nextInt();
            
            if (query == 1){
                int i = in.nextInt();
                long w = in.nextLong();
                fwTree.update(inner[i], outer[i] + 1, w);
            }
            else {
                int i = in.nextInt();
                sb.append(fwTree.sum(inner[i])).append('\n');
            }
        }

        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32);
        return (char)c;
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

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}