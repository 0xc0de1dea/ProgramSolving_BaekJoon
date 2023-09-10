/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

public class Main {
    /*static int n;
    static long[] tower;
    static final int MAX = 1 << 20;
    static int[] phi = new int[MAX];*/

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

    static long mod(long a, long b){
        if (a > b) return b + a % b;
        else return a;
    }

    static long fastPow(long x, long y, long mod){
        long ret = 1;

        while (y > 0){
            if ((y & 1) == 1) ret = mod(ret * x, mod);
            x = mod(x * x, mod); y >>= 1;
        }

        return ret;
    }

    static long g(long x, long m, long depth){
        if (depth == 0) return 1;
        return fastPow(x, g(x - 1, m, depth - 1), m);
    }

    static long f(long x, long m){
        if (m == 1) return 1;
        if (x <= 5) return g(x, m, x);
        long phi = phi(m);
        return fastPow(x, f(x - 1, phi) + phi, m);
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        //for (int i = 1; i < MAX; i++) phi[i] = i;
        //for (int i = 1; i < MAX; i++) for (int j = i + i; j < MAX; j += i) phi[j] -= phi[i];

        int n = in.nextInt();
        int m = in.nextInt();

        long res = f(n, m) % m;
        System.out.print(res);
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