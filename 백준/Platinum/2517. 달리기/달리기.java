//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Pair {
    public int x, y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class SegmentTree {
    final int SIZE = 1 << 19;
    long[] tree = new long[SIZE * 2];

    public SegmentTree(){

    }

    public SegmentTree(long[] data){
        int len = data.length;

        for (int i = 0; i < len; i++){
            tree[SIZE + i] = data[i];
        }

        optimization();
    }

    void optimization(){
        for (int i = SIZE - 1; i > 0; i--){
            tree[i] = tree[i << 1] + tree[i << 1 | 1];
        }
    }

    public long sum(int s, int e){
        return sum(s, e, 1, 0, SIZE - 1);
    }

    long sum(int s, int e, int node, int ns, int ne){
        if (e < ns || ne < s) return 0;
        if (s <= ns && ne <= e) return tree[node];

        int m = ns + ne >> 1;

        return sum(s, e, node << 1, ns, m) + sum(s, e, node << 1 | 1, m + 1, ne);
    }

    public void update(int idx, int value){
        idx += SIZE;

        while (idx > 0){
            tree[idx] += value;
            idx /= 2;
        }
    }
}

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        ArrayList<Pair> players = new ArrayList<>();

        for (int i = 0; i < n; i++) players.add(new Pair(i, in.nextInt()));

        Collections.sort(players, new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b){
                return a.y - b.y;
            }
        });

        int id = 0;

        for (int i = 0; i < n; i++){
            players.get(i).y = id++;
        }

        Collections.sort(players, new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b){
                return a.x - b.x;
            }
        });

        SegmentTree segTree = new SegmentTree();

        for (int i = 0; i < n; i++){
            sb.append(1 + i - segTree.sum(0, players.get(i).y - 1)).append('\n');
            segTree.update(players.get(i).y, 1);
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
        while ((c = read()) <= 32) //{ if (size < 0) return -1; }
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