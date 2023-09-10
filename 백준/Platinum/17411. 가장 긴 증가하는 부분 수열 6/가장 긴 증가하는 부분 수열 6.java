//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;

class Pair implements Comparable<Pair>, Cloneable{
    public long value, idx;

    public Pair(long value, long idx){
        this.value = value;
        this.idx = idx;
    }

    @Override
    public int compareTo(Pair o){
        return this.value != o.value ? (int)(this.value - o.value) : (int)(o.idx - this.idx);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class SegmentTree {
    final int SIZE = 1 << 20;
    final int MOD = 1000000007;
    Pair[] tree = new Pair[SIZE << 1];

    public SegmentTree(){
        for (int i = 0; i < SIZE << 1; i++){
            tree[i] = new Pair(0, 0);
        }
    }

    public SegmentTree(long[] data){
        int len = data.length;

        for (int i = 0; i < len; i++){
            tree[SIZE + i].value = data[i];
        }

        optimization();
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            tree[i].value = tree[i << 1].value + tree[i << 1 | 1].value;
        }
    }

    Pair merge(Pair a, Pair b){
        if (a.value > b.value) return a;
        else if (a.value < b.value) return b;
        else return new Pair(a.value, (a.idx + b.idx) % MOD);
    }

    public Pair get(int s, int e){
        return get(s, e, 1, 0, SIZE - 1);
    }

    Pair get(int s, int e, int node, int ns, int ne){
        if (e < ns || ne < s) return new Pair(0, 0);
        if (s <= ns && ne <= e) return tree[node];

        int m = ns + ne >> 1;
        Pair l = get(s, e, node << 1, ns, m);
        Pair r = get(s, e, node << 1 | 1, m + 1, ne);

        return merge(l, r);
    }

    public void update(int idx, Pair p){
        idx += SIZE;
        tree[idx] = p;

        while (idx > 1){
            idx >>= 1;
            tree[idx] = merge(tree[idx << 1], tree[idx << 1 | 1]);
        }
    }

    public Pair ans(){
        return tree[1];
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final int INIT = 1234567890;
        
        int n = in.nextInt();
        ArrayList<Pair> pair = new ArrayList<>();

        for (int i = 0; i < n; i++){
            pair.add(new Pair(in.nextInt(), i));
        }

        Collections.sort(pair);
        int id = 0;
        long prev = INIT;
        int sz = pair.size();

        for (int i = 0; i < sz; i++){
            if (pair.get(i).value != prev){
                prev = pair.get(i).value;
                pair.get(i).value = ++id;
            }
            else {
                pair.get(i).value = id;
            }
        }

        SegmentTree segTree = new SegmentTree();
        
        for (int i = 0; i < n; i++){
            Pair tmp = (Pair)segTree.get(0, (int)(pair.get(i).idx - 1)).clone();
            tmp.value += 1;
            tmp.idx = Math.max(1, tmp.idx);
            segTree.update((int)pair.get(i).idx, tmp);
        }

        Pair ans = segTree.ans();
        System.out.print(ans.value + " " + ans.idx);
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