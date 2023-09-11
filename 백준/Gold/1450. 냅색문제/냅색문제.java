/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int n, c;
    static ArrayList<Long> setA = new ArrayList<>();
    static ArrayList<Long> setB = new ArrayList<>();

    static void combine(long[] weight, char type , int s, int e, long sum){
        if (sum > c) return;
        if (s > e){
            if (type == 'A') setA.add(sum);
            else if (type == 'B') setB.add(sum);
            return;
        }

        combine(weight, type, s + 1, e, sum);
        combine(weight, type, s + 1, e, sum + weight[s]);
    }

    static int upperbound(ArrayList<Long> source, long target, int s, int e){
        while (s < e){
            int m = s + e >> 1;

            if (source.get(m) <= target) s = m + 1;
            else e = m;
        }

        return e;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        c = in.nextInt();
        long[] weight = new long[n];

        for (int i = 0; i < n; i++) weight[i] = in.nextLong();

        combine(weight, 'A', 0, n / 2 - 1, 0);
        combine(weight, 'B', n / 2, n - 1, 0);
        Collections.sort(setA);

        long ans = 0;

        for (int i = setB.size() - 1; i >= 0; i--){
            int cnt = upperbound(setA, c - setB.get(i), 0, setA.size());
            ans += cnt;
        }

        System.out.print(ans);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
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

    boolean isAlphabet(byte c){
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}