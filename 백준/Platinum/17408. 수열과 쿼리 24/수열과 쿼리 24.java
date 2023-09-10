//import java.io.FileInputStream;

class Pair {
    public int[] pair;

    public Pair(int a, int b){
        pair = new int[] { a, b };
    }

    public void add(int value){
        if (pair[0] == 0) pair[0] = value;
        else pair[1] = value;
    }
}

class SegmentTree {
    final int SIZE = 1 << 17;
    Pair[] tree = new Pair[SIZE << 1];

    public SegmentTree(){ }

    public SegmentTree(int[] data){
        int len = data.length;
        tree[SIZE] = new Pair(0, 0);

        for (int i = 1; i < len; i++){
            tree[i | SIZE] = new Pair(data[i], 0);
        }

        for (int i = len; i < SIZE; i++){
            tree[i | SIZE] = new Pair(0, 0);
        }

        optimization();
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            tree[i] = merge(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    Pair merge(Pair l, Pair r){
        Pair t = new Pair(0, 0);
        int low = 0, high = 0;

        while (low < 2 && high < 2 && t.pair[1] == 0){
            if (l.pair[low] >= r.pair[high]) t.add(l.pair[low++]);
            else t.add(r.pair[high++]);
        }

        return t;
    }

    public Pair query(int s, int e){
        Pair t = new Pair(0, 0);
        s |= SIZE;
        e |= SIZE;

        while (s <= e){
            if ((s & 1) == 1) t = merge(t, tree[s++]);
            if ((~e & 1) == 1) t = merge(t, tree[e--]);

            s >>= 1;
            e >>= 1;
        }

        return t;
    }

    public void update(int idx, int value){
        idx |= SIZE;
        tree[idx] = new Pair(value, 0);

        while (idx > 1){
            idx >>= 1;
            tree[idx] = merge(tree[idx << 1], tree[idx << 1 | 1]);
        }
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

        SegmentTree segTree = new SegmentTree(arr);
        int m = in.nextInt();

        while(m-- > 0){
            int query = in.nextInt();

            if (query == 1){
                int i = in.nextInt();
                int v = in.nextInt();
                segTree.update(i, v);
            }
            else {
                int l = in.nextInt();
                int r = in.nextInt();
                Pair t = segTree.query(l, r);
                sb.append(t.pair[0] + t.pair[1]).append('\n');
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