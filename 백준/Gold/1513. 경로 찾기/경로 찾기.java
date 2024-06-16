/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

 public class Main {
    static int n, m, k;
    static int[][] map;
    static int[][][][] dp;
    static int[] dr = { 0, 1 };
    static int[] dc = { 1, 0 };
    static final int MOD = 1_000_007;

    public static int dp(int r, int c, int visit, int cnt){
        if (r == n && c == m && cnt == 0) return 1;

        if (dp[r][c][visit][cnt] != -1) return dp[r][c][visit][cnt];

        int sum = 0;

        for (int i = 0; i < 2; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (0 < nr && nr <= n && 0 < nc && nc <= m){
                if (map[nr][nc] == 0){
                    sum += dp(nr, nc, visit, cnt);
                } else if (map[nr][nc] > visit && cnt > 0){
                    sum += dp(nr, nc, map[nr][nc], cnt - 1);
                }
            }
        }

        sum %= MOD;

        return dp[r][c][visit][cnt] = sum;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        map = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1][k + 1][k + 1];
        
        for (int i = 1; i <= k; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            map[a][b] = i;
        }

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                for (int p = 0; p <= k; p++){
                    for (int q = 0; q <= k; q++){
                        dp[i][j][p][q] = -1;
                    }
                }
            }
        }

        for (int i = 0; i <= k; i++){
            int res = 0;

            if (map[1][1] > 0 && i > 0){
                res = dp(1, 1, map[1][1], i - 1);
            } else if (map[1][1] == 0){
                res = dp(1, 1, 0, i);
            }

            sb.append(res).append(' ');
        }

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