/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

import java.io.FileInputStream;

public class Main{
    final static int MOD = (int)1e9 + 7;
    static long[] fact = new long[100_002];

    public static long mod(long num){
        return (num %= MOD) < 0 ? num + MOD : num;
    }

    public static long pow(long a, long exp){
        long ret = 1;

        while (exp > 0){
            if ((exp & 1) == 1) ret = (ret * a) % MOD;

            a = (a * a) % MOD;
            exp >>= 1;
        }

        return ret;
    }

    public static long sigma(int n, int d){
        long ans = 0;
        long[] s = new long[d + 2];

        for (int i = 1; i <= d + 1; i++) s[i] = mod(s[i - 1] + pow(i, d));
        if (d + 1 >= n) return s[n];

        long pi = 1;

        for (int i = 0; i <= d + 1; i++) pi = mod(pi * mod(n - i));
        for (int i = 0; i <= d + 1; i++){
            long numerator = s[i], denominator = 1;
            numerator = mod(numerator * pi); numerator = mod(numerator * ((d + 1 - i) % 2 == 1 ? -1 : 1));
            denominator = mod(denominator * mod(n - i));
            denominator = mod(denominator * fact[i]);
            denominator = mod(denominator * fact[d + 1 - i]);
            ans = mod(ans + (mod(numerator * pow(denominator, MOD - 2))));
        }

        return ans;
    }

    public static long kitamasa(int a, int b, int d){
        return mod(sigma(b, d) - sigma(a - 1, d));
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int q = in.nextInt();
        fact[0] = 1;

        for (int i = 1; i <= 100_001; i++) fact[i] = mod(fact[i - 1] * i);
        
        while (q-- > 0){
            int a = in.nextInt();
            int b = in.nextInt();
            int d = in.nextInt();
            sb.append(kitamasa(a, b, d)).append('\n');
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