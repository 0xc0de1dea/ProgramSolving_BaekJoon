//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

class SegmentTree {
    final int SIZE = 1 << 19;
    long[] arr = new long[SIZE * 2];

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

    public long query(int s, int e){
        return query(s, e, 1, 1, SIZE);
    }

    long query(int s, int e, int node, int ns, int ne){
        if (e < ns || ne < s){
            return 0;
        }
        
        if (s <= ns && ne <= e){
            return arr[node];
        }

        int m = (ns + ne) / 2;

        return Math.max(query(s, e, node * 2, ns, m), query(s, e, node * 2 + 1, m + 1, ne));
    }

    public void update(int idx, long value){
        idx += SIZE - 1;
        arr[idx] = Math.max(arr[idx], value);

        while (idx > 1){
            idx /= 2;
            arr[idx]= Math.max(arr[idx * 2], arr[idx * 2 + 1]);
        }
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        long[] arr = new long[n];
        HashSet<Long> hs = new HashSet<>();
        hs.add(0L);
        
        for (int i = 0; i < n; i++){
            arr[i] = in.nextLong();
            hs.add(arr[i]);
        }

        ArrayList<Long> unique = new ArrayList<>(hs);
        Collections.sort(unique);
        int hsSize = hs.size();
        HashMap<Long, Integer> hm = new HashMap<>(hsSize);

        for (int i = 1; i < hsSize; i++){
            hm.put(unique.get(i), i);
        }

        SegmentTree segTree = new SegmentTree();

        for (int i = 0; i < n; i++){
            segTree.update(hm.get(arr[i]),
            segTree.query(1, hm.get(arr[i]) - 1) + arr[i]);
        }

        System.out.print(segTree.arr[1]);
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