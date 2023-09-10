/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 * mt19937 Random Algorithm Author : datquocnguyen
 */

//import java.io.FileInputStream;

import java.util.HashSet;

public class Main {
    static long phi(long n){
        long ret = n;

        for (long i = 2; i * i <= n; i++){
            if (n % i == 0){
                while (n % i == 0) n /= i;
                ret -= ret / i;
            }
        }
        if (n > 1) ret -= ret / n;

        return ret;
    }
    
    static int gcd(int a, int b){
        while (b > 0){
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        final int MOD = (int)1e9 + 7;

        int t = in.nextInt();

        while (t-- > 0){
            int n = in.nextInt();
            int[] element = new int[n];
            long mul = 1L;
            HashSet<Integer> dic = new HashSet<>();

            for (int i = 0; i < n; i++) element[i] = in.nextInt();

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    int gcd = gcd(element[i], element[j]);
                    if (dic.contains(gcd)) continue;
                    else { dic.add(gcd); mul *= phi(gcd); mul %= MOD; }
                }
            }

            sb.append(mul).append('\n');
        }

        System.out.print(sb);
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