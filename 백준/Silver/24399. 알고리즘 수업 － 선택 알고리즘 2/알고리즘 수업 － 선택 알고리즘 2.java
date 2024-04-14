/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int K;
    static int cnt = 0;
    static StringBuilder sb = new StringBuilder();

    public static int select(int[] a, int p, int r, int q){
        if (p == r) return a[p];

        int t = partition(a, p, r);
        int k = t - p + 1;

        if (k > q) return select(a, p, t - 1, q);
        else if (k == q) return a[t];
        else return select(a, t + 1, r, q - k);
    }

    public static int partition(int[] a, int p, int r){
        int x = a[r];
        int idx = p - 1;

        for (int i = p; i < r; i++){
            if (a[i] <= x){
                swap(a, ++idx, i);
            }
        }

        if (idx + 1 != r) swap(a, idx + 1, r);

        return idx + 1;
    }

    public static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;

        cnt++;

        if (cnt == K){
            for (int l = 0; l < a.length; l++){
                sb.append(a[l]).append(' ');
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        
        int N = in.nextInt();
        int Q = in.nextInt();
        K = in.nextInt();
        int[] elements = new int[N];

        for (int i = 0; i < N; i++){
            elements[i] = in.nextInt();
        }

        select(elements, 0, N - 1, Q);

        if (cnt < K){
            System.out.print(-1);
        } else {
            System.out.print(sb);
        }
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
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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