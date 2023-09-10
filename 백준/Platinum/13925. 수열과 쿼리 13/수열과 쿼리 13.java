/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

class SegmentTree {
    final int SIZE = 1 << 17;
    final int MOD = (int)1e9 + 7;
    long[] tree = new long[SIZE << 1];
    long[][] lazy = new long[SIZE << 1][2];

    public SegmentTree(long[] data){
        int len = data.length;

        for (int i = 1; i < len; i++){
            tree[i | SIZE] = data[i];
        }

        for (int i = SIZE * 2 - 1; i > 0; i--){
            lazy[i][0] = 1;
            lazy[i][1] = 0;
        }

        optimization();
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            tree[i] = (tree[i << 1] + tree[i << 1 | 1]) % MOD;
        }
    }

    void propagate(int node, int ns, int ne){
        if (lazy[node][0] != 1 || lazy[node][1] != 0){
            if (node < SIZE){
                lazy[node << 1][0] = lazy[node][0] * lazy[node << 1][0] % MOD;
                lazy[node << 1][1] = (lazy[node][0] * lazy[node << 1][1] + lazy[node][1]) % MOD;
                lazy[node << 1 | 1][0] = lazy[node][0] * lazy[node << 1 | 1][0] % MOD;
                lazy[node << 1 | 1][1] = (lazy[node][0] * lazy[node << 1 | 1][1] + lazy[node][1]) % MOD;
            }

            tree[node] = (lazy[node][0] * tree[node] + lazy[node][1] * (ne - ns + 1)) % MOD;
            lazy[node][0] = 1;
            lazy[node][1] = 0;
        }
    }

    public void update(int s, int e, long a, long b){
        update(s, e, 1, a, b, 0, SIZE - 1);
    }

    void update(int s, int e, int node, long a, long b, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s) return;

        if (s <= ns && ne <= e){
            lazy[node][0] = (a * lazy[node][0]) % MOD;
            lazy[node][1] = (a * lazy[node][1] + b) % MOD;
            propagate(node, ns, ne);
            return;
        }

        int m = ns + ne >> 1;
        update(s, e, node << 1, a, b, ns, m);
        update(s, e, node << 1 | 1, a, b, m + 1, ne);
        tree[node] = (tree[node << 1] + tree[node << 1 | 1]) % MOD;
    }

    public long sum(int s, int e){
        return sum(s, e, 1, 0, SIZE - 1);
    }

    long sum(int s, int e, int node, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s) return 0;
        if (s <= ns && ne <= e) return tree[node];

        int m = ns + ne >> 1;

        return (sum(s, e, node << 1, ns, m) + sum(s, e, node << 1 | 1, m + 1, ne)) % MOD;
    }
}

public class Main {
    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        long[] sequence = new long[n + 1];

        for (int i = 1; i <= n; i++) sequence[i] = in.nextLong();

        SegmentTree segTree = new SegmentTree(sequence);
        int q = in.nextInt();
        
        while (q-- > 0){
            int query = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            long v = query != 4 ? in.nextLong() : 0;

            if (query == 1){
                segTree.update(x, y, 1, v);
            }
            else if (query == 2){
                segTree.update(x, y, v, 0);
            }
            else if (query == 3){
                segTree.update(x, y, 0, v);
            }
            else {
                sb.append(segTree.sum(x, y)).append('\n');
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