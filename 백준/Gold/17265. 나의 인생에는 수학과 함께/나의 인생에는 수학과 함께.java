/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n;
    static char[][] map;
    static int[][] minDp;
    static int[][] maxDp;
    static final int INF = 123456789;

    public static int calc(int a, char op, int b){
        if (op == '+') return a + b;
        else if (op == '-') return a - b;
        else return a * b;
    }

    public static int getMax(int r, int c){
        if (maxDp[r][c] != INF) return maxDp[r][c];

        if (r == 1 && c == 1) return map[r][c] - '0';

        int max = -INF;

        if (r - 2 > 0){
            max = Math.max(max, calc(getMax(r - 2, c), map[r - 1][c], map[r][c] - '0'));
        }

        if (c - 2 > 0){
            max = Math.max(max, calc(getMax(r, c - 2), map[r][c - 1], map[r][c] - '0'));
        }

        if (r - 1 > 0 && c - 1 > 0){
            max = Math.max(max, calc(getMax(r - 1, c - 1), map[r - 1][c], map[r][c] - '0'));
            max = Math.max(max, calc(getMax(r - 1, c - 1), map[r][c - 1], map[r][c] - '0'));
        }

        return maxDp[r][c] = max;
    }

    public static int getMin(int r, int c){
        if (minDp[r][c] != INF) return minDp[r][c];

        if (r == 1 && c == 1) return map[r][c] - '0';

        int min = INF;

        if (r - 2 > 0){
            min = Math.min(min, calc(getMin(r - 2, c), map[r - 1][c], map[r][c] - '0'));
        }

        if (c - 2 > 0){
            min = Math.min(min, calc(getMin(r, c - 2), map[r][c - 1], map[r][c] - '0'));
        }

        if (r - 1 > 0 && c - 1 > 0){
            min = Math.min(min, calc(getMin(r - 1, c - 1), map[r - 1][c], map[r][c] - '0'));
            min = Math.min(min, calc(getMin(r - 1, c - 1), map[r][c - 1], map[r][c] - '0'));
        }

        return minDp[r][c] = min;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        n = in.nextInt();
        map = new char[n + 1][n + 1];
        minDp = new int[n + 1][n + 1];
        maxDp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                map[i][j] = in.nextChar();
                minDp[i][j] = INF;
                maxDp[i][j] = INF;
            }
        }

        sb.append(getMax(n, n)).append(' ').append(getMin(n, n));
        System.out.println(sb);
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
        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
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