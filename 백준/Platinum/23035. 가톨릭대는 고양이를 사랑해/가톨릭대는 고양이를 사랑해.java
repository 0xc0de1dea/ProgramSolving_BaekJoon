//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;

class Point implements Comparable<Point> {
    public int r, c;

    public Point(int r, int c){
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Point o){
        return this.r == o.r ? this.c - o.c : this.r - o.r;
    }
}

public class Main {
    public static int lowerBound(int[] arr, int s, int e, int target){
        while (s != e){
            int m = (s + e) / 2;

            if (arr[m] < target) s = m + 1;
            else e = m;
        }
        return e;
    }

    public static int upperBound(int[] arr, int s, int e, int target){
        while (s != e){
            int m = (s + e) / 2;

            if (arr[m] <= target) s = m + 1;
            else e = m;
        }
        return e;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int m = in.nextInt();
        int t = in.nextInt();
        ArrayList<Point> cats = new ArrayList<>();

        for (int i = 0; i < t; i++){
            int r = in.nextInt();
            int c = in.nextInt();

            if (r >= 0 && r <= n && c >= 0 && c <= m){
                cats.add(new Point(r, c));
            }
        }

        Collections.sort(cats);
        int[] lis = new int[t + 1];
        int len = 0;

        for (Point cat : cats){
            if (cat.c >= lis[len]){
                lis[++len] = cat.c;
            }
            else {
                lis[upperBound(lis, 1, len, cat.c)] = cat.c;
            }
        }

        System.out.print(len);
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