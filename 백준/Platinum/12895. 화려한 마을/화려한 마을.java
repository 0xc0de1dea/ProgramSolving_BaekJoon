/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

class SegmentTree {
    final int SIZE = 1 << 17;
    long[] tree = new long[SIZE << 1];
    long[] lazy = new long[SIZE << 1];

    public SegmentTree(int n){
        update(1, n, 1);
        
        /*
        for (int i = 1; i <= n; i++){
            tree[i | SIZE] = 1;
        }

        optimization();
        */
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            tree[i] = tree[i << 1] | tree[i << 1 | 1];
        }
    }

    void propagate(int node, int ns, int ne){
        if (lazy[node] != 0){
            if (node < SIZE) {
                lazy[node << 1] = lazy[node];
                lazy[node << 1 | 1] = lazy[node];
            }

            tree[node] = lazy[node];
            lazy[node] = 0;
        }
    }

    public void update(int s, int e, long value){
        update(s, e, 1, value, 1, SIZE);
    }

    void update(int s, int e, int node, long value, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s) return;

        if (s <= ns && ne <= e){
            lazy[node] = value;
            propagate(node, ns, ne);
            return;
        }

        int m = ns + ne >> 1;
        update(s, e, node << 1, value, ns, m);
        update(s, e, node << 1 | 1, value, m + 1, ne);
        tree[node] = tree[node << 1] | tree[node << 1 | 1];
    }

    public long sum(int s, int e){
        return sum(s, e, 1, 1, SIZE);
    }

    long sum(int s, int e, int node, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s) return 0;
        if (s <= ns && ne <= e) return tree[node];

        int m = ns + ne >> 1;

        return sum(s, e, node << 1, ns, m) | sum(s, e, node << 1 | 1, m + 1, ne);
    }
}

public class Main {
    public static long bitCounting(long bit){
        long cnt = 0;

        while (bit > 0){
            cnt += bit & 1;
            bit >>= 1;
        }

        return cnt;
    }

    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int t = in.nextInt();
        int q = in.nextInt();

        SegmentTree segTree = new SegmentTree(n);

        while (q-- > 0){
            char query = in.nextChar();

            if (query == 'C'){
                int x = in.nextInt();
                int y = in.nextInt();
                long z = in.nextLong() - 1;

                if (x <= y) segTree.update(x, y, 1 << z);
                else segTree.update(y, x, 1 << z);
            }
            else {
                int x = in.nextInt();
                int y = in.nextInt();
                
                if (x <= y) sb.append(bitCounting(segTree.sum(x, y))).append('\n');
                else sb.append(bitCounting(segTree.sum(y, x))).append('\n');
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