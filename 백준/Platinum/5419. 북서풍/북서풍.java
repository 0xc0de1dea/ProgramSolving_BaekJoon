import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class SegmentTree {
    final int SIZE = 1 << 17;
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

    public long sum(int s, int e){
        return sum(s, e, 1, 0, SIZE - 1);
    }

    long sum(int s, int e, int node, int ns, int ne){
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

    /*
    public void print(){
        StringBuilder sb = new StringBuilder();

        for (int i = SIZE; i < SIZE + 4; i++){
            sb.append(arr[i]).append(' ');
        }
        sb.append('\n');

        System.out.println(sb);
    }
    */
}

class Point {
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int MAX = 1 << 17;

    public static void main(String[] argu) throws Exception {
        //long start = System.nanoTime();
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int t = in.nextInt();

        while (t-- > 0){
            int n = in.nextInt();
            ArrayList<Point> island = new ArrayList<>();

            for (int i = 0; i < n; i++){
                island.add(new Point(in.nextInt(), in.nextInt()));
            }

            Collections.sort(island, new Comparator<Point>() {
                @Override
                public int compare(Point p1, Point p2){
                    return p2.y - p1.y;
                }
            });

            int[] redefine = new int[n];
            int id = 0;

            for (int i = 1; i < n; i++){
                if (island.get(i).y != island.get(i - 1).y) id++;

                redefine[i] = id;
            }

            for (int i = 0; i < n; i++){
                island.get(i).y = redefine[i];
            }

            Collections.sort(island, new Comparator<Point>() {
                @Override
                public int compare(Point p1, Point p2){
                    if (p1.x == p2.x){
                        return p1.y - p2.y;
                    }
                    else {
                        return p1.x - p2.x;
                    }
                }
            });

            SegmentTree segTree = new SegmentTree();
            long cnt = 0;

            for (int i = 0; i < n; i++){
                cnt += segTree.sum(0, island.get(i).y);
                segTree.increase(island.get(i).y, 1);
            }

            sb.append(cnt).append('\n');
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