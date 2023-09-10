/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

public class Main {
    static final int mod = (int)1e9 + 7;

    public static long pwr_mod(long a, long b){
        a %= mod;
        long res = 1;

        while (b > 0){
            if ((b & 1) == 1) res = (res * a) % mod;

            a = (a * a) % mod;
            b >>= 1;
        }

        return res;
    }

    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();

        int n = in.nextInt();
        int p = in.nextInt();
        int[][] c = new int[p + 2][p + 2];

        for (int i = 0; i <= p + 1; i++){
            for (int j = 0; j <= i; j++){
                if (i == 0 || j == 0 || j == i){
                    c[i][j] = 1;
                    continue;
                }

                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
            }
        }

        long[] f = new long[p + 1];
        f[0] = n;

        for (int i = 1; i <= p; i++){
            long t = pwr_mod(n + 1, i + 1);
            t--;

            for (int j = 0; j < i; j++){
                t -= (c[i + 1][j] * f[j]) % mod;

                if (t < 0) t = (t + mod) % mod;
                else t %= mod;
            }

            f[i] = (t * pwr_mod(i + 1, mod - 2)) % mod;
        }

        System.out.print(f[p]);
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