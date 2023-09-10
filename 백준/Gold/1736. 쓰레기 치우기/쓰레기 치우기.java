//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;

class Point implements Comparable<Point>{
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o){
        return this.x != o.x ? this.x - o.x : o.y - this.y;
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

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final int MIN = -1234567890;
        
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Point> trash = new ArrayList<>();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                int check = in.nextInt();

                if (check == 1){
                    trash.add(new Point(i, -j));
                }
            }
        }

        Collections.sort(trash);
        int[] lis = new int[n + 1];
        for (int i = 0; i <= n; i++) lis[i] = MIN;
        int len = 0;
        int sz = trash.size();

        for (int i = 0; i < sz; i++){
            int cur = trash.get(i).y;

            if (cur > lis[len]){
                lis[++len] = cur;
            }
            else {
                lis[lowerBound(lis, 1, len, cur)] = cur;
            }
        }

        System.out.print(len);
    }
}

/* 빠른 입력 */
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