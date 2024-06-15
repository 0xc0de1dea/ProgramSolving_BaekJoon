import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n, m;
    static int[] stone;
    static int[][] dp;
    static final int INF = 0x3f3f3f3f;

    public static int dp(int idx, int jump){
        if (idx == n) return 0;

        if (dp[idx][jump] != -1) return dp[idx][jump];

        int min = INF;

        int slow = jump - 1;
        int keep = jump;
        int fast = jump + 1;

        if (slow >= 1){
            if (idx + slow <= n && stone[idx + slow] == 0){
                min = Math.min(min, 1 + dp(idx + slow, slow));
            }
        }

        if (keep >= 1){
            if (idx + keep <= n && stone[idx + keep] == 0){
                min = Math.min(min, 1 + dp(idx + keep, keep));
            }
        }

        if (idx + fast <= n && stone[idx + fast] == 0){
            min = Math.min(min, 1 + dp(idx + fast, fast));
        }

        return dp[idx][jump] = min;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        stone = new int[10_001];
        dp = new int[10_001][150];

        for (int i = 0; i < 10_001; i++){
            for (int j = 0; j < 150; j++){
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < m; i++){
            stone[in.nextInt()] = 1;
        }

        int res = dp(1, 0);

        System.out.println(res == INF ? -1 : res);
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