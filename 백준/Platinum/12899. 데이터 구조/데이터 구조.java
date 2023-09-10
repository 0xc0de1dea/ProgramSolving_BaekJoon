//import java.io.FileInputStream;

class SegmentTree {
    final int SIZE = 1 << 21;
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
        final int MIN = 1;
        final int MAX = 2_000_000;
        
        int n = in.nextInt();
        SegmentTree segTree = new SegmentTree();
        
        for (int i = 0; i < n; i++){
            int t = in.nextInt();

            if (t == 1){
                segTree.update(MIN, MAX, 1, in.nextInt(), 1);
            }
            else {
                int num = segTree.query(MIN, MAX, 1, in.nextInt());
                segTree.update(MIN, MAX, 1, num, -1);
                sb.append(num).append('\n');
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