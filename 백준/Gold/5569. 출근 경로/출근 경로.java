/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int[][][][] dp;
    static int w, h;
    static final int MOD = 100_000;

    public static int dfs(int r, int c, int rotType, int rem){
        if (r == h && c == w){
            return 1;
        } else if (r > h || c > w){
            return 0;
        }

        if (rem < 0) rem = 0;

        if (dp[r][c][rotType][rem] > 0){
            return dp[r][c][rotType][rem];
        }

        if (rem == 1){
            if (rotType == 0) dp[r][c][rotType][rem] += dfs(r + 1, c, rotType, 0) % MOD;
            else dp[r][c][rotType][rem] += dfs(r, c + 1, rotType, 0) % MOD;
        } else {
            if (rotType == 0) dp[r][c][rotType][rem] += (dfs(r + 1, c, rotType, 0) + dfs(r, c + 1, 1, 1)) % MOD;
            else if (rotType == 1) dp[r][c][rotType][rem] += (dfs(r + 1, c, 0, 1) + dfs(r, c + 1, rotType, 0)) % MOD;
        }

        return dp[r][c][rotType][rem];
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        w = in.nextInt();
        h = in.nextInt();
        dp = new int[h + 1][w + 1][3][2];
        
        int cnt = (dfs(2, 1, 0, 0) + dfs(1, 2, 1, 0)) % MOD;
        System.out.print(cnt);
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