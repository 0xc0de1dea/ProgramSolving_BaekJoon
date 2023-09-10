//import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int[] arr;
    static ArrayList<Integer> setA = new ArrayList<>();
    static ArrayList<Integer> setB = new ArrayList<>();

    public static void combine(ArrayList<Integer> set, int l, int r, int sum){
        if (l > r){
            set.add(sum);
            return;
        }

        combine(set, l + 1, r, sum);
        combine(set, l + 1, r, sum + arr[l]);
    }

    public static int upperBound(ArrayList<Integer> set, int target){
        int s = 0, e = set.size();

        while (s < e){
            int m = s + e >> 1;

            if (set.get(m) <= target) s = m + 1;
            else e = m;
        }

        return e;
    }

    public static int lowerBound(ArrayList<Integer> set, int target){
        int s = 0, e = set.size();

        while (s < e){
            int m = s + e >> 1;

            if (set.get(m) < target) s = m + 1;
            else e = m;
        }

        return e;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int s = in.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = in.nextInt();

        combine(setA, 0, n >> 1, 0);
        combine(setB, (n >> 1) + 1, n - 1, 0);
        Collections.sort(setB);

        long cnt = 0;
        int sz = setA.size();

        for (int i = 0; i < sz; i++){
            cnt += (long)upperBound(setB, s - setA.get(i)) - lowerBound(setB, s - setA.get(i));
        }

        System.out.print(s == 0 ? cnt - 1 : cnt);
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