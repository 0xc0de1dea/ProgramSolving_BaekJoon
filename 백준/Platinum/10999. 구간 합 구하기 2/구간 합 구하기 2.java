class SegmentTree {
    final int SIZE = 1 << 20;
    long[] arr = new long[SIZE * 2];
    long[] lazy = new long[SIZE * 2];

    public SegmentTree(){

    }

    public SegmentTree(long[] data){
        int len = data.length;

        for (int i = 0; i < len; i++){
            arr[SIZE + i] = data[i];
        }

        optimization();
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            arr[i] = arr[i * 2] + arr[i * 2 + 1];
        }
    }

    void propagate(int node, int ns, int ne){
        if (lazy[node] != 0){
            if (node < SIZE){
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            arr[node] += lazy[node] * (ne - ns + 1);
            lazy[node] = 0;
        }
    }

    void add(int s, int e, long value){
        add(s, e, value, 1, 1, SIZE);
    }

    void add(int s, int e, long value, int node, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s) return;

        if (s <= ns && ne <= e){
            lazy[node] += value;
            propagate(node, ns, ne);
            return;
        }

        int mid = (ns + ne) / 2;
        add(s, e, value, node * 2, ns, mid);
        add(s, e, value, node * 2 + 1, mid + 1, ne);
        arr[node] = arr[node * 2] + arr[node * 2 + 1];
    }

    public long sum(int s, int e){
        return sum(s, e, 1, 1, SIZE);
    }

    long sum(int s, int e, int node, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s){
            return 0;
        }
        
        if (s <= ns && ne <= e){
            return arr[node];
        }

        int m = (ns + ne) / 2;

        return sum(s, e, node * 2, ns, m) + sum(s, e, node * 2 + 1, m + 1, ne);
    }

    public void update(int idx, long value){
        idx += SIZE;
        arr[idx] = value;

        while (idx > 1){
            idx /= 2;
            arr[idx]= arr[idx * 2] + arr[idx * 2 + 1];
        }
    }

    public void increase(int idx, long value){
        idx += SIZE;

        while (idx > 0){
            arr[idx] += value;
            idx /= 2;
        }
    }
}

public class Main {
    static final int MAX = 1 << 20;

    public static void main(String[] argu) throws Exception {
        //long start = System.nanoTime();
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        long[] arr = new long[n];

        for (int i = 0; i < n; i++){
            arr[i] = in.nextLong();
        }

        SegmentTree segTree = new SegmentTree(arr);

        for (int i = 0; i < m + k; i++){
            int a = in.nextInt();

            if (a == 1){
                int b = in.nextInt();
                int c = in.nextInt();
                long d = in.nextLong();

                segTree.add(b, c, d);
            }
            else {
                int b = in.nextInt();
                int c = in.nextInt();

                sb.append(segTree.sum(b, c)).append('\n');
            }
        }

        System.out.print(sb);

        //long end = System.nanoTime();
        //System.out.println("수행시간: " + (end - start) + " ns");
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
        while ((c = read()) <= 32);
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