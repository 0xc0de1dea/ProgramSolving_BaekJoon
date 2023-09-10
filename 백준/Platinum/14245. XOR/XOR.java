//import java.io.FileInputStream;

class SegmentTree {
    final int SIZE = 1 << 19;
    int[] tree = new int[SIZE << 1];
    int[] lazy = new int[SIZE << 1];
    int n;

    public SegmentTree(){

    }

    public SegmentTree(int[] data, int n){
        this.n = n;
        init(1, n, 1, data);
    }

    int init(int s, int e, int node, int[] data){
        if (s == e){
            return tree[node] = data[s];
        }

        int m = s + e >> 1;
        return tree[node] = init(s, m, node << 1, data) ^ init(m + 1, e, node << 1 | 1, data);
    }

    void propagate(int node, int ns, int ne){
        if (lazy[node] != 0){
            if ((ne - ns + 1) % 2 == 1){
                tree[node] ^= lazy[node];
            }

            if (ns != ne){
                lazy[node << 1] ^= lazy[node];
                lazy[node << 1 | 1] ^= lazy[node];
            }

            lazy[node] = 0;
        }
    }

    void update(int s, int e, int value){
        update(s, e, value, 1, 1, n);
    }

    void update(int s, int e, int value, int node, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s) return;

        if (s <= ns && ne <= e){
            lazy[node] = value;
            propagate(node, ns, ne);
            return;
        }

        int mid = ns + ne >> 1;
        update(s, e, value, node << 1, ns, mid);
        update(s, e, value, node << 1 | 1, mid + 1, ne);

        tree[node] = tree[node << 1] ^ tree[node << 1 | 1];
    }

    public int query(int s, int e){
        return query(s, e, 1, 1, n);
    }

    int query(int s, int e, int node, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s) return 0;
        if (s <= ns && ne <= e) return tree[node];

        int m = ns + ne >> 1;

        return query(s, e, node << 1, ns, m) ^ query(s, e, node << 1 | 1, m + 1, ne);
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; i++) arr[i] = in.nextInt();

        SegmentTree segTree = new SegmentTree(arr, n);
        int m = in.nextInt();

        while (m-- > 0){
            int q = in.nextInt();

            if (q == 1){
                int i = in.nextInt() + 1;
                int j = in.nextInt() + 1;
                int k = in.nextInt();
                segTree.update(i, j, k);
            }
            else {
                int i = in.nextInt() + 1;
                sb.append(segTree.query(i, i)).append('\n');
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