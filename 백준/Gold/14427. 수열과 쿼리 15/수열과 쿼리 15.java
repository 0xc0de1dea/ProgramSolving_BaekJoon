//import java.io.FileInputStream;

class SegmentTree {
    final int SIZE = 1 << 17;
    final int MAX = 1234567890;
    int[] tree = new int[SIZE << 1];
    int[] index = new int[SIZE << 1];

    public SegmentTree(){

    }

    public SegmentTree(int[] data){
        int len = data.length;

        for (int i = 0; i < len; i++){
            tree[SIZE + i] = data[i];
            index[SIZE + i] = i + 1;
        }

        for (int i = len; i < SIZE; i++){
            tree[SIZE + i] = MAX;
        }

        optimization();
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            int l = tree[i << 1];
            int r = tree[i << 1 | 1];

            if (l > r){
                tree[i] = r;
                index[i] = index[i << 1 | 1];
            }
            else {
                tree[i] = l;
                index[i] = index[i << 1];
            }
        }
    }

    public int query(){
        return index[1];
    }

    public void update(int idx, int value){
        idx += SIZE;
        tree[idx] = value;

        while (idx > 1){
            idx >>= 1;
            int l = tree[idx << 1];
            int r = tree[idx << 1 | 1];

            if (l > r){
                tree[idx] = r;
                index[idx] = index[idx << 1 | 1];
            }
            else {
                tree[idx] = l;
                index[idx] = index[idx << 1];
            }
        }
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int[] sequence = new int[n];

        for (int i = 0; i < n; i++){
            sequence[i] = in.nextInt();
        }

        int m = in.nextInt();
        SegmentTree segTree = new SegmentTree(sequence);

        for (int i = 0; i < m; i++){
            int a = in.nextInt();

            if ((a & 1) == 0){
                sb.append(segTree.query()).append('\n');
            }
            else {
                int b = in.nextInt() - 1;
                int c = in.nextInt();
                segTree.update(b, c);
            }
        }
        
        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);//{ if (size == -1) return -1; }
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