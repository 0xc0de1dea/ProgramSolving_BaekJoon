class SegmentTree {
    final int SIZE = 1 << 17;
    int[] arr = new int[SIZE * 2];
    int[] lazy = new int[SIZE * 2];

    public SegmentTree(){

    }

    public SegmentTree(int[] data){
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
                lazy[node * 2] ^= 1;
                lazy[node * 2 + 1] ^= 1;

                int mid = (ne + ns) / 2;
                int tmp = 0;

                if (lazy[node * 2] > 0){
                    tmp += mid - ns + 1 - arr[node * 2];
                }
                else {
                    tmp += arr[node * 2];
                }

                if (lazy[node * 2 + 1] > 0){
                    tmp += ne - mid - arr[node * 2 + 1];
                }
                else {
                    tmp += arr[node * 2 + 1];
                }

                arr[node] = tmp;
            }
            else {
                arr[node] ^= 1;
            }

            lazy[node] = 0;
        }
    }

    public void update(int s, int e){
        update(s, e, 1, 1, SIZE);
    }

    void update(int s, int e, int node, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s){
            return;
        }

        if (s <= ns && ne <= e){
            lazy[node] ^= 1;
            propagate(node, ns, ne);
            return;
        }
        
        int mid = (ns + ne) / 2;
        update(s, e, node * 2, ns, mid);
        update(s, e, node * 2 + 1, mid + 1, ne);            
        arr[node] = arr[node * 2] + arr[node * 2 + 1];
    }

    public int sum(int s, int e){
        return sum(s, e, 1, 1, SIZE);
    }

    int sum(int s, int e, int node, int ns, int ne){
        propagate(node, ns, ne);

        if (e < ns || ne < s){
            return 0;
        }

        if (s <= ns && ne <= e){
            return arr[node];
        }

        int mid = (ns + ne) / 2;
        return sum(s, e, node * 2, ns, mid) + sum(s, e, node * 2 + 1, mid + 1, ne);
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //long start = System.nanoTime();
        //System.setIn(new FileInputStream("input.in"));

        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();

        SegmentTree segTree = new SegmentTree();

        for (int i = 0; i < m; i++){
            int o = in.nextInt();
            
            if (o == 0){
                int s = in.nextInt();
                int t = in.nextInt();

                segTree.update(s, t);
            }
            else {
                int s = in.nextInt();
                int t = in.nextInt();

                sb.append(segTree.sum(s, t)).append('\n');
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