//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

class Point implements Comparable<Point>{
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o){
        return this.x != o.x ? o.x - this.x : this.y - o.y;
    }

    public boolean equals(Object obj){
        if (obj instanceof Point){
            Point tmp = (Point)obj;
            return x == tmp.x && y == tmp.y;
        }
        return false;
    }

    public int hashCode(){
        return Objects.hash(x, y);
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

    public static void tracking(int[] lenData, int len, int[] copy){
        int cur = lenData.length - 1;
        
        while (len > 0){
            if (lenData[cur] == len){
                copy[len--] = cur;
            }
            cur--;
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int t = in.nextInt();

        while (t-- > 0){
            int n = in.nextInt();
            int[] lis = new int[n + 1];
            int[] lenData = new int[n + 1];
            int len = 0;

            for (int i = 1; i <= n; i++){
                int trophy = in.nextInt();

                if (trophy > lis[len]){
                    lis[++len] = trophy;
                    lenData[i] = len;
                }
                else {
                    int idx = lowerBound(lis, 1, len, trophy);
                    lis[idx] = trophy;
                    lenData[i] = idx;
                }
            }

            sb.append(len).append('\n');
            tracking(lenData, len, lis);

            for (int i = 1; i <= len; i++){
                sb.append(lis[i]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
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