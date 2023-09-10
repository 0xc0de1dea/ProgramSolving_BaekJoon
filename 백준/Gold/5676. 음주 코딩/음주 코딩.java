//import java.io.FileInputStream;

class SegmentTree {
    final int SIZE = 1 << 17;
    int[] tree = new int[SIZE << 1];

    public SegmentTree(){

    }

    public SegmentTree(int[] data){
        int len = data.length;

        for (int i = 0; i < len; i++){
            tree[SIZE + i] = data[i];
        }

        optimization();
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            tree[i] = tree[i << 1] * tree[i << 1 | 1];
        }
    }

    public int query(int s, int e){
        return query(s, e, 1, 0, SIZE - 1);
    }

    int query(int s, int e, int node, int ns, int ne){
        if (e < ns || ne < s) return 1;
        if (s <= ns && ne <= e) return tree[node];

        int m = ns + ne >> 1;

        return query(s, e, node << 1, ns, m) * query(s, e, node << 1 | 1, m + 1, ne);
    }

    public void update(int idx, int value){
        idx += SIZE;
        tree[idx] = value;

        while (idx > 1){
            idx >>= 1;
            tree[idx] = tree[idx << 1] * tree[idx << 1 | 1];
        }
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = 0, k = 0;

        while((n = in.nextInt()) > 0){
            k = in.nextInt();
            int[] data = new int[n];

            for (int i = 0; i < n; i++){
                int num = in.nextInt();

                if (num > 0) data[i] = 1;
                else if (num < 0) data[i] = -1;
                else data[i] = 0;
            }

            SegmentTree segTree = new SegmentTree(data);

            for (int i = 0; i < k; i++){
                char order = in.nextChar();

                if (order == 'C'){
                    int idx = in.nextInt() - 1;
                    int num = in.nextInt();
                    num = num > 0 ? 1 : num < 0 ? -1 : 0;
                    segTree.update(idx, num);
                }
                else {
                    int a = in.nextInt() - 1;
                    int b = in.nextInt() - 1;
                    int result = segTree.query(a, b);
                    sb.append(result > 0 ? "+" : result < 0 ? "-" : "0");
                }
            }

            sb.append('\n');
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