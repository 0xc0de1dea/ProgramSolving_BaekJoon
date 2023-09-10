//import java.io.FileInputStream;

import java.util.ArrayList;

class MergeSortTree {
    final int SIZE = 1 << 17;
    ArrayList<Integer>[] tree = new ArrayList[SIZE << 1];

    public MergeSortTree(int[] data){
        int len = data.length;
        tree[SIZE] = new ArrayList<>();

        for (int i = 1; i < len; i++){
            tree[i | SIZE] = new ArrayList<>();
            tree[i | SIZE].add(data[i]);
        }

        for (int i = len; i < SIZE; i++){
            tree[i | SIZE] = new ArrayList<>();
        }

        construction();
    }

    void construction(){
        for (int i = SIZE - 1; i > 0; i--){
            tree[i] = merge(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right){
        ArrayList<Integer> sorted = new ArrayList<>();
        int szl = left.size(), szr = right.size();
        int l = 0, r = 0;

        while (l < szl && r < szr){
            if (left.get(l) <= right.get(r)){
                sorted.add(left.get(l++));
            }
            else {
                sorted.add(right.get(r++));
            }
        }

        while (l < szl) sorted.add(left.get(l++));
        while (r < szr) sorted.add(right.get(r++));

        return sorted;
    }

    public int query(int l, int r, int k){
        int t = 0;
        l |= SIZE;
        r |= SIZE;

        while (l <= r){
            if ((l & 1) == 1) t += upperBound(tree[l++], k);
            if ((~r & 1) == 1) t += upperBound(tree[r--], k);

            l >>= 1;
            r >>= 1;
        }

        return t;
    }

    int upperBound(ArrayList<Integer> data, int target){
        int s = 0;
        int e = data.size();

        while (s < e){
            int m = s + e >> 1;

            if (data.get(m) <= target) s = m + 1;
            else e = m;
        }

        return e;
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = new int[n + 1];
        
        for (int i = 1; i <= n; i++) arr[i] = in.nextInt();

        MergeSortTree msTree = new MergeSortTree(arr);

        while (m-- > 0){
            int i = in.nextInt();
            int j = in.nextInt();
            int k = in.nextInt();

            int l = (int)-1e9, r = (int)1e9;

            while (l < r){
                int mid = l + r >> 1;
                int cnt = msTree.query(i, j, mid);

                if (cnt < k) l = mid + 1;
                else r = mid;
            }

            sb.append(r).append('\n');
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