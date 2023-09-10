/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

public class Main {
    static final int MOD = 1999;
    static long[] a, c;
    static int k;

    public static void inc(long[] d, long[] t){
        t[0] = (d[k - 1] * c[0]) % MOD;

        for (int i = 1; i < k; i++) t[i] = (d[i - 1] + d[k - 1] * c[i]) % MOD;
    }

    public static void mul(long[] d, long[] t){
        long[][] dx2 = new long[k][k];

        for (int i = 0; i < k; i++) dx2[0][i] = d[i];
        for (int i = 0; i < k - 1; i++) inc(dx2[i], dx2[i + 1]);
        for (int i = 0; i < k; i++){
            t[i] = 0;
            for (int j = 0; j < k; j++) t[i] = (t[i] + dx2[0][j] * dx2[j][i]) % MOD;
        }
    }

    public static long kitamasa(long n){
        long[] d = new long[k]; d[1] = 1;
        long[] t = new long[k];

        long ans = 0;
        int shift = 34;

        while (((n >> --shift) & 1) == 0);
        while (shift-- > 0){
            mul(d, d);
            if (((n >> shift) & 1) == 1){
                inc(d, t);
                for (int i = 0; i < k; i++) d[i] = t[i];
            }
        }

        for (int i = 0; i < k; i++) ans = (ans + d[i] * a[i]) % MOD;

        return ans;
    }

    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();

        int n = in.nextInt();
        long m = in.nextLong();
        a = new long[n];
        c = new long[n];
        k = n; a[0] = 1;
        
        for (int i = 1; i < n; i++) a[i] = (a[i - 1] << 1) % MOD;
        for (int i = 0; i < n; i++) c[i] = 1;
        for (int i = 0; i < n - 1; i++) c[0] = (c[0] << 1) % MOD;
        a[n - 1] = ((a[n - 1] << 1) - 1) % MOD;

        long ans = n != 1 ? kitamasa(m - 1) : a[0];
        System.out.print(ans);
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