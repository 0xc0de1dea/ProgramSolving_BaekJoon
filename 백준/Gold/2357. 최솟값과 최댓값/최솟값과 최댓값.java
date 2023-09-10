//import java.io.FileInputStream;

class SegmentTree {
    final int SIZE = 1 << 17;
    final int MAX = 1234567890;
    int[] minTree = new int[SIZE << 1];
    int[] maxTree = new int[SIZE << 1];

    public SegmentTree(){

    }

    public SegmentTree(int[] data){
        int len = data.length;

        for (int i = 0; i < len; i++){
            minTree[SIZE + i] = data[i];
            maxTree[SIZE + i] = data[i];
        }

        for (int i = len; i < SIZE; i++){
            minTree[SIZE + i] = MAX;
        }

        optimization();
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            minTree[i] = Math.min(minTree[i << 1], minTree[i << 1 | 1]);
            maxTree[i] = Math.max(maxTree[i << 1], maxTree[i << 1 | 1]);
        }
    }

    public int query1(int s, int e){
        return query1(s, e, 1, 0, SIZE - 1);
    }

    public int query2(int s, int e){
        return query2(s, e, 1, 0, SIZE - 1);
    }

    int query1(int s, int e, int node, int ns, int ne){
        if (e < ns || ne < s) return MAX;
        if (s <= ns && ne <= e) return minTree[node];

        int m = ns + ne >> 1;
        int l = query1(s, e, node << 1, ns, m);
        int r = query1(s, e, node << 1 | 1, m + 1, ne);

        return Math.min(l, r);
    }

    int query2(int s, int e, int node, int ns, int ne){
        if (e < ns || ne < s) return 0;
        if (s <= ns && ne <= e) return maxTree[node];

        int m = ns + ne >> 1;
        int l = query2(s, e, node << 1, ns, m);
        int r = query2(s, e, node << 1 | 1, m + 1, ne);

        return Math.max(l, r);
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int m = in.nextInt();
        int[] sequence = new int[n];

        for (int i = 0; i < n; i++){
            sequence[i] = in.nextInt();
        }

        SegmentTree segTree = new SegmentTree(sequence);

        for (int i = 0; i < m; i++){
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            sb.append(segTree.query1(a, b)).append(' ').append(segTree.query2(a, b)).append('\n');
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