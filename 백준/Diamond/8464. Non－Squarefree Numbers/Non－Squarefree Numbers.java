//import java.io.FileInputStream;

public class Main {
    static long n;
    static int[] prime;
    static int[] unionOperator;

    public static int soe(boolean[] isPrime){
        int maxPrime = isPrime.length - 1;
        int idx = 1;

        for (int i = 2; i <= maxPrime; i++){
            if (isPrime[i]) continue;

            for (int j = 2 * i; j <= maxPrime; j += i)
                isPrime[j] = true;

            prime[idx++] = i;
        }

        return idx - 1;
    }

    public static long lowerBound(long s, long e, long key){
        while (s < e){
            long m = s + e >> 1;
            long result = countSquarefulNumber(m);

            if (result < key) s = m + 1;
            else e = m;
        }
        return e;
    }

    public static long countSquarefulNumber(long n){
        long cnt = 0;

        for (long i = 2; i * i <= n; i++){
            cnt += n / (i * i) * unionOperator[(int)i];
        }

        return cnt;
    }

    public static void initUnionOperator(int n, int unionCnt){
        jump : for (int i = 2; i <= n; i++){
            for (int j = 2; j * j <= i; j++)
                if (i % (j * j) == 0)
                    continue jump;

            int number = i;
            int overlap = 0;

            for (int j = 1; j <= unionCnt; j++){
                if (number < prime[j]) break;

                if (number % prime[j] == 0){
                    number /= prime[j];
                    overlap++;
                }
            }

            if (overlap % 2 == 0)
                unionOperator[i] = -1;
            else
                unionOperator[i] = 1;
        }
    }

    public static void main(String args[]) throws Exception {
        Reader in = new Reader();
        n = in.nextLong();
        int maxPrime = (int)Math.sqrt(n << 2);
        boolean[] isPrime = new boolean[maxPrime + 1];
        prime = new int[maxPrime + 1];
        unionOperator = new int[maxPrime + 1];

        int cnt = soe(isPrime);
        initUnionOperator(maxPrime, cnt);
        long kthSqareFreeInt = lowerBound(1, n << 2, n);

        System.out.println(kthSqareFreeInt);
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