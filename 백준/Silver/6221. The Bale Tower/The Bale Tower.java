//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;

class Bale implements Comparable<Bale>{
    public int a, b;

    public Bale(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Bale o){
        return this.a != o.a ? this.a - o.a : o.b - this.b;
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
        
        int n = in.nextInt();
        ArrayList<Bale> bales = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < n; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            bales.add(new Bale(a, b));
            max = Math.max(max, Math.max(a, b));
        }

        Collections.sort(bales);
        int[] lis = new int[max + 1];
        int len = 0;
        int sz = bales.size();

        for (int i = 0; i < sz; i++){
            int cur = bales.get(i).b;

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