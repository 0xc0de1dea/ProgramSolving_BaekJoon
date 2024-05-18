/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

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

    static long mod(long a, long b){
        if (a > b) return b + a % b;
        else return a;
    }

    static long add(long x, long y, long mod){
        x = mod(x, mod); y = mod(y, mod);
        return (x > mod - y ? x - (mod - y) + mod : x + y);
    }

    static long mul(long x, long y, long mod){
        long ret = 0;

        while (y > 0){
            if ((y & 1) == 1) ret = add(ret, x, mod);
            x = add(x, x, mod); y >>= 1;
        }

        return ret;
    }

    static long pow(long x, long y, long mod){
        long ret = 1;

        while (y > 0){
            if ((y & 1) == 1) ret = mul(ret, x, mod);
            x = mul(x , x, mod); y >>= 1;
        }

        return ret;
    }

    static long f(long x, long m, long depth){
        if (m == 1 || depth == 0) return 1;
        long phi = phi(m);
        return pow(x, f(x, phi, depth - 1) + (x >= phi ? phi : 0), m);
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int t = in.nextInt();

        while (t-- > 0){
            long n = in.nextLong();
            sb.append(phi(n + 1)).append('\n');
        }

        System.out.print(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
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
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
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
        return (64 < c && c < 91) || (96 < c && c < 123);
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}