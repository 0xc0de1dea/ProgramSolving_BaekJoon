//import java.io.FileInputStream;

class SegmentTree {
    final int SIZE = 1 << 17;
    int[] tree = new int[SIZE << 1];

    public SegmentTree(){ }

    public SegmentTree(int[] data){
        int len = data.length;

        for (int i = 0; i < len; i++){
            tree[i | SIZE] = data[i];
        }

        optimization();
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }

    public int query(int idx){
        return query(0, SIZE - 1, 1, idx);
    }

    int query(int s, int e, int node, int idx){
        tree[node]--;
        
        if (s == e) return s;

        int m = s + e >> 1;

        if (idx > tree[node << 1]) return query(m + 1, e, node << 1 | 1, idx - tree[node << 1]);
        else return query(s, m, node << 1, idx);
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = 1;

        SegmentTree segTree = new SegmentTree(arr);
        int idx = k - 1;
        sb.append("<");
        
        for (int i = 1; i <= n; i++){
            int getIdx = segTree.query(idx + 1) + 1;

            if (i != n) sb.append(getIdx).append(", ");
            else sb.append(getIdx);

            idx += k - 1;
            idx %= segTree.tree[1] != 0 ? segTree.tree[1] : 1;
        }

        sb.append(">");
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