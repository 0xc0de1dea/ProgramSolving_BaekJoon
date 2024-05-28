/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n;
    static int[][] map;
    static long[][][] dp;

    public static long dp(int r, int c, int type){
        if (dp[r][c][type] >= 0) return dp[r][c][type];

        if (type == 0){
            if (map[r][c - 1] == 1 || map[r][c] == 1) return 0;

            return dp[r][c][type] = dp(r, c - 1, 0) + dp(r, c - 1, 2);
        } else if (type == 1){
            if (map[r - 1][c] == 1 || map[r][c] == 1) return 0;

            return dp[r][c][type] = dp(r - 1, c, 1) + dp(r - 1, c, 2);
        } else {
            if (map[r - 1][c - 1] == 1 || map[r - 1][c] == 1 || map[r][c - 1] == 1 || map[r][c] == 1) return 0;

            return dp[r][c][type] = dp(r - 1, c - 1, 0) + dp(r - 1, c - 1, 1) + dp(r - 1, c - 1, 2);
        }
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        map = new int[n + 2][n + 2];
        dp = new long[n + 2][n + 2][3];

        for (int i = 0; i <= n + 1; i++){
            map[i][0] = map[i][n + 1] = 1;
            map[0][i] = map[n + 1][i] = 1;
        }

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                map[i][j] = in.nextInt();
                
                for (int k = 0; k < 3; k++){
                    dp[i][j][k] = -1;
                }
            }
        }

        dp[1][2][0] = 1;

        long tot = dp(n, n, 0) + dp(n, n, 1) + dp(n, n, 2);

        System.out.print(tot);
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