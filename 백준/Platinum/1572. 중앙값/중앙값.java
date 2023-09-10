//import java.io.FileInputStream;

import java.util.LinkedList;
import java.util.Queue;

class SegmentTree {
    final int SIZE = 1 << 17;
    int[] tree = new int[SIZE << 1];

    public SegmentTree() { }

    public void update(int s, int e, int node, int value, int sign){
        if (s == e){
            tree[node] += sign > 0 ? 1 : -1;
            return;
        }

        int m = s + e >> 1;

        if (value <= m) update(s, m, node << 1, value, sign);
        else update(m + 1, e, node << 1 | 1, value, sign);

        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    public int query(int s, int e, int node, int k){
        if (s == e) return s;

        int m = s + e >> 1;
        int leftSum = tree[node << 1];

        if (k <= leftSum) return query(s, m, node << 1, k);
        else return query(m + 1, e, node << 1 | 1, k - leftSum);
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final int MIN = 0;
        final int MAX = 65536;

        int n = in.nextInt();
        int k = in.nextInt();
        SegmentTree segTree = new SegmentTree();
        Queue<Integer> q = new LinkedList<>();
        long sum = 0;
        
        for (int i = 1; i <= n; i++){
            int num = in.nextInt();
            q.add(num);
            segTree.update(MIN, MAX, 1, num, 1);

            if (i >= k){
                if ((k & 1) == 1) sum += (long)segTree.query(MIN, MAX, 1, (k + 1) >> 1);
                else sum += (long)segTree.query(MIN, MAX, 1, k >> 1);
                segTree.update(MIN, MAX, 1, q.poll(), -1);
            }
        }

        System.out.print(sum);
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