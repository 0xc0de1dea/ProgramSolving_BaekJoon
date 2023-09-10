//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Line implements Comparable<Line> {
    public int x, y1, y2, value;

    public Line(int x, int y1, int y2, int value){
        this.x = x;
        this.y1 = y1;
        this.y2 = y2;
        this.value = value;
    }

    @Override
    public int compareTo(Line o){
        return this.x - o.x;
    }
}

class SegmentTree {
    final int SIZE = 1 << 19;
    long[] tree = new long[SIZE << 1];
    int[] cnt = new int[SIZE << 1];
    int[] non_compressed;

    public SegmentTree(int[] non_compressed){
        this.non_compressed = non_compressed;
    }

    public void update(int s, int e, int value){
        update(s, e, 1, value, 0, SIZE - 1);
    }

    void update(int s, int e, int node, int value, int ns, int ne){
        if (e < ns || ne < s) return;

        if (s <= ns && ne <= e){
            cnt[node] += value;
        }
        else {
            int m = ns + ne >> 1;
            update(s, e, node << 1, value, ns, m);
            update(s, e, node << 1 | 1, value, m + 1, ne);
        }

        tree[node] = cnt[node] > 0 ? (long)non_compressed[ne] - non_compressed[ns - 1] : ne != ns ? tree[node << 1] + tree[node << 1 | 1] : 0;
    }

    public long getYLen(){
        return tree[1];
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        ArrayList<Line> lines = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();
        HashMap<Integer, Integer> compressed = new HashMap<>(400_001);
        int[] non_compressed = new int[400_001];

        for (int i = 0; i < n; i++){
            int x1 = in.nextInt();
            int x2 = in.nextInt();
            int y1 = in.nextInt();
            int y2 = in.nextInt();

            lines.add(new Line(x1, y1, y2, 1));
            lines.add(new Line(x2, y1, y2, -1));
            yList.add(y1);
            yList.add(y2);
        }

        Collections.sort(yList);
        Collections.sort(lines);
        int ysz = yList.size();
        int id = 0;
        compressed.put(yList.get(0), id);
        non_compressed[id] = yList.get(0);

        for (int i = 1; i < ysz; i++){
            if (!yList.get(i).equals(yList.get(i - 1))) id++;

            compressed.put(yList.get(i), id);
            non_compressed[id] = yList.get(i);
        }

        SegmentTree segTree = new SegmentTree(non_compressed);
        int lnsz = lines.size();
        long area = 0;
        long prev = 0;

        for (int i = 0; i < lnsz; i++){
            area += segTree.getYLen() * (lines.get(i).x - prev);
            prev = lines.get(i).x;            
            segTree.update(compressed.get(lines.get(i).y1) + 1, compressed.get(lines.get(i).y2), lines.get(i).value);
        }

        System.out.print(area);
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